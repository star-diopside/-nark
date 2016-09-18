package jp.gr.java_conf.star_diopside.nark.web.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jp.gr.java_conf.star_diopside.nark.service.UserService;

@Component
public class AuthenticationFailureBadCredentialsEventListener
        implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        if (event.getException() instanceof UsernameNotFoundException) {
            return;
        }
        userService.loginFailure(event.getAuthentication().getName());
    }
}
