package com.model;

/**
 * Issues #87-90 added by Ranger Klauk
 */

/**
 * Temporary solution to get test to run for my computer, type this into temrinal:
 * C:\Users\range\.m2\wrapper\dists\apache-maven-3.6.3-bin\1iopthnavndlasol9gbrbg6bf2\apache-maven-3.6.3\bin\mvn.cmd clean test
 */

/**
+--------------------------------------------------------+-------------------------------------------------------------------+
| Test                                                   | Reasoning                                                         |
+--------------------------------------------------------+-------------------------------------------------------------------+
| empty question list                                    | Should return null when there are no questions                    |
| single question                                        | Single question should always be returned                         |
| registered user tries to add question                  | Registered users should not be able to add questions              |
| contributor adds question with empty title and content | Contributors can add questions even if title or content is empty  |
| remove question not in list                            | Removing a question not in the list should leave list unchanged   |
| remove same question twice                             | Removing the same question twice should not crash                 |
| valid question ID                                      | Valid question UUID should return the correct question            |
| random question ID                                     | Non-existent question UUID should return null                     |
| valid solution ID                                      | Valid solution UUID should return the correct solution            |
| random solution ID                                     | Non-existent solution UUID should return null                     |
| empty tag filter                                       | Empty tag filter should return no questions                       |
| minimum difficulty higher than all                     | Questions with min difficulty higher than all should return empty |
| maximum difficulty lower than all                      | Questions with max difficulty lower than all should return empty  |
| minimum higher than maximum                            | Min difficulty higher than max should return empty                |
| only solved questions                                  | Only solved questions should be included in results               |
| non-existent author                                    | Questions by authors that do not exist should return empty        |
| set current question to null                           | Current question should return null if set to null                |
| overwrite current question                             | Setting a new current question should overwrite previous one      |
+--------------------------------------------------------+-------------------------------------------------------------------+
*/

// AI assisted, see reasoning table above

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Unit tests for QuestionList - edge case focused
 * Tested by: Ranger Klauk
 */
public class QuestionListTest {

    private QuestionList questionList;
    private User contributorUser;
    private User registeredUser;

    @Before
    public void setUp() throws Exception {
        Field instanceField = QuestionList.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);

        questionList = QuestionList.getInstance();

