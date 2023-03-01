package kg.mega.natv_v1.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.natv_v1.models.utils.DateUtil;
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
@Table(name = "tb_discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int discount;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    Date startDate;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    Date endDate;
    int discountDays;
    @ManyToOne
    @JoinColumn(name = "channel_id")
    Channel channel;

    @PrePersist
    protected void onCreate() {
        endDate = DateUtil.getINSTANCE().getEndDate();
        startDate = new Date();
    }


}
