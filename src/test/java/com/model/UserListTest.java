package com.model;

/**
 * Tests created by EJ Gordon
 * Issues #75 - 79 created
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class UserListTest {

    private UserList userList;

    @Before
    public void setup() {
        userList = UserList.getInstance();

    }

    @Test
    public void testAddUserWithEmptyString() {
        boolean result = userList.addUser("", "" , "");
        assertFalse(result);
    }


    @Test
    public void testAddUserWithNull() {
        boolean result = userList.addUser(null, null, null);
        assertFalse(result);
    }

    @Test
    public void testContainsUserPassNull() {
        boolean result = userList.containsUser("playboicarti", null);
        assertFalse(result);
    }

    @Test
    public void testcontainsUserNullPassTrue() {
        boolean result = userList.containsUser(null, "feinfeinfein123");
    }

    @Test
    public void testcontainsUserAllNull() {
        boolean result = userList.containsUser(null, null);
    }
    @Test
    public void testContainsUserOtherUserPass() {
        boolean result = userList.containsUser("playboicarti", "feinfeinfein123");
        assertFalse(result);
    }
    
    @Test
    public void testRemoveUserNull() {
        int before = userList.getUsers().size();
        userList.removeUser(null);
        int after = userList.getUsers().size();
        assertEquals(before - 1, after);
    }

    @Test
    public void testRemoveNulllUser() {
        int before = userList.getUsers().size();
        userList.removeUser(userList.getUser(null, null));
        int after = userList.getUsers().size();
        assertEquals(before - 1, after);
    }

    @Test
    public void testRemoveActualUser() {
        int before = userList.getUsers().size();
        userList.removeUser(userList.getUser("playboicarti", "WholeLottaRed123"));
        int after = userList.getUsers().size();
        assertEquals(before - 1, after);
    }

}
