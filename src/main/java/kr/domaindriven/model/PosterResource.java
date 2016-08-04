package kr.domaindriven.model;

/**
 * 포스트 자원을 위한 모델
 * Created by jerry on 2016-08-03.
 */
public class PosterResource {
    private String resourcesName;
    private Boolean isDeleted;

    public PosterResource(){}

    public PosterResource(String resourcesName, Boolean isDeleted) {
        this.resourcesName = resourcesName;
        this.isDeleted = isDeleted;
    }

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
