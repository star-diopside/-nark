package jp.gr.java_conf.star_diopside.nark.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

@Configuration
public class AppConfig {

    @Autowired
    private MessageSource messageSource;

    @Bean
    public MessageSourceAccessor messages() {
        return new MessageSourceAccessor(messageSource);
    }
}
