package kg.mega.natv_v1.services.impl;

import kg.mega.natv_v1.dao.TextRep;
import kg.mega.natv_v1.mappers.TextMapper;
import kg.mega.natv_v1.models.dtos.TextDto;
import kg.mega.natv_v1.models.entities.Text;
import kg.mega.natv_v1.services.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TextServiceImpl implements TextService {
    private final TextRep textRep;
    private final TextMapper textMapper = TextMapper.INSTANCE;
    @Override
    public TextDto save(TextDto textDto) {
        Text text = textMapper.textDtoToText(textDto);
        text = textRep.save(text);
        textDto.setId(text.getId());
        return textDto;
    }

    @Override
    public TextDto findById(Long id) {
        return null;
    }

    @Override
    public List<TextDto> findAll() {
        return null;
    }

    @Override
    public TextDto update(TextDto t) {
        return null;
    }

    @Override
    public TextDto delete(Long id) {
        return null;
    }
}
