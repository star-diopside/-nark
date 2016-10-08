package jp.gr.java_conf.star_diopside.nark.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import jp.gr.java_conf.star_diopside.nark.data.support.Trackable;
import jp.gr.java_conf.star_diopside.nark.data.support.TrackableListener;
import jp.gr.java_conf.star_diopside.silver.commons.data.converter.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "authorities")
@Entity
@EntityListeners(TrackableListener.class)
@Table(name = "users")
@SuppressWarnings("serial")
public class User implements Trackable, Serializable {

    @Id
    private String username;

    private String password;

    private boolean enabled;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "password_updated_at")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime passwordUpdatedAt;

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private LoginUser loginUser;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Authority> authorities;

}
