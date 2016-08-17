package kr.domaindriven.service;

import kr.domaindriven.model.form.IdAndPassWordForm;
import org.springframework.stereotype.Service;

/**
 * Created by jerry on 2016-08-17.
 * 로그인 서비스 구현체 입니다.
 * isOurUser 메소드는 Argument의 Class에 따라서 알맞게 오버로딩된 메소드를 사용합니다.
 */
@Service
public class LoginService<T> implements ILoginService {

    @Override
    public Boolean isOurUser(Object o) {
        if(o instanceof IdAndPassWordForm){
            isOurUser(o);
        }
        return null;
    }

    public Boolean isOurUser(IdAndPassWordForm idAndPassWord) {
        if ((idAndPassWord.getId().equals("test@test.com")) && (idAndPassWord.getPassword().equals("1234"))) {
            return true;
        } else {
            return false;
        }
    }


}
