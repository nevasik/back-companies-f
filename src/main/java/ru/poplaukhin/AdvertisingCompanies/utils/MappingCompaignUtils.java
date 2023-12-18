package ru.poplaukhin.AdvertisingCompanies.utils;

import org.springframework.stereotype.Service;
import ru.poplaukhin.AdvertisingCompanies.dto.CompaignDto;
import ru.poplaukhin.AdvertisingCompanies.entity.Compaign;

import java.util.Objects;

@Service
public class MappingCompaignUtils {
    // from Entity to DTO
    public CompaignDto mapToDtoFromEntity(Compaign compaign) {
        CompaignDto compaignDto = new CompaignDto();

        compaignDto.setId(compaign.getId());
        compaignDto.setName(compaign.getName());
        compaignDto.setTargetAudience(compaign.getTargetAudience());
        compaignDto.setBudget(compaign.getBudget());
        compaignDto.setStartDate(compaign.getStartDate());
        compaignDto.setEndDate(compaign.getEndDate());

        if (Objects.nonNull(compaign.getPerson())) {
            compaignDto.setPerson(compaign.getPerson().getId());
        }

        if (Objects.nonNull(compaign.getStatistics())) {
            compaignDto.setStatistics(compaign.getStatistics().getId());
        }

        return compaignDto;
    }

    // from DTO to Entity
    public Compaign mapToEntityFromDto(CompaignDto compaignDto) {
        Compaign compaign = new Compaign();

        compaign.setId(compaignDto.getId());
        compaign.setName(compaignDto.getName());
        compaign.setTargetAudience(compaignDto.getTargetAudience());
        compaign.setBudget(compaignDto.getBudget());
        compaign.setStartDate(compaignDto.getStartDate());
        compaign.setEndDate(compaignDto.getEndDate());
        compaign.setAvatar(compaignDto.getAvatar());

        return compaign;
    }
}
