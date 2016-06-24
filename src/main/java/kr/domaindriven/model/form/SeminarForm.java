package kr.domaindriven.model.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by donghoon on 2016. 5. 29..
 * 이 클래스는 세미나추가 폼에서 전달 받은 데이터를 위해 만들었음.
 */
public class SeminarForm {

    @NotNull
    @Size(min = 2, max = 100)
    private String title;
    @NotNull
    private Date date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
