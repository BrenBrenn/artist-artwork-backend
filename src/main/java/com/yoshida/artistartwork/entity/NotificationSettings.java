package com.yoshida.artistartwork.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "notification_settings")
public class NotificationSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String notificationType; // like_backend, comment_backend, comment_email, comment_line

    @Column(nullable = false)
    private Boolean isEnabled = false; // 1 Yes 0 No, default is 0 (disabled)

    /**
     * Timezone-aware
     */
    @Column(nullable = false)
    private OffsetDateTime createTime;

    @Column(nullable = false)
    private OffsetDateTime updateTime;
}