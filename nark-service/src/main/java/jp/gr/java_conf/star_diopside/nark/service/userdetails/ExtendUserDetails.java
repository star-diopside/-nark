package jp.gr.java_conf.star_diopside.nark.service.userdetails;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;

public interface ExtendUserDetails extends UserDetails {

    String getDisplayName();

    void setDisplayName(String displayName);

    LocalDateTime getLastLoginAt();

    void setLastLoginAt(LocalDateTime lastLoginAt);

    LocalDateTime getLogoutAt();

    void setLogoutAt(LocalDateTime logoutAt);

}
