package kr.domaindriven.service;

/**
 * Created by jerry on 2016-08-17.\
 * 로그인을 위한 서비스입니다.
 * 해당 정보의 인증을 위해 사용됩니다.
 * isOurUser 메소드를 사용하여 해당 서비스의 User 유무를 확인합니다.
 */
public interface ILoginService<T> {
    Boolean isOurUser(T t);
}
