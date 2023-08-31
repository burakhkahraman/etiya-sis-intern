package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.dto.ResultOfExamDTO;
import com.etiya.studentinfosystem.postgredb.model.ResultOfExam;
import com.etiya.studentinfosystem.postgredb.service.ResultOfExamService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultOfExamController {

    @Autowired
    private ResultOfExamService resultOfExamService;

    @GetMapping
    @Operation(summary = "Tüm sınav sonuçlarını listeler")
    public List<ResultOfExamDTO> getAllResults() {
        return resultOfExamService.getAllResults();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Belirtilen ID'ye sahip sınav sonucunu getirir")
    public ResponseEntity<ResultOfExamDTO> getResultById(@PathVariable Long id) {
        return resultOfExamService.getResultById(id)
                .map(result -> ResponseEntity.ok().body(result))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Yeni bir sınav sonucu ekler")
    public ResultOfExamDTO createResult(@RequestBody ResultOfExamDTO resultOfExamDTO) {
        return resultOfExamService.createResult(resultOfExamDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Belirtilen ID'ye sahip sınav sonucunu günceller")
    public ResponseEntity<ResultOfExamDTO> updateResult(@PathVariable Long id, @RequestBody ResultOfExamDTO resultOfExamDTO) {
        return resultOfExamService.updateResult(id, resultOfExamDTO)
                .map(updatedResult -> ResponseEntity.ok().body(updatedResult))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Belirtilen ID'ye sahip sınav sonucunu siler")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        if (resultOfExamService.deleteResult(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
