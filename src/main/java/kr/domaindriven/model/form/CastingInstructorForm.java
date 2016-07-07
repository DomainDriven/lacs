package kr.domaindriven.model.form;

/**
 * Created by jerry on 2016-06-15.
 * 강사선정시 CastringInstructorForm.html 로부터 값을 전달받을 때 사용하는 Form 입니다.
 */
public class CastingInstructorForm {

    private String instructorName;
    private String workerName;
    private String subject;
    private String date;
    private String progress;

    //get(set)+document.elementById로 타임리프는 객체생성
    public String getSelectedInstructor() {
        return instructorName;
    }

    public void setSelectedInstructor(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getSelectedWorker() {
        return workerName;
    }

    public void setSelectedWorker(String workerName) {
        this.workerName = workerName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "CastingInstructorForm{" +
                "instructorName='" + instructorName + '\'' +
                ", workerName='" + workerName + '\'' +
                ", subject='" + subject + '\'' +
                ", date='" + date + '\'' +
                ", progress='" + progress + '\'' +
                '}';
    }

}
