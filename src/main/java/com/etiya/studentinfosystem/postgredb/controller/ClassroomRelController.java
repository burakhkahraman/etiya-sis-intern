package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.model.ClassroomRel;
import com.etiya.studentinfosystem.postgredb.service.ClassroomRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classroom-relations")
public class ClassroomRelController {
    @Autowired
    private ClassroomRelService classroomRelService;

    @GetMapping
    public ResponseEntity<List<ClassroomRel>> getAllRelations() {
        return ResponseEntity.ok(classroomRelService.getAllRelations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomRel> getRelationById(@PathVariable Long id) {
        return classroomRelService.getRelationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClassroomRel> createRelation(@RequestBody ClassroomRel classroomRel) {
        return ResponseEntity.ok(classroomRelService.createRelation(classroomRel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomRel> updateRelation(@PathVariable Long id, @RequestBody ClassroomRel classroomRel) {
        ClassroomRel updatedClassroomRel = classroomRelService.updateRelation(id, classroomRel);
        if (updatedClassroomRel != null) {
            return ResponseEntity.ok(updatedClassroomRel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelation(@PathVariable Long id) {
        classroomRelService.deleteRelation(id);
        return ResponseEntity.noContent().build();
    }
}
