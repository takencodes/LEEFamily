package com.lee.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by defei on 3/9/16.
 */
public class ManageLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
    implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Authentication authentication)
        throws IOException, ServletException {

        super.handle(httpServletRequest, httpServletResponse, authentication);
    }
}
