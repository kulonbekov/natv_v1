package kg.mega.natv_v1.dao;

import kg.mega.natv_v1.models.entities.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRep extends JpaRepository<Channel, Long> {

    @Query(value = "select * from tb_channel where channel_status = 'TRUE' order by channel_name asc", nativeQuery = true)
    List<Channel> findAllByActive();

    @Query(value = "select * from tb_channel where id = :id and channel_status = 'TRUE'", nativeQuery = true)
    Channel finByIdAndActual(Long id);
}
