package kr.domaindriven.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by jerry on 2016-05-24.
 * 강사섭외 작업을 위한 클래스.
 * Task를 상속받으며 강사섭외작업에 대한 정보를 제공한다.
 */
public class SelectedInstructorForm extends Task {

    private List<Instructor> instructors;

    //// TODO: 2016-05-24 아래 세가지 멤버는 리팩토링요구됨 - JERRY
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

    public SelectedInstructorForm(String name, List<Worker> workers, List<Instructor> instructors) {
        super(name, workers);
        this.instructors = instructors;
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

}