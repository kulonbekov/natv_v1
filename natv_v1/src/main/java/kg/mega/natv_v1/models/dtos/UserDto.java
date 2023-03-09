package kg.mega.natv_v1.models.dtos;

import kg.mega.natv_v1.models.enums.Role;
import kg.mega.natv_v1.models.enums.UserStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    String fio;
    @Enumerated(EnumType.STRING)
    Role role;
    String login;
    String email;
    String phone;
    @Enumerated(EnumType.STRING)
    UserStatus userStatus;

}
