package jp.gr.java_conf.star_diopside.nark.data.support;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class TrackableListener {

    @PrePersist
    public void changeCreated(Trackable entity) {
        String username = principalName();
        LocalDateTime current = LocalDateTime.now();
        entity.setCreatedAt(current);
        entity.setCreatedBy(username);
        entity.setUpdatedAt(current);
        entity.setUpdatedBy(username);
    }

    @PreUpdate
    public void changeUpdated(Trackable entity) {
        String username = principalName();
        LocalDateTime current = LocalDateTime.now();
        entity.setUpdatedAt(current);
        entity.setUpdatedBy(username);
    }

    private String principalName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null ? null : authentication.getName();
    }
}
