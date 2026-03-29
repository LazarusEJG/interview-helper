package com.model;

/**
 * Issues #83 and 85 added by Ranger Klauk
 */

/**
 * Temporary solution to get test to run for my computer, type this into temrinal:
 * C:\Users\range\.m2\wrapper\dists\apache-maven-3.6.3-bin\1iopthnavndlasol9gbrbg6bf2\apache-maven-3.6.3\bin\mvn.cmd clean test
 */

/**
+-----------------------------------------------------------+---------------------------------------------------------------------------------+
| Test                                                      | Reasoning                                                                       |
+-----------------------------------------------------------+---------------------------------------------------------------------------------+
| explanation with special characters                       | Explanations with symbols, whitespace, and escape chars should store correctly  |
| very long explanation                                     | Extremely long explanations should store and retrieve correctly                 |
| filename with special characters                          | Filenames with spaces, parentheses, or symbols should store correctly           |
| empty filename                                            | Empty filenames should be allowed and stored correctly                          |
| new solution not verified by default                      | Solutions start unverified                                                      |
| manual verify then downvoted below 80                     | manually verified solutions currently lose verification if score drops below 80 |
| upvote exactly 80 times auto-verifies                     | Solutions auto-verify when score reaches the threshold                          |
| upvote 79 times does not auto-verify                      | Solutions just below the threshold should not auto-verify                       |
| downvote verified at 80 drops to 79 removes verification  | Verified solutions lose verification if score falls below threshold             |
| downvote large negative score                             | Very large negative scores should not crash the system                          |
| report verified solution can still be flagged             | Verified solutions can still be flagged                                         |
| report not flagged by default                             | Solutions are not flagged unless explicitly reported                            |
| remove removed solution can still be upvoted              | Removed solutions can still be upvoted                                          |
| remove removed solution can still be verified             | Removed solutions can still be verified                                         |
| add comment to removed solution                           | Removed solutions can still receive comments                                    |
| add large number of comments                              | Very large number of comments should all be stored correctly                    |
+-----------------------------------------------------------+---------------------------------------------------------------------------------+
*/

// AI assisted, see reasoning table above

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Unit tests for Solution
 * Tested by: Ranger Klauk
 */
public class SolutionTest {

    private UUID authorId;
    private Solution solution;

    @Before
    public void setUp() {
        authorId = UUID.randomUUID();
        solution = new Solution(authorId, "This is an explanation.", "solution.java");
    }

    // ─── getExplanation ───────────────────────────────────────────────────────

    @Test
    public void getExplanation_specialCharacters_storesCorrectly() {
        Solution special = new Solution(authorId, "!@#$%^&*()<>?/\\|{}~`\n\t", "file.java");
        assertEquals("!@#$%^&*()<>?/\\|{}~`\n\t", special.getExplanation());
    }

    @Test
    public void getExplanation_veryLongExplanation_storesCorrectly() {
        String longExplanation = "a".repeat(100000);
        Solution long_solution = new Solution(authorId, longExplanation, "file.java");
        assertEquals(longExplanation, long_solution.getExplanation());
    }

    // ─── getFile ──────────────────────────────────────────────────────────────

    @Test
    public void getFile_specialCharactersInFilename_storesCorrectly() {
        Solution special = new Solution(authorId, "explanation", "my file (1).java");
        assertEquals("my file (1).java", special.getFile());
    }

    @Test
    public void getFile_emptyFilename_storesCorrectly() {
        Solution empty = new Solution(authorId, "explanation", "");
        assertEquals("", empty.getFile());
    }

    // ─── isVerified / verify ──────────────────────────────────────────────────

    @Test
    public void isVerified_newSolution_isFalseByDefault() {
        assertFalse(solution.isVerified());
    }

    @Test
    public void verify_manualVerify_thenDownvotedBelow80_losesVerification() {
        solution.verify();
        assertTrue(solution.isVerified());
        solution.downVote();
        assertFalse(solution.isVerified());
    }

    // ─── upVote ───────────────────────────────────────────────────────────────

    @Test
    public void upVote_exactlyAt80_autoVerifies() {
        for (int i = 0; i < 80; i++) solution.upVote();
        assertTrue(solution.isVerified());
    }

    @Test
    public void upVote_79Times_doesNotAutoVerify() {
        for (int i = 0; i < 79; i++) solution.upVote();
        assertFalse(solution.isVerified());
    }

    // ─── downVote ─────────────────────────────────────────────────────────────

    @Test
    public void downVote_verifiedAt80_dropTo79_removesVerification() {
        for (int i = 0; i < 80; i++) solution.upVote();
        assertTrue(solution.isVerified());
        solution.downVote();
        assertFalse(solution.isVerified());
    }

    @Test
    public void downVote_largeNegativeScore_doesNotCrash() {
        for (int i = 0; i < 10000; i++) solution.downVote();
        assertEquals(-10000, solution.getScore());
    }

    // ─── report ───────────────────────────────────────────────────────────────

    @Test
    public void report_verifiedSolution_canStillBeFlagged() {
        solution.verify();
        solution.report();
        assertTrue(solution.isFlagged() && solution.isVerified());
    }

    @Test
    public void report_notFlaggedByDefault() {
        assertFalse(solution.isFlagged());
    }

    // ─── remove ───────────────────────────────────────────────────────────────

    @Test
    public void remove_removedSolution_canStillBeUpvoted() {
        solution.remove();
        solution.upVote();
        assertEquals(1, solution.getScore());
    }

    @Test
    public void remove_removedSolution_canStillBeVerified() {
        solution.remove();
        solution.verify();
        assertTrue(solution.isVerified());
    }

    // ─── addComment ───────────────────────────────────────────────────────────

    @Test
    public void addComment_removedSolution_canStillReceiveComments() {
        solution.remove();
        solution.addComment(new Comment(UUID.randomUUID(), "comment on removed solution"));
        assertEquals(1, solution.getReplies().size());
    }

    @Test
    public void addComment_largeNumberOfComments_allStored() {
        for (int i = 0; i < 10000; i++) {
            solution.addComment(new Comment(UUID.randomUUID(), "comment " + i));
        }
        assertEquals(10000, solution.getReplies().size());
    }
}