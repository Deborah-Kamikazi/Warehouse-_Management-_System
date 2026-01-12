package tech.kamikazi.warehousemanagementsystembackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kamikazi.warehousemanagementsystembackend.dto.CreatingSsccDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.SsccDto;
import tech.kamikazi.warehousemanagementsystembackend.services.SsccService;

@RestController
@RequestMapping("/api/carton-headers")
@AllArgsConstructor
public class SsccController {

    private final SsccService ssccService;


    @PostMapping("/{cartonHeaderId}/ssccs")
    public ResponseEntity<SsccDto> createSscc(
            @PathVariable Long cartonHeaderId,
            @RequestBody CreatingSsccDto createSsccDto
    ) {
        SsccDto createdSscc = ssccService.createSscc(createSsccDto, cartonHeaderId);
        return new ResponseEntity<>(createdSscc, HttpStatus.CREATED);
    }
}