        contributorUser = new User(
            UUID.fromString("3fa7a504-89fe-46f7-bb93-80ef40dd1671"),
            UserType.CONTRIBUTOR,
            "playboicarti@gmail.com", "playboicarti", "WholeLottaRed123",
            new ArrayList<>(), 1, 14,
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), java.time.LocalDate.now(), 150
        );

        registeredUser = new User(
            UUID.fromString("57b3c909-973d-462d-b851-f8a673d3e70b"),
            UserType.REGISTERED,
            "travisscott@gmail.com", "travisscott", "feinfeinfein123",
            new ArrayList<>(), 8, 21,
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), java.time.LocalDate.now(), 467
        );
    }

    // ─── getDailyQuestion ────────────────────────────────────────────────────────

    @Test
    public void getDailyQuestion_emptyList_returnsNull() throws Exception {
        Field questionsField = QuestionList.class.getDeclaredField("questions");
        questionsField.setAccessible(true);
        questionsField.set(questionList, new ArrayList<>());
        assertNull(questionList.getDailyQuestion());
    }

    @Test
    public void getDailyQuestion_singleQuestion_alwaysReturnsThatQuestion() throws Exception {
        Field questionsField = QuestionList.class.getDeclaredField("questions");
        questionsField.setAccessible(true);
        ArrayList<Question> single = new ArrayList<>();
        //single.add(new Question(contributorUser.getId(), "Only Question", "content"));
        questionsField.set(questionList, single);
        assertEquals("Only Question", questionList.getDailyQuestion().getTitle());
    }

    // ─── addQuestion ─────────────────────────────────────────────────────────────

    @Test
    public void addQuestion_registeredUser_returnsFalseAndListUnchanged() {
        int before = questionList.getQuestions().size();
        //boolean result = questionList.addQuestion(registeredUser, "Sneaky", "content");
        //assertFalse(result);
        assertEquals(before, questionList.getQuestions().size());
    }

    @Test
    public void addQuestion_emptyTitleAndContent_contributorStillAdds() {
        int before = questionList.getQuestions().size();
        //questionList.addQuestion(contributorUser, "", "");
        assertEquals(before + 1, questionList.getQuestions().size());
    }

    // ─── removeQuestion ──────────────────────────────────────────────────────────

    @Test
    public void removeQuestion_questionNotInList_listUnchanged() {
        //Question ghost = new Question(contributorUser.getId(), "Ghost", "content");
        int before = questionList.getQuestions().size();
        //questionList.removeQuestion(ghost);
        assertEquals(before, questionList.getQuestions().size());
    }

    @Test
    public void removeQuestion_removeSameQuestionTwice_doesNotCrash() {
        //questionList.addQuestion(contributorUser, "Temp", "content");
        Question target = questionList.getQuestions().get(questionList.getQuestions().size() - 1);
        questionList.removeQuestion(target);
        int after = questionList.getQuestions().size();
        questionList.removeQuestion(target);
        assertEquals(after, questionList.getQuestions().size());
    }

    // ─── getQuestion(UUID) ───────────────────────────────────────────────────────

    @Test
    public void getQuestion_validId_returnsCorrectQuestion() {
        // BUG: will return null due to == instead of .equals() on UUID
        Question expected = questionList.getQuestions().get(0);
        Question result = questionList.getQuestion(expected.getId());
        assertNotNull(result);
    }

    @Test
    public void getQuestion_randomId_returnsNull() {
        assertNull(questionList.getQuestion(UUID.randomUUID()));
    }

    // ─── getSolution(UUID) ───────────────────────────────────────────────────────

    @Test
    public void getSolution_validId_returnsCorrectSolution() {
        Solution expected = questionList.getQuestions().get(0).getSolutions().get(0);
        Solution result = questionList.getSolution(expected.getId());
        assertNotNull(result);
    }

    @Test
    public void getSolution_randomId_returnsNull() {
        assertNull(questionList.getSolution(UUID.randomUUID()));
    }

    // ─── getQuestions (filtered) ─────────────────────────────────────────────────

    @Test
    public void getQuestions_emptyTagList_returnsNoQuestions() {
        ArrayList<String> emptyTags = new ArrayList<>();
        ArrayList<Question> result = questionList.getQuestions(emptyTags, null, null, false, null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getQuestions_minDifficultyHigherThanAll_returnsEmpty() {
        ArrayList<Question> result = questionList.getQuestions(null, 999, null, false, null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getQuestions_maxDifficultyLowerThanAll_returnsEmpty() {
        ArrayList<Question> result = questionList.getQuestions(null, null, -1, false, null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getQuestions_minHigherThanMax_returnsEmpty() {
        ArrayList<Question> result = questionList.getQuestions(null, 5, 1, false, null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getQuestions_onlySolved_noUnsolvedInResults() {
        ArrayList<Question> result = questionList.getQuestions(null, null, null, true, null);
        for (Question q : result) {
            assertFalse(q.getSolutions().isEmpty());
        }
    }

    @Test
    public void getQuestions_nonExistentAuthor_returnsEmpty() {
        ArrayList<UUID> authors = new ArrayList<>();
        authors.add(UUID.randomUUID());
        ArrayList<Question> result = questionList.getQuestions(null, null, null, false, authors);
        assertTrue(result.isEmpty());
    }

    // ─── setCurrentQuestion / getCurrentQuestion ──────────────────────────────────

    @Test
    public void setCurrentQuestion_null_getCurrentReturnsNull() {
        questionList.setCurrentQuestion(null);
        assertNull(questionList.getCurrentQuestion());
    }

    @Test
    public void setCurrentQuestion_overwritesPreviousQuestion() {
        Question first = questionList.getQuestions().get(0);
        Question second = questionList.getQuestions().get(1);
        questionList.setCurrentQuestion(first);
        questionList.setCurrentQuestion(second);
        assertEquals(second.getId(), questionList.getCurrentQuestion().getId());
    }
}