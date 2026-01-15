package tech.kamikazi.warehousemanagementsystembackend.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;
import tech.kamikazi.warehousemanagementsystembackend.dto.WarehouseDto;
import tech.kamikazi.warehousemanagementsystembackend.entities.Warehouse;
import tech.kamikazi.warehousemanagementsystembackend.mappers.WarehouseMapper;
import tech.kamikazi.warehousemanagementsystembackend.repositories.WarehouseRepository;
import tech.kamikazi.warehousemanagementsystembackend.services.WarehouseService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WarehouseServiceTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @Mock
    private WarehouseMapper warehouseMapper;

    @InjectMocks
    private WarehouseService warehouseService;

    private Warehouse warehouse;
    private WarehouseDto warehouseDto;

    @BeforeEach
    void setup() {
        warehouse = new Warehouse();
        warehouse.setId(1);
        warehouse.setWarehouseNumber("1");
        warehouse.setName("Main Warehouse");
        warehouse.setActive(true);

        warehouseDto = new WarehouseDto();
        warehouseDto.setWarehouseNumber("1");
        warehouseDto.setName("Main Warehouse");
        warehouseDto.setActive(true);
    }


    @Test
    void createWarehouse_shouldCreateWarehouse() {
        when(warehouseRepository.existsByWarehouseNumber("1")).thenReturn(false);
        when(warehouseMapper.toEntity(warehouseDto)).thenReturn(warehouse);
        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);
        when(warehouseMapper.toDto(warehouse)).thenReturn(warehouseDto);

        WarehouseDto result = warehouseService.createWarehouse(warehouseDto);

        assertNotNull(result);
        assertEquals("1", result.getWarehouseNumber());
        verify(warehouseRepository).save(any(Warehouse.class));
    }

    @Test
    void createWarehouse_duplicateWarehouseNumber_shouldThrowException() {
        when(warehouseRepository.existsByWarehouseNumber("1")).thenReturn(true);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> warehouseService.createWarehouse(warehouseDto)
        );

        assertTrue(ex.getMessage().contains("already exists"));
        verify(warehouseRepository, never()).save(any());
    }


    @Test
    void updateWarehouse_shouldUpdateWarehouse() {
        when(warehouseRepository.findById(1)).thenReturn(Optional.of(warehouse));
        when(warehouseRepository.save(warehouse)).thenReturn(warehouse);
        when(warehouseMapper.toDto(warehouse)).thenReturn(warehouseDto);

        WarehouseDto result = warehouseService.updateWarehouse(1, warehouseDto);

        assertNotNull(result);
        verify(warehouseRepository).save(warehouse);
    }

    @Test
    void updateWarehouse_notFound_shouldThrowException() {
        when(warehouseRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> warehouseService.updateWarehouse(1, warehouseDto)
        );

        assertTrue(ex.getMessage().contains("doesn't exist"));
    }

    @Test
    void updateWarehouse_optimisticLockingConflict_shouldThrowIllegalState() {
        when(warehouseRepository.findById(1)).thenReturn(Optional.of(warehouse));
        when(warehouseRepository.save(warehouse))
                .thenThrow(new OptimisticLockingFailureException("Conflict"));

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> warehouseService.updateWarehouse(1, warehouseDto)
        );

        assertTrue(ex.getMessage().contains("Conflict"));
    }



    @Test
    void getAllWarehouses_shouldReturnList() {
        when(warehouseRepository.findAll()).thenReturn(List.of(warehouse));
        when(warehouseMapper.toDto(warehouse)).thenReturn(warehouseDto);

        List<WarehouseDto> result = warehouseService.getAllWarehouses();

        assertEquals(1, result.size());
        verify(warehouseRepository).findAll();
    }
}
