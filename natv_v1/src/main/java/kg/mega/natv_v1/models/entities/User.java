package kg.mega.natv_v1.models.entities;

import kg.mega.natv_v1.models.enums.Role;
import kg.mega.natv_v1.models.enums.UserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    Role role;
    String login;
    String email;
    String phone;
    String password;
    @Enumerated(EnumType.STRING)
    UserStatus userStatus;


}
