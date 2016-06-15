package kr.domaindriven.model.form;

/**
 * Created by jerry on 2016-06-15.
 * 강사선정시 CastringInstructor.html 로부터 값을 전달받을 때 사용하는 Form 입니다.
 */
public class CastingInstructorForm {

    private String selectedInstructor;
    private String selectedWorker;
    private String Subject;
    private String Date;

    public String getSelectedInstructor() {
        return selectedInstructor;
    }

    public void setSelectedInstructor(String selectedInstructor) {
        this.selectedInstructor = selectedInstructor;
    }

    public String getSelectedWorker() {
        return selectedWorker;
    }

    public void setSelectedWorker(String selectedWorker) {
        this.selectedWorker = selectedWorker;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public CastingInstructorForm(){}

    public CastingInstructorForm(String selectedInstructor, String selectedWorker, String subject, String date) {
        this.selectedInstructor = selectedInstructor;
        this.selectedWorker = selectedWorker;
        Subject = subject;
        Date = date;
    }

    @Override
    public String toString() {
        return "CastingInstructorForm{" +
                "selectedInstructor='" + selectedInstructor + '\'' +
                ", selectedWorker='" + selectedWorker + '\'' +
                ", Subject='" + Subject + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }

}
