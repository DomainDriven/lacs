package kr.domaindriven.service;

import kr.domaindriven.model.Instructor;
import kr.domaindriven.persistance.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by jerry on 2016-06-02.
 */
@Service
@Transactional
public class InstructorService implements IInstructorService {

    @Autowired
    InstructorRepository repository;

    /**
     * mongodb save 함수는 upsert 로 동작한다.
     *
     * @param Instructor
     */

    @Override
    public Instructor save(Instructor instructor) {

        if (instructor == null)
            throw new NullPointerException("instructor object is null...");
        return repository.save(instructor);
    }

    @Override
    public Instructor findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Page<Instructor> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Long count() {
        return repository.count();
    }

}
