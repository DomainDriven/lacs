package kr.domaindriven.model;


import java.util.List;

/**
 * Created by jerry on 2016-06-06.
 */
public class CastingInstructor extends Task implements ICastingInstructor {

    private String selectedInstructor;
    private String selectedWorker;
    private String account;
    private String phone;
    private String subject;
    private String date;
    private String file;

    public CastingInstructor() {
        super();
        this.setTaskName(LacsCnstE.CAST_INSTRUCTOR.getTaskName());
    }

    public CastingInstructor(String TaskName, List<Worker> worker){
        super(TaskName,worker);
    }

    public CastingInstructor(kr.domaindriven.model.SelectedInstructorForm selectedInstructorForm){
        this();
        this.selectedInstructor = selectedInstructorForm.getSelectedInstructor();
        this.selectedWorker = selectedInstructorForm.getSelectedWorker();
        this.account = selectedInstructorForm.getAccount();
        this.phone = selectedInstructorForm.getPhone();
        this.subject = selectedInstructorForm.getSubject();
        this.date = selectedInstructorForm.getDate();
        this.file = selectedInstructorForm.getFile();
        // TODO: 2016-06-08 TASK의 생성자 추가해야함 - Jerry
        this.setProgress(selectedInstructorForm.getProgress());
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
        return "CastingInstructor{" +
                "selectedInstructor='" + selectedInstructor + '\'' +
                ", selectedWorker='" + selectedWorker + '\'' +
                ", account='" + account + '\'' +
                ", phone='" + phone + '\'' +
                ", subject='" + subject + '\'' +
                ", date='" + date + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}
