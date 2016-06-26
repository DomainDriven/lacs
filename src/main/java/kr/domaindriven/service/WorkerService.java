package kr.domaindriven.service;

import kr.domaindriven.model.Worker;
import kr.domaindriven.persistance.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by donghoon on 2016. 5. 31..
 */
@Service
@Transactional
public class WorkerService implements IWorkerService {

    @Autowired
    private WorkerRepository repository;


    /**
     * mongodb save 함수는 upsert 로 동작한다.
     *
     * @param worker
     */
    @Override
    public Worker save(Worker worker) {
        if (worker == null)
            throw new NullPointerException("worker object is null...");

        return repository.save(worker);
    }

    @Override
    public List<Worker> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Worker> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Worker findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void deleteWorker(String id) {
        repository.delete(id);
    }

    @Override
    public Long count() {
        return repository.count();
    }
}
