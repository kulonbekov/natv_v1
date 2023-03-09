package kg.mega.natv_v1.services.crudOperations.impl;

import kg.mega.natv_v1.dao.UserRep;
import kg.mega.natv_v1.mappers.UserMapper;
import kg.mega.natv_v1.models.dtos.UserDto;
import kg.mega.natv_v1.models.entities.User;
import kg.mega.natv_v1.models.enums.UserStatus;
import kg.mega.natv_v1.services.crudOperations.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRep userRep;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user = userRep.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRep.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.userToUserDtoList(userRep.findAll());
    }

    @Override
    public UserDto update(UserDto t) {
        return null;
    }

    @Override
    public UserDto delete(Long id) {
        UserDto userDto = findById(id);
        userDto.setUserStatus(UserStatus.FALSE);
        return save(userDto);
    }
}
