import Mocks.MockRepository;
import com.hotelbooking.model.User;
import com.hotelbooking.model.valueObjects.Adress;
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
        Adress adress = new Adress(
                "Country1",
                123456,
                "Berlin",
                "Street",
                123
        );
        User user = new User();
        user.setUserId(0);
        user.setEmail(emailExample);
        user.setAdress(adress);

        userRegistrationService.createEntity(user);

        User createdUser = userRepository.getById(0);
        assertNotNull(createdUser);
        assertEquals(emailExample, createdUser.getEmail());
        assertEquals(adress, createdUser.getAdress());
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setUserId(0);
        user.setEmail(emailExample);

        userRegistrationService.createEntity(user);

        User foundUser = userRegistrationService.getEntityById(0);

        assertNotNull(foundUser);
        assertEquals(emailExample, foundUser.getEmail());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setUserId(0);
        user.setEmail(emailExample);

        userRegistrationService.createEntity(user);

        String updatedEmail = "email1@email1.de";
        user.setEmail(updatedEmail);
        userRegistrationService.updateEntityById(0, user);

        User updatedUser = userRegistrationService.getEntityById(0);

        assertNotNull(updatedUser);
        assertEquals(updatedEmail, updatedUser.getEmail());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setUserId(1);
        user.setEmail(emailExample);

        userRegistrationService.createEntity(user);
        userRegistrationService.deleteEntityById(0);
        User deletedUser = userRegistrationService.getEntityById(0);

        assertNull(deletedUser);
    }

    @Test
    void testDeleteUserNotFound() {
        assertThrows(RuntimeException.class, () -> userRegistrationService.deleteEntityById(0));
    }

    @Test
    void testUpdateUserNotFound() {
        User user = new User();
        user.setUserId(0);
        user.setEmail(emailExample);

        assertThrows(RuntimeException.class, () -> userRegistrationService.updateEntityById(0, user));
    }
}
