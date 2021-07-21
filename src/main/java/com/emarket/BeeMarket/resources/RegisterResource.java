package com.emarket.BeeMarket.resources;

import com.emarket.BeeMarket.dao.Registration;
import com.emarket.BeeMarket.model.AppUser;
import com.emarket.BeeMarket.model.GoogleUser;
import com.emarket.BeeMarket.model.RoleEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.sql.SQLException;

@Path("/register")
public class RegisterResource {

    @POST
    public String registerWithRole(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        int role = Integer.parseInt(request.getParameter("role"));
        if (session != null && session.getAttribute("googleUser") != null) {
            GoogleUser googleUser = (GoogleUser) session.getAttribute("googleUser");
            AppUser user = new AppUser();
            user.setRoleEnumName(RoleEnum.getByRole(role));
            user.setUserName(googleUser.getName());
            user.setEmail(googleUser.getEmail());
            user.setGoogleId(googleUser.getUserId());
            try {
                Registration.registerUser(user);
            } catch (SQLException exception) {
                exception.printStackTrace();
                return "Failed";
            }
            session.setAttribute("user", user);
            return "Success";
        } else {
            return "";
        }
    }
}
