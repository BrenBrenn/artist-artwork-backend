package com.yoshida.artistartwork.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "comic_series_artwork")
public class ComicSeriesArtwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer seriesId; // Logical Foreign Key → comic_series.id

    @Column(nullable = false)
    private Integer artworkId; // Logical Foreign Key → artwork.id

    /**
     * Display order within the series
     * Lower numbers appear first (0, 1, 2, 3, etc.)
     */
    @Column(nullable = false)
    private Integer sortOrder = 0; // Order in Series

    /**
     * Timezone-aware, automatically managed by database
     */
    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createTime;
}