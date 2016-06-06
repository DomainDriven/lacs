package kr.domaindriven.service;

import kr.domaindriven.model.*;
import kr.domaindriven.persistance.SeminarRepository;
import kr.domaindriven.persistance.SubtaskRepository;
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

    @Autowired
    private SubtaskRepository subtaskRepository;

    public SeminarService() {
    }

    /**
     * mongodb save 함수는 upsert 로 동작한다.
     *
     * @param seminar
     */
    @Override
    public Seminar save(Seminar seminar) {
        if (seminar == null){
            throw new NullPointerException("seminar object is null...");}
        return repository.save(seminar);
    }

    public Seminar save(Seminar seminar, Task task) {
        if (seminar == null || task ==null){
            throw new NullPointerException("seminar object is null...");}
        subTaskSave(seminar.getId(),task);
        return save(seminar);
    }

    //SubTask를 추가 및 현재 세미나 id를 넣기 위한 메소드
    public void subTaskSave(String seminarId, Task task){
       //강사 섭외일때.
      //  if(task.getTaskName().equals(LacsCnst.CAST_INSTRUCTOR)){
            CastingInstructor castingInstructor = (CastingInstructor) task;
            castingInstructor.setSeminarId(seminarId);
            Subtask <CastingInstructor> subtask = new <CastingInstructor> Subtask();
            subtask.setTask(castingInstructor);
            subtaskRepository.save(subtask);
    //    }
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
