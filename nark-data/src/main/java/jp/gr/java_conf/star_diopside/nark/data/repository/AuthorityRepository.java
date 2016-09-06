package jp.gr.java_conf.star_diopside.nark.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.gr.java_conf.star_diopside.nark.data.entity.Authority;
import jp.gr.java_conf.star_diopside.nark.data.entity.AuthorityId;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {
}
