package kg.mega.natv_v1.dao;

import kg.mega.natv_v1.models.entities.ChannelOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelOrderRep extends JpaRepository<ChannelOrder, Long> {
}
