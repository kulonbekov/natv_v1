package kg.mega.natv_v1.dao;

import kg.mega.natv_v1.models.entities.OrderDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDatesRep extends JpaRepository<OrderDates, Long> {

}
