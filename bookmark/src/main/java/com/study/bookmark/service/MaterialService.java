package com.study.bookmark.service;

import com.study.bookmark.model.Material;
import com.study.bookmark.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaterialService {
 private final MaterialRepository repo;

    public MaterialService(MaterialRepository repo) {
        this.repo = repo;
    }

    public Material save(Material m) {
        return repo.save(m);
    }

    public List<Material> getAll() {
        return repo.findAll();
    }

    public List<Material> searchByTag(String tag) {
        return repo.findByTagsContaining(tag);
    }
     public boolean deleteById(Integer id) {   // ✅ Change Long → Integer
        if (repo.existsById(id)) {            // ✅ existsById now works
            repo.deleteById(id);              // ✅ deleteById now works
            return true;
        }
        return false;
    }
}
