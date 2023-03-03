package kg.mega.natv_v1.mappers;

import kg.mega.natv_v1.models.dtos.UserDto;
import kg.mega.natv_v1.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDto);

    List<User> userDtoToUserList(List<UserDto> userDtoList);

    UserDto userToUserDto(User user);

    List<UserDto> userToUserDtoList(List<User> users);

}
