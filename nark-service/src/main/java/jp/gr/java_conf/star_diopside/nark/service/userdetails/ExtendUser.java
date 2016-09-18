package jp.gr.java_conf.star_diopside.nark.service.userdetails;

import java.time.LocalDateTime;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import jp.gr.java_conf.star_diopside.nark.data.entity.LoginUser;
import jp.gr.java_conf.star_diopside.nark.data.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class ExtendUser extends org.springframework.security.core.userdetails.User implements ExtendUserDetails {

    private String displayName;

    private LocalDateTime lastLoginAt;

    private LocalDateTime logoutAt;

    public ExtendUser(User user) {
        super(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true,
                AuthorityUtils.NO_AUTHORITIES);
        this.displayName = user.getDisplayName();
        LoginUser loginUser = user.getLoginUser();
        if (loginUser != null) {
            this.lastLoginAt = loginUser.getLastLoginAt();
            this.logoutAt = loginUser.getLogoutAt();
        }
    }

    public ExtendUser(UserDetails user, ExtendUserDetails extendUser) {
        super(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
                user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getAuthorities());
        this.displayName = extendUser.getDisplayName();
        this.lastLoginAt = extendUser.getLastLoginAt();
        this.logoutAt = extendUser.getLogoutAt();
    }
}
