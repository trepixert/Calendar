package ulstu.backend.calendar.service.implementations;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ulstu.backend.calendar.dto.FileDTO;
import ulstu.backend.calendar.models.File;
import ulstu.backend.calendar.models.User;
import ulstu.backend.calendar.repository.FileRepo;
import ulstu.backend.calendar.repository.UserRepo;
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
    private DbxClientV2 client;

    @Override
    public List<FileDTO> findAllByUser(User user) {
        return fileRepo.findAllByUser(user)
                .stream()
                .map(file -> new FileDTO(file.getUrl(), file.getFilename()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<FileDTO> save(MultipartFile file, User user) {
        try (InputStream in = file.getInputStream()) {
            String path = "/" + file.getOriginalFilename();
            client.files()
                    .uploadBuilder(path)
                    .uploadAndFinish(in);
            SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings(path);
            File newFile = new File(sharedLinkMetadata.getId(), sharedLinkMetadata.getUrl(), sharedLinkMetadata.getName());
            newFile.setUser(user);
            fileRepo.save(newFile);
            return Optional.of(new FileDTO(newFile.getUrl(), newFile.getFilename()));
        } catch (IOException | DbxException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
