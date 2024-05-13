package com.jfreed.expensestracker.auth;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException
    {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected void handle(
        HttpServletRequest request,
        HttpServletResponse response, 
        Authentication authentication) throws IOException
    {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            log.debug("Response has already been committed. Unable to redirect to {}",
                targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication) {
        Map<String, String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put("ROLE_USER", "/user/home");
        roleTargetUrlMap.put("ROLE_ADMIN", "/admin/home");

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if(roleTargetUrlMap.containsKey(authorityName)) {
                return roleTargetUrlMap.get(authorityName);
            }
        }

        throw new IllegalStateException();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}