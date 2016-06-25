package kr.domaindriven.util;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by donghoon on 2016. 6. 25..
 * <p/>
 * 컨트롤러 클래스에서 자주 사용하는 기능을 추출한 클래스 입니다.
 */
@Component
public class ControllerUtil {

    public void LoggingError(BindingResult result, Logger logger) {
        List<ObjectError> errorList = result.getAllErrors();
        for (ObjectError error : errorList) {
            logger.error(error.toString());
        }
    }
}
