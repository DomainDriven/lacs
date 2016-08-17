package kr.domaindriven.service;

import kr.domaindriven.model.form.IdAndPassWordForm;
import kr.domaindriven.util.FacebookAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jerry on 2016-08-17.
 * 로그인 서비스 구현체 입니다.
 * isOurUser 메소드는 Argument의 Class에 따라서 알맞게 오버로딩된 메소드를 사용합니다.
 */
@Service
public class LoginService<T> implements ILoginService {

    @Autowired
    WorkerService workerService;

    @Override
    public Boolean isOurUser(Object o) {
        if(o instanceof IdAndPassWordForm){
           return isOurUserBasic((IdAndPassWordForm) o);
        }else if(o instanceof FacebookAuth){
           return isOurUserFaceBook((FacebookAuth) o);
        }else{
        return null;}
    }

    private Boolean isOurUserBasic(IdAndPassWordForm idAndPassWord) {
        if ((idAndPassWord.getId().equals("test@test.com")) && (idAndPassWord.getPassword().equals("1234"))) {
            return true;
        } else {
            return false;
        }
    }
    /*
    * 엑세스코드를 통해 디비로부터 해당 계정이 존재하면 true, 아니면 false
    * */
    private Boolean isOurUserFaceBook(FacebookAuth facebookAuth) {
        //재열이의 계정 MD 값 (id+email)
        //6286af825faee64657edbd10dccc0743c5ea74ec12d8e0a7d36c9a42843d373a896c3e81ca804289c1732744c86c70d642e9cebd2ed522bdd86a4956535f60fe
        String accessCode =facebookAuth.getAuthInfo(); //엑세스코드
        if (workerService.findByAccessCode(accessCode) != null) {
            System.out.println(workerService.findByAccessCode(accessCode).getName()+"페이스북 SSO로 로그인");
            return true;
        } else {
            return false;
        }
    }


}
