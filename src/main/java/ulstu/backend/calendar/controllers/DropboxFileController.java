package ulstu.backend.calendar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ulstu.backend.calendar.dto.FileDTO;
import ulstu.backend.calendar.service.FileService;

import java.util.List;

@RestController
@RequestMapping("/storage")
public class DropboxFileController {

    @Autowired
    private FileService fileService;

    @GetMapping
    public List<FileDTO> findAll(){
        return fileService.findAll();
    }

    @PostMapping("/save/{eventId}")
    public ResponseEntity<FileDTO> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable Long eventId){
        FileDTO savedFile = fileService.save(file, eventId).orElse(null);
        return ResponseEntity.ok(savedFile);
    }
}
