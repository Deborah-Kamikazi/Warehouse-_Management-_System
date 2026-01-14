package tech.kamikazi.warehousemanagementsystembackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.kamikazi.warehousemanagementsystembackend.dto.MoveItemDto;
import tech.kamikazi.warehousemanagementsystembackend.services.MoveItemsService;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MoveItemController {
    private MoveItemsService moveItemsService;

    @PostMapping("/move")
    public ResponseEntity<MoveItemDto> moveItem(@RequestBody MoveItemDto moveItemDto) {
        MoveItemDto result = moveItemsService.moveItem(moveItemDto);
        return ResponseEntity.ok(result);

//        try {
//
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().build();
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body((MoveItemDto) Map.of("error", e.getMessage()));
//        }
    }
}
