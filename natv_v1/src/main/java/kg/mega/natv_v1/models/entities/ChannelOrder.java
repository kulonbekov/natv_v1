package kg.mega.natv_v1.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_channel_order")
public class ChannelOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "channel_id")
    Channel channel;
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
    int daysCount;
    double price;
    double priceWithDiscount;
}
