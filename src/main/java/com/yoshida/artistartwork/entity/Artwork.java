package com.yoshida.artistartwork.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "artwork")
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private Integer categoryId; // Logical Foreign Key

    /**
     * Artwork image url
     */
    @Column(nullable = false, length = 255)
    private String imageUrl;

    /*
     * Thumbnail image path
     */
    @Column(length = 255)
    private String thumbnail;

    @Column(nullable = false)
    private Integer likeCount = 0;

    @Column(length = 500)
    private String description; // Artwork description

    @Column(nullable = false)
    private Boolean isNsfw = false; // 1 NSFW 0 Safe

    @Column(nullable = false)
    private Integer status = 1; // 1 Visible 0 Hidden

    /**
     * Timezone-aware, automatically set on insert/update
     */
    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createTime;

    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime updateTime;
}
