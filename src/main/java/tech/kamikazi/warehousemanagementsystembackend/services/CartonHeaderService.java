package tech.kamikazi.warehousemanagementsystembackend.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.kamikazi.warehousemanagementsystembackend.dto.CartonHeaderDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.CreateCartonHeaderDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.UpdateCartonHeaderDto;
import tech.kamikazi.warehousemanagementsystembackend.entities.CartonHeader;
import tech.kamikazi.warehousemanagementsystembackend.mappers.CartonHeaderMapper;
import tech.kamikazi.warehousemanagementsystembackend.repositories.CartonHeaderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CartonHeaderService {
    private CartonHeaderRepository cartonHeaderRepository;
    private CartonHeaderMapper cartonHeaderMapper;

    public CartonHeaderDto createCartonHeader(CreateCartonHeaderDto cartonHeaderDto) {
        if (cartonHeaderRepository.existsByBarcode(cartonHeaderDto.getBarcode())) {
            throw new IllegalArgumentException(
                    "Carton header with barcode " + cartonHeaderDto.getBarcode() + " already exists"
            );
        }
        CartonHeader cartonHeader = cartonHeaderMapper.toEntity(cartonHeaderDto);
        CartonHeader saved = cartonHeaderRepository.save(cartonHeader);

        return cartonHeaderMapper.toDto(saved);
    }

    public CartonHeaderDto updateCartonHeader(  String barcode  , UpdateCartonHeaderDto updateCartonHeaderDto) {

        CartonHeader cartonHeader = (CartonHeader) cartonHeaderRepository.findByBarcode(barcode)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Carton header not found with barcode: " + barcode
                        )
                );

        cartonHeader.setDescription(updateCartonHeaderDto.getDescription());

        try {
            CartonHeader updated = cartonHeaderRepository.save(cartonHeader);
            return cartonHeaderMapper.toDto(updated);
        } catch (OptimisticLockingFailureException e) {
            throw new IllegalStateException(
                    "Conflict: Carton header was updated by another user",
                    e
            );

        }

    }
    public List<CartonHeaderDto> getAllCartonHeader() {
        return cartonHeaderRepository.findAll()
                .stream()
                .map(cartonHeaderMapper::toDto)
                .collect(Collectors.toList());
    }
}


