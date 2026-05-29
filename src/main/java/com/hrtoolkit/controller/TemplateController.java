package com.hrtoolkit.controller;

import com.hrtoolkit.entity.Template;
import com.hrtoolkit.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/templates")
@CrossOrigin(origins = "*")
public class TemplateController {

    @Autowired
    private TemplateRepository templateRepository;

    @GetMapping
    public ResponseEntity<List<Template>> getAllTemplates() {
        return ResponseEntity.ok(templateRepository.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Template>> getActiveTemplates() {
        return ResponseEntity.ok(templateRepository.findByIsActiveTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Template> getTemplateById(@PathVariable Long id) {
        Optional<Template> template = templateRepository.findById(id);
        return template.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Template>> getTemplatesByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(templateRepository.findByCategoryId(categoryId));
    }

    @PostMapping
    public ResponseEntity<Template> createTemplate(@RequestBody Template template) {
        Template savedTemplate = templateRepository.save(template);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTemplate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Template> updateTemplate(@PathVariable Long id, @RequestBody Template templateDetails) {
        Optional<Template> template = templateRepository.findById(id);
        if (template.isPresent()) {
            Template existingTemplate = template.get();
            existingTemplate.setName(templateDetails.getName());
            existingTemplate.setDescription(templateDetails.getDescription());
            existingTemplate.setContent(templateDetails.getContent());
            existingTemplate.setIsActive(templateDetails.getIsActive());
            return ResponseEntity.ok(templateRepository.save(existingTemplate));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        if (templateRepository.existsById(id)) {
            templateRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
