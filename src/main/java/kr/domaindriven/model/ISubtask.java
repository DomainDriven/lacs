package kr.domaindriven.model;

import kr.domaindriven.model.Task;

import java.util.List;

/**
 * Created by jerry on 2016-06-06.
 * 이 인터페이스는 하위 작업들의 정보를 주고 받기위해 만들었습니다.
 * @param T 는 Task의 하위 클래스만 가능합니다.
 */
public interface ISubtask<T extends Task> {
   T getTask();
}
