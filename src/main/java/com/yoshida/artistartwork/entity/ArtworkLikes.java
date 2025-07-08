package com.yoshida.artistartwork.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "artwork_likes")
public class ArtworkLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer artworkId; // Logical Foreign Key

    /**
     * No need to register to like, one person can like multiple times at same artwork
     * Save the IP address of the liker for sample statistics, 
     * for checking how many times the same IP address has liked the same artwork
     */
    @Column(nullable = false, length = 45)  // IP address max 45 char (IPv6)
    private String anonymousIdentifier; // save IP address

    /**
     * Reserved for future registration
     * If the user is registered, save the user ID here
     * If not registered, this field will be null
     */
    @Column
    private Integer userId; // Reserved for future registration

    /**
     * Timezone-aware, automatically set on insert
     * No need to update the timestamp - records are never updated
     */
    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createTime;

}
