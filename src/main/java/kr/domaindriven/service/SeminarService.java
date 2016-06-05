package kr.domaindriven.service;

import kr.domaindriven.model.Seminar;
import kr.domaindriven.persistance.SeminarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by donghoon on 2016. 5. 26..
 */
@Service
@Transactional
public class SeminarService implements ISeminarService {

    @Autowired
    private SeminarRepository repository;

    public SeminarService(){
    }
    /**
     * mongodb save 함수는 upsert 로 동작한다.
     *
     * @param seminar
     */
    @Override
    public Seminar save(Seminar seminar) {
        if (seminar == null)
            throw new NullPointerException("seminar object is null...");
        return repository.save(seminar);
    }

    @Override
    public Page<Seminar> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Seminar findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public Seminar findByIsCompleted(boolean isCompleted) {
        return repository.findByIsCompleted(isCompleted);
    }

    @Override
    public Long count() {
        return repository.count();
    }

}
