package kg.mega.natv_v1.dao;

import kg.mega.natv_v1.models.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRep extends JpaRepository<Discount, Long> {

    @Query(value = "select * from tb_discount where channel_id = :id", nativeQuery = true)
    List<Discount> getDiscounts(Long id);

}
