package com.study.bookmark.repository;
import com.study.bookmark.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    List<Material> findByTagsContaining(String tag);
}
