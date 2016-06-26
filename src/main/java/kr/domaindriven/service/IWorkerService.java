package kr.domaindriven.service;

import kr.domaindriven.model.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by donghoon on 2016. 5. 31..
 */
public interface IWorkerService {

    Worker save(Worker worker);

    Worker findOne(String id);

    List<Worker> findAll();

    Page<Worker> findAll(Pageable pageable);

    Worker findByName(String name);

    void deleteWorker(String id);

    Long count();
}
