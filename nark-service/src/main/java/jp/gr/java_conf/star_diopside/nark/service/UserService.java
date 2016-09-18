package jp.gr.java_conf.star_diopside.nark.service;

import java.util.function.Supplier;

import jp.gr.java_conf.star_diopside.nark.service.userdetails.ExtendUserDetails;

public interface UserService {

    void create(String username, Supplier<String> password, String displayName, String... authorities);

    void loginSuccess(ExtendUserDetails user);

    void loginFailure(String username);

    void logout(ExtendUserDetails user);

}
