package com.yoshida.artistartwork.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "comic_series")
public class ComicSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Comic series title
     *
     * Note: Not enforcing unique constraint to allow:
     * - Multiple series with similar names (e.g., "Practice Vol.1", "Practice Vol.2")
     * - Artist freedom in naming conventions
     * - Reuse of common titles like "Sketches", "Fan Art", etc.
     *
     * Use ID for unique identification in backend management.
     */
    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private Integer categoryId; // Logical Foreign Key

    @Column(length = 500)
    private String description;

    @Column(length = 255)
    private String coverImage;

    @Column(nullable = false)
    private Integer status = 1; // 1 Active 0 Inactive

    /**
     * Sort order for categories display sequence
     * Lower numbers appear first in frontend
     *
     * Recommended values: 10, 20, 30, 40, etc.
     * - Start from 10 and increment by 10
     * - Can allow easy insertion between existing categories
     * - Example: to insert between 10 and 20, use 15
     */
    @Column(nullable = false)
    private Integer sort = 10;

    /**
     * Timezone-aware, automatically set on insert/update
     */
    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createTime;

    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime updateTime;
}