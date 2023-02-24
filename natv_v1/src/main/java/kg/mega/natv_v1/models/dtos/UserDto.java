package kg.mega.natv_v1.models.dtos;

import kg.mega.natv_v1.models.enums.Role;
import kg.mega.natv_v1.models.enums.UserStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    String fio;
    Role role;
    String login;
    String email;
    String phone;
    UserStatus userStatus;
}
