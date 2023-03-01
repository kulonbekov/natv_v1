package kg.mega.natv_v1.models.entities;

import kg.mega.natv_v1.models.enums.Role;
import kg.mega.natv_v1.models.enums.UserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String fio;

    Role role;
    String login;
    String email;
    String phone;
    UserStatus userStatus;


}
