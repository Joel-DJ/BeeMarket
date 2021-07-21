package com.emarket.BeeMarket.resources;

import com.emarket.BeeMarket.dao.RoleUpdate;
import com.emarket.BeeMarket.model.AppUser;
import com.emarket.BeeMarket.model.RoleEnum;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/changeRole")
public class RoleResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String changeUserRole(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            AppUser appUser = (AppUser) session.getAttribute("user");
            if (appUser.getRoleEnumName() == RoleEnum.BUYER) {
                appUser.setRoleEnumName(RoleEnum.SELLER);
            } else {
                appUser.setRoleEnumName(RoleEnum.BUYER);
            }
            try {
                RoleUpdate.updateRole(appUser);
            } catch (SQLException exception) {
                exception.printStackTrace();
                return "Failed";
            }
            session.setAttribute("user", appUser);
            return "Success";
        } else {
            return "";
        }
    }
}
