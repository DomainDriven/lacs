package kr.domaindriven.service;

import java.io.IOException;

/**
 * Created by donghoon on 2016. 6. 25..
 */
public interface IFileService {

    void insertJsonFile(String location, String collection) throws IOException;
}
