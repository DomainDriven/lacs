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
        if (seminar == null) {
            throw new NullPointerException("seminar object is null...");
        }
        return repository.save(seminar);
    }

    //세미나와 하위 task의 상태를 함께 저장함.
    public Seminar save(Seminar seminar, Task task) {
        if (seminar == null || task == null) {
            throw new NullPointerException("seminar object is null...");
        }
        subTaskSave(seminar.getId(), task);
        return save(seminar);
    }

    // TODO: 2016-06-08 세부 태스크별 타입변환 클래스 혹은 메소드 필요 - Jerry
    //SubTask를 추가 및 현재 세미나 id를 넣기 위한 메소드
    public void subTaskSave(String seminarId, Task task) {
        //강사 섭외일때.
//        if(task.getTaskName().equals(LacsCnstE.CAST_INSTRUCTOR.getTaskName())){
        CastingInstructor castingInstructor = (CastingInstructor) task;
        castingInstructor.setSeminarId(seminarId);
        Subtask<CastingInstructor> subtask = new <CastingInstructor>Subtask();
        subtask.setTask(castingInstructor);
        subtaskRepository.save(subtask);
        //    }
    }

    //현재 세미나의 서브 테스크를 조회 및 반환하는 기능
    public Task findSubtesk(String seminarID, String subTaskName){
        //강사섭외일때
        Subtask castingInstructor = subtaskRepository.castingInstructor_findBySeminarIDWithTaskName(seminarID,subTaskName);
        CastingInstructor task = (CastingInstructor) castingInstructor.getTask();
        return task;
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
