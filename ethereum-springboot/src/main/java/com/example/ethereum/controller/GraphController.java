package com.example.ethereum.controller;

import com.example.ethereum.service.TheGraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/graph")
@RequiredArgsConstructor
public class GraphController {

    private final TheGraphService graphService;

    @GetMapping("/value-changes")
    public ResponseEntity<List<TheGraphService.ValueChangeEvent>> getValueChanges(
            @RequestParam(defaultValue = "0") BigInteger minValue,
            @RequestParam(defaultValue = "1000000") BigInteger maxValue) {

        try {
            List<TheGraphService.ValueChangeEvent> events =
                    graphService.getValueChangesInRange(minValue, maxValue);
            return ResponseEntity.ok(events);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}