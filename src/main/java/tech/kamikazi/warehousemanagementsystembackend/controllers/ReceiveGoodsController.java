package tech.kamikazi.warehousemanagementsystembackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.kamikazi.warehousemanagementsystembackend.dto.ItemDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.ReceiveDto;
import tech.kamikazi.warehousemanagementsystembackend.services.ReceiveGoodsService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/receive")
public class ReceiveGoodsController {
    private ReceiveGoodsService receiveGoodsService;
    @PostMapping()
   public ResponseEntity<ItemDto> receiveGoods(@RequestBody @Valid ReceiveDto receiveDto) {

        ItemDto itemDto = receiveGoodsService.receiveGoods(receiveDto);
        return ResponseEntity.ok(itemDto);
    }

}
