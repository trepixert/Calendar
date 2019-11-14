package ulstu.backend.calendar.service.implementations;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ulstu.backend.calendar.dto.FileDTO;
import ulstu.backend.calendar.models.Event;
import ulstu.backend.calendar.models.File;
import ulstu.backend.calendar.repository.EventRepo;
import ulstu.backend.calendar.repository.FileRepo;
import ulstu.backend.calendar.service.FileService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepo fileRepo;

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private DbxClientV2 client;

    @Override
    public List<FileDTO> findAll() {
        return fileRepo.findAll()
                .stream()
                .map(file -> new FileDTO(file.getUrl(), file.getFilename(), file.getEvent().getId()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<FileDTO> save(MultipartFile file, Long eventId) {
        Optional<Event> event = eventRepo.findById(eventId);
        try (InputStream in = file.getInputStream()) {
            if (!event.isPresent()) {
                throw new Exception("Ивент не найден");
            }
            String path = "/" + file.getOriginalFilename();
            client.files()
                    .uploadBuilder(path)
                    .uploadAndFinish(in);
            SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings(path);
            File newFile = new File(sharedLinkMetadata.getId(), sharedLinkMetadata.getUrl(), sharedLinkMetadata.getName());
            newFile.setEvent(event.get());
            fileRepo.save(newFile);
            return Optional.of(new FileDTO(newFile.getUrl(), newFile.getFilename(), eventId));
        } catch (IOException | DbxException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
