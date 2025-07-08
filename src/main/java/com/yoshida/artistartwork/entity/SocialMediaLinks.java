package com.yoshida.artistartwork.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "social_media_links")
public class SocialMediaLinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String platform; // Twitter, Instagram, etc

    @Column(nullable = false, length = 255)
    private String url;

    @Column(length = 255)
    private String icon;

    @Column(nullable = false)
    private Integer sort = 0;

    @Column(nullable = false)
    private Integer status = 1; // 1 Active 0 Inactive

    /**
     * Timezone-aware
     */
    @Column(nullable = false)
    private OffsetDateTime createTime;

    @Column(nullable = false)
    private OffsetDateTime updateTime;

}