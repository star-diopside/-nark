package jp.gr.java_conf.star_diopside.nark.web.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import jp.gr.java_conf.star_diopside.nark.service.UserService;
import jp.gr.java_conf.star_diopside.nark.service.userdetails.ExtendUserDetails;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        userService.loginSuccess((ExtendUserDetails) event.getAuthentication().getPrincipal());
    }
}
