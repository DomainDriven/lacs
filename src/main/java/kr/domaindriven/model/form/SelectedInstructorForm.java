package kr.domaindriven.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jerry on 2016-05-24.
 * 강사섭외 작업을 위한 클래스.
 * Task를 상속받으며 강사섭외작업에 대한 정보를 제공한다.
 */
public class SelectedInstructorForm extends CastingInstructor {

    private List<Instructor> instructors;

    //// TODO: 2016-05-24 아래 세가지 멤버는 리팩토링요구됨 - JERRY
    // TODO: 2016-06-08 상속을 받아서 아래의 클래스변수들은 삭제를 해도 되나, @NotNull 과 같은 유효성검증으로 인해 일단 나둠. - 재열
    // TODO: 2016-06-08 추후 Instroctor를 가져와서 사용 할 수 있도록 할 것.  - 재열
    @NotNull
    private String selectedInstructor;
    @NotNull
    private String selectedWorker;
    private String account;
    /////상위까지
    @NotNull
    @Size(min = 10, max = 13)
    private String phone;
    @NotNull
    private String subject;
    @NotNull
    private String date;
    private String file;

    public SelectedInstructorForm() {
        super();
    }

    public SelectedInstructorForm(String selectedInstructor,String selectedWorker, String date) {
        super();
    }

    public SelectedInstructorForm(CastingInstructor castingInstructor){
        super(castingInstructor.getTaskName(),new ArrayList<Worker>());
        this.selectedInstructor = castingInstructor.getSelectedInstructor();
        this.getWorkers().add(new Worker(castingInstructor.getSelectedWorker()));
        this.selectedWorker = castingInstructor.getSelectedWorker();
        this.account = castingInstructor.getAccount();
        this.phone = castingInstructor.getPhone();
        this.subject = castingInstructor.getSubject();
        this.date = castingInstructor.getDate();
        this.file = castingInstructor.getFile();
        // TODO: 2016-06-08 TASK의 생성자 추가해야함 - Jerry
        this.setProgress(castingInstructor.getProgress());
        this.instructors = new ArrayList<Instructor>();

        instructors.add(new Instructor(castingInstructor.getSelectedInstructor(),castingInstructor.getPhone(),"aaa@email.com"));

    }
    public SelectedInstructorForm(String taskName, List<Worker> workers, List<Instructor> instructors) {
        super(taskName, workers);
        this.instructors = instructors;
        this.setTaskName(LacsCnstE.CAST_INSTRUCTOR.getTaskName());
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSelectedWorker() {
        return selectedWorker;
    }

    public void setSelectedWorker(String selectedWorker) {
        this.selectedWorker = selectedWorker;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSelectedInstructor() {
        return selectedInstructor;
    }

    public void setSelectedInstructor(String selectedInstructor) {
        this.selectedInstructor = selectedInstructor;
    }

    @Override
    public String toString() {
        return "SelectedInstructorForm{" +
                "instructors=" + instructors +
                ", selectedInstructor='" + selectedInstructor + '\'' +
                ", selectedWorker='" + selectedWorker + '\'' +
                ", account='" + account + '\'' +
                ", phone='" + phone + '\'' +
                ", subject='" + subject + '\'' +
                ", date='" + date + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}