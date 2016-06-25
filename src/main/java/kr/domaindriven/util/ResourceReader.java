package kr.domaindriven.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Created by donghoon on 2016. 6. 25..
 *
 * file 처리를 위한 util class.
 */
@Component
public class ResourceReader implements ResourceLoaderAware {
    private static final Logger logger = LoggerFactory.getLogger(ResourceReader.class);

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource getResource(String location) {
        return resourceLoader.getResource(location);
    }
}
