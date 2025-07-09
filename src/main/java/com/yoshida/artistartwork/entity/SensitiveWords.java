
package com.yoshida.artistartwork.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

/**
 * SENSITIVE WORDS FILTERING SYSTEM - BUSINESS LOGIC
 *
 * === WORKFLOW ===
 * 1. USER POSTS MESSAGE
 *    - User submits message via frontend form
 *    - Frontend does basic validation (length, not empty)
 *
 * 2. BACKEND RECEIVES & VALIDATES
 *    - Spring validation (@Size, @NotBlank) runs first
 *    - If validation passes, proceed to content filtering
 *
 * 3. SENSITIVE WORD DETECTION
 *    - Query all active sensitive words (status = 1)
 *    - Scan message content for matches (case-insensitive)
 *    - Group matches by action type: block/replace/flag
 *
 * 4. ACTION PROCESSING
 *    a) BLOCK: If any "block" words found
 *       → Reject entire message
 *       → Return error to user: "Message contains inappropriate content"
 *
 *    b) REPLACE: If "replace" words found
 *       → Substitute word with replacement text
 *       → Continue processing modified message
 *
 *    c) FLAG: If "flag" words found
 *       → Allow message to post
 *       → Set status = "pending" (requires manual review)
 *       → Notify artist for review
 *
 * 5. SAVE MESSAGE
 *    - Save processed message to database
 *    - Status: "visible" (normal) or "pending" (flagged)
 *
 * 6. FUTURE AI INTEGRATION HOOK
 *    - After keyword filtering, call AI service for advanced detection
 *    - AI can catch context-based issues keywords miss
 *    - Configurable: enable/disable AI second-pass filtering
 *
 * === CATEGORIES ===
 * - profanity: Offensive language → usually BLOCK
 * - spam: Repetitive/promotional content → BLOCK or FLAG
 * - inappropriate: Context-dependent → FLAG for review
 * - violence: Violent content → BLOCK
 * - adult: Adult content (for art site) → FLAG (manual review)
 *
 * === ARTIST MANAGEMENT ===
 * - Backend interface to add/edit/disable sensitive words
 * - Bulk import from external lists  (!Not sure yet)
 * - Statistics: how many messages caught by each word (!Not sure yet)
 * - Manual review queue for flagged messages
 */

@Data
@Entity
@Table(name = "sensitive_words")
public class SensitiveWords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The sensitive word or phrase to be filtered
     * Used for content moderation in:
     * - Anonymous message board posts (AnonymousMessageBoard.messageContent)
     * - Future comment system (when implemented)
     * - Future commission order requirements (CommissionOrders.requirements)
     * - Any user-generated text content that needs moderation
     */
    @Column(nullable = false, length = 200) //for English(~30 max?), Japanese words(~20 max?), short sentences, emoji
    private String word;

    /**
     * Category classification for the sensitive word
     * Examples:
     * - EN: profanity, spam, inappropriate, violence, adult
     * - JP: 不適切, スパム, 暴力, 成人向け
     * Helps with organized management and different handling rules
     */
    @Column(nullable = false, length = 30)
    private String category; // profanity, spam, inappropriate, violence, adult

    /**
     * Action to take when this word is detected
     * - block: Prevent the entire message from being posted
     * - replace: Replace the word with the replacement text
     * - flag: Allow posting but mark for manual review
     */
    @Column(nullable = false, length = 10)
    private String action; // block, replace, flag

    /**
     * Replacement text for "replace" action
     * If action is "replace", use this text to substitute the sensitive word
     * Can be asterisks, alternative words, or empty string
     */
    @Column(length = 50)
    private String replacement; // For replace action

    /**
     * Status of this sensitive word rule
     * 1 = Active (rule is enforced)
     * 0 = Inactive (rule is disabled)
     */
    @Column(nullable = false)
    private Integer status = 1; // 1 Active 0 Inactive

    /**
     * AI model to use for secondary content analysis
     * Examples: "toxic-bert", "roberta-hate-speech-detection", "perspective-api"
     * Used for advanced context-aware content moderation beyond keyword matching
     */
    @Column(length = 50)
    private String aiModel; // "toxic-bert", "roberta-hate-speech-detection", etc.

    /**
     * Enable AI-powered second-pass content filtering
     * When true, content that passes keyword filtering will be sent to AI for analysis
     * AI can detect context-based issues that keyword filtering might miss
     */
    @Column(nullable = false)
    private Boolean useAiSecondPass = false; // AI second-pass enabled: true=enabled, false=disabled (default)

    /**
     * Timezone-aware, automatically managed by database
     */
    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createTime;

    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime updateTime;
}