package tech.kamikazi.warehousemanagementsystembackend.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.kamikazi.warehousemanagementsystembackend.dto.CreatingSsccDto;
import tech.kamikazi.warehousemanagementsystembackend.dto.SsccDto;
import tech.kamikazi.warehousemanagementsystembackend.entities.CartonHeader;
import tech.kamikazi.warehousemanagementsystembackend.entities.SSCC;
import tech.kamikazi.warehousemanagementsystembackend.mappers.SsccMapper;
import tech.kamikazi.warehousemanagementsystembackend.repositories.CartonHeaderRepository;
import tech.kamikazi.warehousemanagementsystembackend.repositories.SsccRepository;

import java.time.Instant;

@Service
@AllArgsConstructor
@Getter
@Setter
public class SsccService {

    private final SsccRepository ssccRepository;
    private final CartonHeaderRepository cartonHeaderRepository;
    private final SsccMapper ssccMapper;

    @Transactional
    public SsccDto createSscc(CreatingSsccDto createSsccDto, Long cartonHeaderId) {

        if (ssccRepository.existsBySsccAndCartonHeaderId(createSsccDto.getSscc(), cartonHeaderId)) {
            throw new IllegalStateException(
                    "SSCC '" + createSsccDto.getSscc() + "' already exists"
            );
        }

        CartonHeader cartonHeader = cartonHeaderRepository
                .findById(cartonHeaderId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "CartonHeader not found with id " + cartonHeaderId
                        )
                );

        System.out.println("cartonHeaderId: " + cartonHeaderId);

        SSCC sscc = ssccMapper.toEntity(createSsccDto);
        sscc.setCartonHeader(cartonHeader);

        Instant now = Instant.now();
        sscc.setCreatedTimestamp(now);
        sscc.setUpdatedTimestamp(now);

        SSCC saved = ssccRepository.save(sscc);

        return ssccMapper.toDto(saved);
    }
}