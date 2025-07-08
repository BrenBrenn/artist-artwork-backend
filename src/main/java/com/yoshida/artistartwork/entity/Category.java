package com.yoshida.artistartwork.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 255)
    private String image;

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

    @Column(nullable = false)
    private Integer status = 1; // 1 Enabled 0 Disabled

    /**
     * Timezone-aware, automatically set on insert/update
     */
    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createTime;

    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime updateTime;
}