
package com.yoshida.artistartwork.entity;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "anonymous_message_board")
public class AnonymousMessageBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Message content from anonymous users
     * Supports emoji and Japanese kaomoji: (´∀`) ヽ(´▽`)ノ 😊💖
     * Character limit: 2000 (reasonable for message board posts)
     */
    @Column(nullable = false, length = 2000)
    @Size(min = 1, max = 2000, message = "Message content must be between 1 and 2000 characters")
    @NotBlank(message = "Message content cannot be blank")
    private String messageContent;

    /**
     * Anonymous identifier (IP address or generated ID)
     * Used for tracking and basic moderation
     */
    @Column(nullable = false, length = 255)
    private String anonymousIdentifier;

    /**
     * Reserved for future user registration system
     * Will be used when user login functionality is implemented
     */
    @Column
    private Integer userId; // Reserved for Stage 3

    /**
     * Parent message ID for threaded replies
     * NULL for root messages, points to parent for replies
     * Enables artist to reply to specific messages
     */
    @Column
    private Integer parentMessageId; // For artist replies

    /**
     * Message status for content moderation
     * - pending: Awaiting review (default for new messages)
     * - visible: Approved and displayed publicly  
     * - hidden: Moderated/hidden from public view
     */
    @Column(nullable = false, length = 15)
    private String status = "pending"; // pending, visible, hidden

    /**
     * Timezone-aware, automatically managed by database
     */
    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createTime;

    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime updateTime;
}