package com.yoshida.artistartwork.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name = "yoshida";

    @Column(nullable = false, length = 64)
    private String password = "123456";

    //Split bio into two fields

    //shortBio (500 chars) for homepage(ARTIST) brief intro
    @Column(name = "shortBio", length = 500)
    private String shortBio;

    /**
     * bio (2000 chars) for detailed About page
     */
    @Column(name = "bio", length = 2000)
    private String bio;

    /**
     * must have an email for homepage(CONTACT) and About page
     */
    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 255)
    private String avatar;

    // Purpose:
    // - Backend management: To display account creation timestamps
    // - Data auditing: To track modification times of user data
    // - Debugging: To assist in troubleshooting and issue tracking

    /**
     * Timezone-aware
     */
    @Column(nullable = false)
    private OffsetDateTime createTime;

    @Column(nullable = false)
    private OffsetDateTime updateTime;
}