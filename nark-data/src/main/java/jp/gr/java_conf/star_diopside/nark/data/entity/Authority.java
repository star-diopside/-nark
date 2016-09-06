package jp.gr.java_conf.star_diopside.nark.data.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "user")
@Entity
@Table(name = "authorities")
@IdClass(AuthorityId.class)
@SuppressWarnings("serial")
public class Authority implements Serializable {

    @Id
    private String username;

    @Id
    private String authority;

    @ManyToOne
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private User user;

}
