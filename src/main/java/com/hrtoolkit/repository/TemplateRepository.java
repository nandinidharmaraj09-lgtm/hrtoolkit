package com.hrtoolkit.repository;

import com.hrtoolkit.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
    List<Template> findByCategoryId(Long categoryId);
    List<Template> findByIsActiveTrue();
}
