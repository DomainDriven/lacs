package kr.domaindriven.model.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by donghoon on 2016. 7. 24..
 * 이 클래스는 강사후보 추가 폼에서 전달 받은 데이터를 위해 만들었음.
 */
public class InstructorForm {

    @NotNull
    @Size(min = 2, max = 20)
    private String name;
    @NotNull
    @Size(min = 2)
    private String phoneNumber;
    @NotNull
    @Size(min = 2, max = 30)
    private String mail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
