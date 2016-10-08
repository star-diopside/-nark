package jp.gr.java_conf.star_diopside.nark.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@ToString(exclude = "user")
@Entity
@EntityListeners(TrackableListener.class)
@Table(name = "authorities")
@IdClass(AuthorityId.class)
@SuppressWarnings("serial")
public class Authority implements Trackable, Serializable {

    @Id
    private String username;

    @Id
    private String authority;

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

    @ManyToOne
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private User user;

}
