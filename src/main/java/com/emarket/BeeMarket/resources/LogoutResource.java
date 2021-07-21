package com.emarket.BeeMarket.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/logout")
public class LogoutResource {

    @GET
    public String logout(@Context HttpServletRequest request,@Context HttpServletResponse response){
        HttpSession session=request.getSession();
        session.removeAttribute("googleUser");
        session.removeAttribute("user");
        session.invalidate();
        return "success";
    }
}

