package jp.gr.java_conf.star_diopside.nark.data.entity;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class AuthorityId implements Serializable {

    private String username;

    private String authority;

}
