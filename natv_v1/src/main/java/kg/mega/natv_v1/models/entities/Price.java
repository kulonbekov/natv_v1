package kg.mega.natv_v1.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "tb_price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    double pricePerSymbol;
    double bannerPrice;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    Date startDate;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    Date endDate;
    @ManyToOne
    @JoinColumn(name = "channel_id")
    Channel channel;
}
