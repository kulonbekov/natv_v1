package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.TextDto;
import kg.mega.natv_v1.models.entities.Text;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TextMapper {
    TextMapper INSTANCE = Mappers.getMapper(TextMapper.class);

    Text textDtoToText(TextDto textDto);

    List<Text> textDtoToTextList(List<TextDto> textDtoList);

    TextDto textToTextDto(Text text);

    List<TextDto> textToTextDtoList(List<Text> text);

}
