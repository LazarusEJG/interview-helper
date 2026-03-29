package com.model;

/**
 * Issues #81-82 added by Ranger Klauk
 */

/**
 * Temporary solution to get test to run for my computer, type this into temrinal:
 * C:\Users\range\.m2\wrapper\dists\apache-maven-3.6.3-bin\1iopthnavndlasol9gbrbg6bf2\apache-maven-3.6.3\bin\mvn.cmd clean test
 */

/**
+----------------------------------------------------------+--------------------------------------------------------------+
| Test                                                     | Reasoning                                                    |
+----------------------------------------------------------+--------------------------------------------------------------+
| comment with special characters                          | Content with special characters should be stored correctly   |
| very long comment content                                | Very large content should be stored and retrieved correctly  |
| all-zero UUID author                                     | UUID of all zeros should be stored and retrieved correctly   |
| two comments with same author                            | Multiple comments can have the same author UUID              |
| upvote after many downvotes                              | Upvoting after many downvotes correctly increments score by 1|
| upvote large number of times                             | Scores should accumulate correctly after many upvotes        |
| downvote after many upvotes                              | Downvoting after many upvotes correctly decrements score by 1|
| downvote large number of times                           | Scores should accumulate correctly after many downvotes      |
| report removed comment                                   | Removed comments can still be flagged                        |
| report flagged comment can still be removed              | Flagged comments can still be removed                        |
| remove removed comment can still be upvoted              | Removed comments can still be upvoted                        |
| remove removed comment can still receive replies         | Removed comments can still receive replies                   |
| add reply to reply nested correctly                      | Replies to replies should be nested correctly                |
| add large number of replies                              | Large number of replies should all be stored correctly       |
+----------------------------------------------------------+--------------------------------------------------------------+
*/

// AI assisted, see reasoning table above

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Unit tests for Comment
 * Tested by: Ranger Klauk
 */
public class CommentTest {

    private UUID authorId;
    private Comment comment;

    @Before
    public void setUp() {
        authorId = UUID.randomUUID();
        comment = new Comment(authorId, "This is a test comment.");
    }

    // ─── getContent ───────────────────────────────────────────────────────────

    @Test
    public void getContent_specialCharacters_storesCorrectly() {
        Comment special = new Comment(UUID.randomUUID(), "!@#$%^&*()<>?/\\|{}~`\n\t");
        assertEquals("!@#$%^&*()<>?/\\|{}~`\n\t", special.getContent());
    }

    @Test
    public void getContent_veryLongContent_storesCorrectly() {
        String longContent = "a".repeat(100000);
        Comment long_comment = new Comment(UUID.randomUUID(), longContent);
        assertEquals(longContent, long_comment.getContent());
    }

    // ─── getAuthor ────────────────────────────────────────────────────────────

    @Test
    public void getAuthor_allZerosUUID_storesCorrectly() {
        UUID zeroId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        Comment zeroComment = new Comment(zeroId, "content");
        assertEquals(zeroId, zeroComment.getAuthor());
    }

    @Test
    public void getAuthor_twoCommentsWithSameAuthor_bothMatch() {
        Comment comment2 = new Comment(authorId, "second comment");
        assertEquals(comment.getAuthor(), comment2.getAuthor());
    }

    // ─── upVote ───────────────────────────────────────────────────────────────

    @Test
    public void upVote_afterManyDownvotes_scoreIncreasesByOne() {
        for (int i = 0; i < 1000; i++) comment.downVote();
        comment.upVote();
        assertEquals(-999, comment.getScore());
    }

    @Test
    public void upVote_largeNumberOfTimes_scoresAccumulate() {
        for (int i = 0; i < 10000; i++) comment.upVote();
        assertEquals(10000, comment.getScore());
    }

    // ─── downVote ─────────────────────────────────────────────────────────────

    @Test
    public void downVote_afterManyUpvotes_scoreDecreasesByOne() {
        for (int i = 0; i < 1000; i++) comment.upVote();
        comment.downVote();
        assertEquals(999, comment.getScore());
    }

    @Test
    public void downVote_largeNumberOfTimes_scoresAccumulate() {
        for (int i = 0; i < 10000; i++) comment.downVote();
        assertEquals(-10000, comment.getScore());
    }

    // ─── report ───────────────────────────────────────────────────────────────

    @Test
    public void report_removedComment_canStillBeFlagged() {
        comment.remove();
        comment.report();
        assertTrue(comment.isFlagged());
    }

    @Test
    public void report_flaggedCommentCanStillBeRemoved() {
        comment.report();
        comment.remove();
        assertTrue(comment.isRemoved() && comment.isFlagged());
    }

    // ─── remove ───────────────────────────────────────────────────────────────

    @Test
    public void remove_removedComment_canStillBeUpvoted() {
        comment.remove();
        comment.upVote();
        assertEquals(1, comment.getScore());
    }

    @Test
    public void remove_removedComment_canStillReceiveReplies() {
        comment.remove();
        comment.addComment(new Comment(UUID.randomUUID(), "reply to removed comment"));
        assertEquals(1, comment.getReplies().size());
    }

    // ─── addComment ───────────────────────────────────────────────────────────

    @Test
    public void addComment_replyToReply_nestedCorrectly() {
        Comment reply = new Comment(UUID.randomUUID(), "first reply");
        Comment nestedReply = new Comment(UUID.randomUUID(), "nested reply");
        reply.addComment(nestedReply);
        comment.addComment(reply);
        assertEquals("nested reply", comment.getReplies().get(0).getReplies().get(0).getContent());
    }

    @Test
    public void addComment_largeNumberOfReplies_allStored() {
        for (int i = 0; i < 10000; i++) {
            comment.addComment(new Comment(UUID.randomUUID(), "reply " + i));
        }
        assertEquals(10000, comment.getReplies().size());
    }
}