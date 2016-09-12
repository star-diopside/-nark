package jp.gr.java_conf.star_diopside.nark.service;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.gr.java_conf.star_diopside.nark.data.entity.Authority;
import jp.gr.java_conf.star_diopside.nark.data.entity.User;
import jp.gr.java_conf.star_diopside.nark.data.repository.AuthorityRepository;
import jp.gr.java_conf.star_diopside.nark.data.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(String username, Supplier<String> password, String... authorities) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password.get()));
        user.setEnabled(true);
        userRepository.save(user);

        authorityRepository.save(Arrays.stream(authorities).map(auth -> {
            Authority authority = new Authority();
            authority.setUsername(username);
            authority.setAuthority(auth);
            return authority;
        }).collect(Collectors.toList()));
    }
}
