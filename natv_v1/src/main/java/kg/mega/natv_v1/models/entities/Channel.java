package kg.mega.natv_v1.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.natv_v1.models.enums.ChannelStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_channel")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String channelName;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    Date createdDate;
    ChannelStatus channelStatus;
    String logoPath;

}

