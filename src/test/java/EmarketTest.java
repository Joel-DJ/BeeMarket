import com.emarket.BeeMarket.dao.Registration;
import com.emarket.BeeMarket.dao.RoleUpdate;
import com.emarket.BeeMarket.dao.UserVerification;
import com.emarket.BeeMarket.model.AppUser;
import com.emarket.BeeMarket.model.RoleEnum;
import com.emarket.BeeMarket.util.ConnectionUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmarketTest {

    static String id = String.valueOf(Math.random());

    @Test
    public void testRegister() throws SQLException {
        AppUser appUser = new AppUser();
        appUser.setUserName("John Doe");
        appUser.setEmail("johndoe@gmail.com");
        appUser.setRoleEnumName(RoleEnum.BUYER);
        appUser.setGoogleId(id);
        Assertions.assertEquals(1, Registration.registerUser(appUser));
    }

    @Test
    public void testUserVerify() throws SQLException {
        AppUser appUser = UserVerification.verifyUser(id);
        assert appUser != null;
        Assertions.assertEquals(RoleEnum.BUYER, appUser.getRoleEnumName());
    }

    @Test
    public void testRoleUpdate() throws SQLException {
        AppUser appUser = new AppUser();
        appUser.setUserName("John Doe");
        appUser.setEmail("johndoe@gmail.com");
        appUser.setRoleEnumName(RoleEnum.BUYER);
        appUser.setGoogleId(id);
        Assertions.assertEquals(1, RoleUpdate.updateRole(appUser));
    }

    @AfterAll
    static void deleteTestUser() throws SQLException {
        final String deleteUser = "DELETE FROM users WHERE emailId=?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prepareDelete = connection.prepareStatement(deleteUser)) {
            prepareDelete.setString(1, id);
            prepareDelete.executeUpdate();
        }
    }
}
