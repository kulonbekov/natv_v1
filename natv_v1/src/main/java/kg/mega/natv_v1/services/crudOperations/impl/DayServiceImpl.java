package kg.mega.natv_v1.services.crudOperations.impl;

import kg.mega.natv_v1.dao.DayRep;
import kg.mega.natv_v1.mappers.DayMapper;
import kg.mega.natv_v1.models.dtos.DayDto;
import kg.mega.natv_v1.models.entities.Day;
import kg.mega.natv_v1.services.crudOperations.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DayServiceImpl implements DayService {
    private final DayRep dayRep;
    private final DayMapper dayMapper = DayMapper.INSTANCE;

    @Override
    public DayDto save(DayDto dayDto) {
        Day day = dayMapper.dayDtoToDay(dayDto);
        day = dayRep.save(day);
        dayDto.setId(day.getId());
        return dayDto;
    }

    @Override
    public DayDto findById(Long id) {
        Day day = dayRep.findById(id).orElseThrow(() -> new RuntimeException("Order dates no found"));
        return dayMapper.dayToDayDto(day);
    }

    @Override
    public List<DayDto> findAll() {
        return dayMapper.dayToDayDtoList(dayRep.findAll());
    }

    @Override
    public DayDto update(DayDto t) {
        return null;
    }

    @Override
    public DayDto delete(Long id) {
        return null;
    }
}
