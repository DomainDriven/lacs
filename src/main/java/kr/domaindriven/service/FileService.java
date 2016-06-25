package kr.domaindriven.service;

import kr.domaindriven.util.ResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by donghoon on 2016. 6. 25..
 */
@Service
public class FileService implements IFileService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ResourceReader reader;

    /**
     * resources/data 폴더의 json 파일을 읽어들여서 insert 하는 메소드.
     *
     * @param location
     * @param collection
     * @throws IOException
     */
    @Override
    public void insertJsonFile(String location, String collection) throws IOException {
        Resource resource = reader.getResource(location);

        InputStream is = resource.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = br.readLine()) != null) {
            mongoTemplate.insert(line, collection);
        }
        br.close();
    }
}
