package jp.gr.java_conf.star_diopside.nark.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.gr.java_conf.star_diopside.nark.data.entity.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, String> {
}
