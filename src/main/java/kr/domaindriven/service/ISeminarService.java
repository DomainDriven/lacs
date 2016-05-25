package kr.domaindriven.service;

import kr.domaindriven.model.Seminar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by donghoon on 2016. 5. 26..
 */
public interface ISeminarService {

    Seminar save(Seminar seminar);

    Page<Seminar> findAll(Pageable pageable);

    Long count();
}
