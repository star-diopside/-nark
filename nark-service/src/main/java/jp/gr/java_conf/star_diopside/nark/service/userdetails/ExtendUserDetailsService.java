package jp.gr.java_conf.star_diopside.nark.service.userdetails;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

import jp.gr.java_conf.star_diopside.nark.data.entity.User;
import jp.gr.java_conf.star_diopside.nark.data.repository.AuthorityRepository;
import jp.gr.java_conf.star_diopside.nark.data.repository.UserRepository;

@Service
public class ExtendUserDetailsService extends JdbcDaoImpl {

    @Resource
    private DataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    protected void checkDaoConfig() {
        setDataSource(dataSource);
        super.checkDaoConfig();
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        User user = userRepository.findOne(username);
        return user == null ? Collections.emptyList() : Collections.singletonList(new ExtendUser(user));
    }

    @Override
    protected List<GrantedAuthority> loadUserAuthorities(String username) {
        return authorityRepository.findByUsername(username).stream()
                .map(authority -> new SimpleGrantedAuthority(getRolePrefix() + authority.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
            List<GrantedAuthority> combinedAuthorities) {
        return new ExtendUser(super.createUserDetails(username, userFromUserQuery, combinedAuthorities),
                (ExtendUserDetails) userFromUserQuery);
    }
}
