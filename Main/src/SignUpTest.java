import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SignUpTest {
    private SignUp signUp;

    @BeforeEach
    public void setUp() {
        signUp = new SignUp();
    }

    @Test
    public void testEmailTaken() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("UserInfo.txt"))) {
            writer.println("123,test@example.com,testuser,Display Name,Password123");
        }
        signUp.setEmail("test@example.com");
        assertTrue(signUp.isEmailTaken(), "Email should be detected as taken");

        signUp.setEmail("newuser@example.com");
        assertFalse(signUp.isEmailTaken(), "Email should not be detected as taken");
    }

    @Test
    public void testUsernameTaken() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("UserInfo.txt"))) {
            writer.println("123,test@example.com,testuser,Display Name,Password123");
        }
        signUp.setUsername("testuser");
        assertTrue(signUp.isUsernameTaken(), "Username should be detected as taken");

        signUp.setUsername("newuser");
        assertFalse(signUp.isUsernameTaken(), "Username should not be detected as taken");
    }

    @Test
    public void testSetAndGetUserID() {
        signUp.setUserID(12345);
        assertEquals(12345, signUp.getUserID(), "UserID should match the set value");
    }

    @Test
    public void testSetAndGetUsername() {
        signUp.setUsername("newuser");
        assertEquals("newuser", signUp.getUsername(), "Username should match the set value");
    }

    @Test
    public void testSetAndGetDisplayName() {
        signUp.setDisplayName("DisplayNameTest");
        assertEquals("DisplayNameTest", signUp.getDisplayName(), "DisplayName should match the set value");
    }

    @Test
    public void testSetAndGetPassword() {
        signUp.setPassword("SecurePassword123");
        assertEquals("SecurePassword123", signUp.getPassword(), "Password should match the set value");
    }
}
