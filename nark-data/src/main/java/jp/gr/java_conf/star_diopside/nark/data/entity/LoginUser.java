package jp.gr.java_conf.star_diopside.nark.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import jp.gr.java_conf.star_diopside.nark.data.support.Trackable;
import jp.gr.java_conf.star_diopside.nark.data.support.TrackableListener;
import jp.gr.java_conf.star_diopside.spark.commons.data.converter.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "user")
@Entity
@EntityListeners(TrackableListener.class)
@Table(name = "login_users")
@SuppressWarnings("serial")
public class LoginUser implements Trackable, Serializable {

    @Id
    private String username;

    @Column(name = "login_error_count")
    private int loginErrorCount;

    @Column(name = "lockout_at")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime lockoutAt;

    @Column(name = "last_login_at")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime lastLoginAt;

    @Column(name = "logout_at")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime logoutAt;

    @Column(name = "created_at")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Version
    private int version;

    @OneToOne
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private User user;

}
