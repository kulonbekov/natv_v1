package kg.mega.natv_v1.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.natv_v1.models.enums.OrderStatus;
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
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    Date createdDate;
    String clientEmail;
    String clientFio;
    String clientPhone;
    OrderStatus orderStatus;
    double orderSum;
    @ManyToOne
    @JoinColumn(name = "channel_id")
    Channel channel;
    @ManyToOne
    @JoinColumn(name = "text_id")
    Text text;
    @ManyToOne
    @JoinColumn(name = "banner_id")
    Banner banner;



}
