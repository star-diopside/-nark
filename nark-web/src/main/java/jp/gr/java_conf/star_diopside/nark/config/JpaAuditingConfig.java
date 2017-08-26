package jp.gr.java_conf.star_diopside.nark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jp.gr.java_conf.star_diopside.nark.data.domain.SecurityContextAuditorAware;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new SecurityContextAuditorAware();
    }
}
