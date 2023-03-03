package kg.mega.natv_v1.services;

import java.util.List;

public interface BaseService<Dto> {

    Dto save(Dto dto);

    Dto findById(Long id);

    List<Dto> findAll();

    Dto update(Dto dto);

    Dto delete(Long id);

}
