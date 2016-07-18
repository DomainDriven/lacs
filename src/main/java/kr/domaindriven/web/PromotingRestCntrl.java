package kr.domaindriven.web;

import kr.domaindriven.model.Seminar;
import kr.domaindriven.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jerry on 2016-07-13.
 */
@RestController
public class PromotingRestCntrl {

    @Autowired
    SeminarService seminarService;

    @RequestMapping(value = "/deletePromotion",method = RequestMethod.POST)
    public void updatePosterTask(@RequestParam("indexNumber") int indexNumber, @RequestParam("title") String title){
        // TODO: 2016-07-13 완료되지 않은 세미나를 동시에 두개 이상 병렬로 진행 할 때 문제가 될 것 같음 - 재열
        Seminar seminar = seminarService.findByTitle(title);
        seminar.getTasks().get(4).getPromotingResources().remove(indexNumber);
        seminarService.save(seminar);
    }
    
}
