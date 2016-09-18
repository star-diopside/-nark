package jp.gr.java_conf.star_diopside.nark.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.gr.java_conf.star_diopside.nark.data.entity.Authority;
import jp.gr.java_conf.star_diopside.nark.data.entity.LoginUser;
import jp.gr.java_conf.star_diopside.nark.data.entity.User;
import jp.gr.java_conf.star_diopside.nark.data.repository.AuthorityRepository;
import jp.gr.java_conf.star_diopside.nark.data.repository.LoginUserRepository;
import jp.gr.java_conf.star_diopside.nark.data.repository.UserRepository;
import jp.gr.java_conf.star_diopside.nark.service.userdetails.ExtendUserDetails;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginUserRepository loginUserRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void create(String username, Supplier<String> password, String displayName, String... authorities) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password.get()));
        user.setEnabled(true);
        user.setDisplayName(displayName);
        userRepository.save(user);

        authorityRepository.save(Arrays.stream(authorities).map(auth -> {
            Authority authority = new Authority();
            authority.setUsername(username);
            authority.setAuthority(auth);
            return authority;
        }).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public void loginSuccess(ExtendUserDetails user) {
        LoginUser loginUser = loginUserRepository.findOne(user.getUsername());

        if (loginUser == null) {
            loginUser = new LoginUser();
            loginUser.setUsername(user.getUsername());
        }

        loginUser.setLoginErrorCount(0);
        loginUser.setLastLoginAt(LocalDateTime.now());
        loginUser.setLogoutAt(null);

        loginUserRepository.save(loginUser);

        user.setLastLoginAt(loginUser.getLastLoginAt());
        user.setLogoutAt(loginUser.getLogoutAt());
    }

    @Override
    @Transactional
    public void loginFailure(String username) {
        LoginUser loginUser = loginUserRepository.findOne(username);

        if (loginUser == null) {
            loginUser = new LoginUser();
            loginUser.setUsername(username);
            loginUser.setLoginErrorCount(1);
        } else {
            loginUser.setLoginErrorCount(loginUser.getLoginErrorCount() + 1);
        }

        loginUserRepository.save(loginUser);
    }

    @Override
    @Transactional
    public void logout(ExtendUserDetails user) {
        LoginUser loginUser = loginUserRepository.findOne(user.getUsername());
        loginUser.setLogoutAt(LocalDateTime.now());
        loginUserRepository.save(loginUser);
    }
}
