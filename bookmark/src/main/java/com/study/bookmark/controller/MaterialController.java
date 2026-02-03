package com.study.bookmark.controller;

import com.study.bookmark.model.Material;
import com.study.bookmark.service.MaterialService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin

public class MaterialController {

    private final MaterialService service;

    public MaterialController(MaterialService service) {
        this.service = service;
    }

    @PostMapping("/add-material")
    public Material addMaterial(
            @RequestParam String title,
            @RequestParam(required=false) String description,
            @RequestParam(required=false) String youtubeLink,
            @RequestParam(required=false) String noteText,
            @RequestParam(required=false) String tags,
            @RequestParam(required=false) MultipartFile pdf) throws Exception {

        String pdfPath = null;

        if (pdf != null && !pdf.isEmpty()) {
            Files.createDirectories(Paths.get("uploads"));
            String fileName = UUID.randomUUID() + pdf.getOriginalFilename();
            Path path = Paths.get("uploads/" + fileName);
            Files.write(path, pdf.getBytes());
            pdfPath = "/uploads/" + fileName;
        }

        Material m = new Material();
        m.setTitle(title);
        m.setDescription(description);
        m.setYoutubeLink(youtubeLink);
        m.setNoteText(noteText);
        m.setTags(tags);
        m.setPdfPath(pdfPath);

        return service.save(m);
    }

    @GetMapping("/materials")
    public List<Material> getAll() {
        return service.getAll();
    }

    @GetMapping("/materials/search")
    public List<Material> search(@RequestParam String tag) {
        return service.searchByTag(tag);
    }
    @DeleteMapping("/materials/delete")
public ResponseEntity<Void> deleteMaterial(@RequestParam Integer id) { 
    boolean deleted = service.deleteById(id);
    if (deleted) {
        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
