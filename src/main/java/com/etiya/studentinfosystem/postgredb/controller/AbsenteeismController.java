package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.model.Absenteeism;
import com.etiya.studentinfosystem.postgredb.service.AbsenteeismService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/absenteeisms")
@Tag(name = "Absenteeism", description = "Devamsızlık yönetimi")
public class AbsenteeismController {
    @Autowired
    private AbsenteeismService absenteeismService;

    // CREATE
    @PostMapping
    public ResponseEntity<Absenteeism> createAbsenteeism(@RequestBody Absenteeism absenteeism) {
        Absenteeism newAbsenteeism = absenteeismService.saveAbsenteeism(absenteeism);
        return ResponseEntity.ok(newAbsenteeism);
    }

    // READ
    @GetMapping
    public ResponseEntity<List<Absenteeism>> getAllAbsenteeisms() {
        List<Absenteeism> absenteeisms = absenteeismService.findAllAbsenteeisms();
        return ResponseEntity.ok(absenteeisms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Absenteeism> getAbsenteeismById(@PathVariable Long id) {
        Absenteeism absenteeism = absenteeismService.findAbsenteeismById(id);
        if (absenteeism != null) {
            return ResponseEntity.ok(absenteeism);
        }
        return ResponseEntity.notFound().build();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Absenteeism> updateAbsenteeism(@PathVariable Long id, @RequestBody Absenteeism absenteeism) {
        Absenteeism updatedAbsenteeism = absenteeismService.updateAbsenteeism(id, absenteeism);
        if (updatedAbsenteeism != null) {
            return ResponseEntity.ok(updatedAbsenteeism);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbsenteeism(@PathVariable Long id) {
        absenteeismService.deleteAbsenteeism(id);
        return ResponseEntity.noContent().build();
    }
}
