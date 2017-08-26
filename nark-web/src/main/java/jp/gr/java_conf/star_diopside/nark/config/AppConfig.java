package jp.gr.java_conf.star_diopside.nark.config;

import java.util.Collections;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.NoRollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
@EntityScan(basePackages = { "jp.gr.java_conf.star_diopside.nark.data.entity",
        "org.springframework.data.jpa.convert.threeten" })
public class AppConfig {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public MessageSourceAccessor messages() {
        return new MessageSourceAccessor(messageSource);
    }

    @Bean
    public Advice txAdvice() {
        MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setName("*");
        transactionAttribute.setRollbackRules(
                Collections.singletonList(new NoRollbackRuleAttribute(AuthenticationException.class)));
        source.setTransactionAttribute(transactionAttribute);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("target(" + DaoAuthenticationProvider.class.getName() + ") && execution(* "
                + AuthenticationProvider.class.getName() + ".authenticate(" + Authentication.class.getName() + "))");
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
