package jp.gr.java_conf.star_diopside.nark.data.support;

import java.time.LocalDateTime;

public interface Trackable {

    void setCreatedAt(LocalDateTime createdAt);

    void setCreatedBy(String createdBy);

    void setUpdatedAt(LocalDateTime updatedAt);

    void setUpdatedBy(String updatedBy);

}
