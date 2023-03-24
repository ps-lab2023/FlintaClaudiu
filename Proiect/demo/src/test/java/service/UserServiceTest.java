package service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.implementation.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    private static final String USERNAME = "ClaudiuFlinta";
    private static final String USERNAME_NOT = "NuExista";
    private static final long ID = 1;
    private static final long WRONG_ID = 1;
    private static final String OLD_PASSWORD = "ALABALA";
    private static final String NEW_PASSWORD = "ALABALA_123";
    private static final String WRONG_PASSWORD = "ALABA";

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void init() {
        initMocks(this);
        user = new User();
        user.setId(1L);
        user.setUserName(USERNAME);
        user.setPassword(OLD_PASSWORD);
        when(userRepository.findFirstByUserName(USERNAME)).thenReturn(user);
        when(userRepository.findFirstById(ID)).thenReturn(user);
        when(userRepository.findUsersByUserNameAndPassword(USERNAME,OLD_PASSWORD)).thenReturn(user);
    }

    @Test
    void givenExistingUsername_whenFindByUsername_thenFindOne() {

        userService = new UserService(userRepository);

        User user = userService.findByUserName(USERNAME);
        System.out.println(user);

        assertNotNull(user);
        assertEquals(USERNAME, user.getUserName());
    }

    @Test
    void givenNonExistingUsername_whenFindByUsername_thenThrowException() {
        when(userRepository.findFirstByUserName(USERNAME_NOT)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.findByUserName(USERNAME_NOT);
        });

    }

    @Test
    void whenupdateUserPassword_thenCheckCorrectResult() {

        userService = new UserService(userRepository);

        userService.updateUserPassword(user, NEW_PASSWORD);

        assertNotNull(user);
        assertEquals(NEW_PASSWORD, user.getPassword());

    }

    @Test
    void whenupdateUserPassword_thenCheckWrongResult() {
        userService = new UserService(userRepository);

        userService.updateUserPassword(user, NEW_PASSWORD);

        assertNotNull(user);
        assertNotEquals(WRONG_PASSWORD, user.getPassword());

    }

    @Test
    void insertNewUser_thenCheckAdded_Pass() {

        User user2 = new User();
        user2.setUserName("ABCD");
        userService = new UserService(userRepository);

        User addedUser = userService.addUser(user2);

        assertNotNull(user2);
        assertNotNull(addedUser);
        assertEquals(addedUser, user2);

    }

    @Test
    void deleteUser_thenCheck_Pass() {

        userService = new UserService(userRepository);

        Boolean deleted = userService.deleteUser(user);

        assertEquals(deleted, true);

    }

    @Test
    void callLogIn_thenCheck_Pass() {

        userService = new UserService(userRepository);

        User new_user = userService.LogIn(USERNAME,OLD_PASSWORD);

        assertNotNull(new_user);
        assertEquals(user, new_user);

    }

    @Test
    void callLogIn_thenCheck_Fail() {

        userService = new UserService(userRepository);

        User new_user = userService.LogIn(USERNAME,NEW_PASSWORD);

        assertNull(new_user);

    }

    @Test
    void givenExistingId_whenFindById_thenFindOne() {

        userService = new UserService(userRepository);

        User user = userService.findUserById(ID);

        assertNotNull(user);
        assertEquals(ID, user.getId());
    }

    @Test
    void givenNonExistingId_whenFindById_thenThrowException() {
        when(userRepository.findUserById(WRONG_ID)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.findUserById(WRONG_ID);
        });

    }


}
