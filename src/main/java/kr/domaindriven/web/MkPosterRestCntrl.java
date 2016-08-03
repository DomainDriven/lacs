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

/**
 * Created by jerry on 2016-07-13.
 */
@RestController
public class MkPosterRestCntrl {

    @Autowired
    SeminarService seminarService;

    @RequestMapping(value = "/updatePosterTask", method = RequestMethod.POST)
    public void updatePosterTask(@RequestParam("progress") int progress) {
        // TODO: 2016-07-13 완료되지 않은 세미나를 동시에 두개 이상 병렬로 진행 할 때 문제가 될 것 같음 - 재열
        Seminar seminar = seminarService.findByIsCompleted(false);
        seminar.getTasks().get(2).setProgress(progress);
        seminarService.save(seminar);
    }

    @RequestMapping(value = "/uploadPosterResourceByRest", method = RequestMethod.POST)
    public void uploadPosterResource(@RequestParam("inputFile") String inputFile, @RequestParam("title") String title) {
        Seminar seminar = seminarService.findByTitle(title);
        Task makingPoster = seminar.getTasks().get(2);
        String fileName = inputFile;
        if (makingPoster.getPosterResources().get(0).getResourcesName().equals("포스터 자료 없음")) {
            makingPoster.getPosterResources().set(0, new PosterResource(fileName,false));
        } else {
            makingPoster.getPosterResources().add(new PosterResource(fileName,false));
        }
        seminarService.save(seminar);
    }

    @RequestMapping(value = "/deletePosterResourceByRest", method = RequestMethod.POST)
    public void deletePosterResource(@RequestParam("fileIndex") int fileIndex, @RequestParam("title") String title) {
        Seminar seminar = seminarService.findByTitle(title);
        Task makingPoster = seminar.getTasks().get(2);
        makingPoster.getPosterResources().get(fileIndex).setDeleted(true);
        seminarService.save(seminar);
    }

}
