package com.emarket.BeeMarket.resources;

import com.emarket.BeeMarket.dao.UserVerification;
import com.emarket.BeeMarket.model.AppUser;
import com.emarket.BeeMarket.model.GoogleUser;
import com.emarket.BeeMarket.util.GoogleAPIUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.sql.SQLException;

@Path("/login")
public class LoginResource {

    @POST
    public String loginUser(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        String idTokenString = request.getParameter("idtoken");
        GoogleUser googleUser = GoogleAPIUtil.verifyIDTokens(idTokenString);
        String result = "";
        if (googleUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("googleUser", googleUser);
            AppUser appUser;
            try {
                appUser = UserVerification.verifyUser(googleUser.getUserId());
                if (appUser != null) {
                    session.setAttribute("user", appUser);
                    result = "exist";
                } else {
                    result = "new";
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
                result = "Failed";
            }
        }
        return result;
    }
}
