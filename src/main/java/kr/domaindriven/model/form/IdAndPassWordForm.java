package kr.domaindriven.model.form;

/**
 * 이 클래스는 클라이언트의 인증을 위한 아이디와 비밀번호를 전달하기 위한 폼 클래스 입니다.
 * 현재 클라이언트의 상태를 서버에게 하이퍼미디어로 전송하기 위한 클래스입니다.
 * 멤버변수로 ID와 PASSWORD가 String Type으로 존재합니다.
 * Created by jerry on 2016-08-14.
 */
public class IdAndPassWordForm {
    private String id;
    private String password;

    public IdAndPassWordForm() {
    }

    public IdAndPassWordForm(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "IdAndPassWordForm{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
