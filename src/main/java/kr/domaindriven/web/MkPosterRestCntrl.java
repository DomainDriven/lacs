package kr.domaindriven.web;

import kr.domaindriven.model.PosterResource;
import kr.domaindriven.model.Seminar;
import kr.domaindriven.model.Task;
import kr.domaindriven.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jerry on 2016-07-13.
 */
@RestController
public class MkPosterRestCntrl {

    @Autowired
    private SeminarService seminarService;

    @RequestMapping(value = "/updatePosterTask", method = RequestMethod.POST)
    public void updatePosterTask(@RequestParam("progress") int progress) {
        // TODO: 2016-07-13 완료되지 않은 세미나를 동시에 두개 이상 병렬로 진행 할 때 문제가 될 것 같음 - 재열
        Seminar seminar = seminarService.findByIsCompleted(false);
        seminar.getTasks().get(2).setProgress(progress);
        seminarService.save(seminar);
    }

    @RequestMapping(value = "/uploadPosterResourceByRest", method = RequestMethod.POST)
    public void uploadPosterResourceByRest(@RequestParam("inputFile") String inputFile, @RequestParam("title") String title) {
        Seminar seminar = seminarService.findByTitle(title);
        List<PosterResource> posterResources = seminar.getTasks().get(2).getPosterResources();
        String fileName = inputFile;

        if (posterResources.get(0).getResourcesName().equals("포스터 자료 없음")) {
            posterResources.set(0, new PosterResource(fileName,false));
        } else {
            int indexNum = 0;
            for(PosterResource pr:posterResources){
                if(pr.getResourcesName().equals(inputFile)){
                    break;
                }
                indexNum++;
            }
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@"+indexNum);
            if(posterResources.size()!=indexNum){
                posterResources.remove(indexNum);}
            posterResources.add(new PosterResource(fileName,false));
        }
        seminarService.save(seminar);
    }

    @RequestMapping(value = "/deletePosterResourceByRest", method = RequestMethod.POST)
    public void deletePosterResourceByRest(@RequestParam("fileIndex") int fileIndex, @RequestParam("title") String title) {
        Seminar seminar = seminarService.findByTitle(title);
        Task makingPoster = seminar.getTasks().get(2);
        makingPoster.getPosterResources().get(fileIndex).setIsDeleted(true);
        seminarService.save(seminar);
    }

}
