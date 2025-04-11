import Mocks.MockRepository;
import com.hotelbooking.model.User;
import com.hotelbooking.service.UserRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegistrationServiceTest {

    private MockRepository<User> userRepository;
    private UserRegistrationService userRegistrationService;
    private final String emailExample = "email@email.com";

    @BeforeEach
    void setUp() {
        userRepository = new MockRepository<>();
        userRegistrationService = new UserRegistrationService(userRepository);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setUserId(0);
        user.setEmail(emailExample);

        userRegistrationService.create(user);

        User createdUser = userRepository.getById(0);
        assertNotNull(createdUser);
        assertEquals(emailExample, createdUser.getEmail());
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setUserId(0);
        user.setEmail(emailExample);

        userRegistrationService.create(user);

        User foundUser = userRegistrationService.getById(0);

        assertNotNull(foundUser);
        assertEquals(emailExample, foundUser.getEmail());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setUserId(0);
        user.setEmail(emailExample);

        userRegistrationService.create(user);

        String updatedEmail = "email1@email1.de";
        user.setEmail(updatedEmail);
        userRegistrationService.update(0, user);

        User updatedUser = userRegistrationService.getById(0);

        assertNotNull(updatedUser);
        assertEquals(updatedEmail, updatedUser.getEmail());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setUserId(1);
        user.setEmail(emailExample);

        userRegistrationService.create(user);
        userRegistrationService.delete(0);
        User deletedUser = userRegistrationService.getById(0);

        assertNull(deletedUser);
    }

    @Test
    void testDeleteUserNotFound() {
        assertThrows(RuntimeException.class, () -> userRegistrationService.delete(0));
    }

    @Test
    void testUpdateUserNotFound() {
        User user = new User();
        user.setUserId(0);
        user.setEmail(emailExample);

        assertThrows(RuntimeException.class, () -> userRegistrationService.update(0, user));
    }
}
