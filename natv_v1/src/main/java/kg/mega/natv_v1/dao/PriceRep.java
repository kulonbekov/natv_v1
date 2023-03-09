package kg.mega.natv_v1.dao;

import kg.mega.natv_v1.models.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRep extends JpaRepository<Price, Long> {

    @Query(value = "select * from tb_price where channel_id = :id", nativeQuery = true)
    List<Price> getPrice(Long id);
}
