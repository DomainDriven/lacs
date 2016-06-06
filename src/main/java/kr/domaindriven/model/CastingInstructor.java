package kr.domaindriven.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by jerry on 2016-06-06.
 */
public class CastingInstructor extends Task {

    private String selectedInstructor;
    private String selectedWorker;
    private String account;
    private String phone;
    private String subject;
    private String date;
    private String file;

    public CastingInstructor() {
        super();
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
