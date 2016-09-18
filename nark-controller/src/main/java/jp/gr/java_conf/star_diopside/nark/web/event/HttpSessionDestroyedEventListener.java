package jp.gr.java_conf.star_diopside.nark.web.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

import jp.gr.java_conf.star_diopside.nark.service.UserService;
import jp.gr.java_conf.star_diopside.nark.service.userdetails.ExtendUserDetails;

@Component
public class HttpSessionDestroyedEventListener implements ApplicationListener<HttpSessionDestroyedEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(HttpSessionDestroyedEvent event) {
        event.getSecurityContexts().stream().map(context -> context.getAuthentication().getPrincipal())
                .filter(principal -> principal instanceof ExtendUserDetails).map(ExtendUserDetails.class::cast)
                .forEach(userService::logout);
    }
}
