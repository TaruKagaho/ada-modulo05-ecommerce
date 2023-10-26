package ada.santander.coders.mod05.ecommerce02.infra.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        /*response.sendError(
                HttpServletResponse.SC_UNAUTHORIZED,
                authException.getMessage()
        );*/
        if (response.getStatus() != 200) {
            response.sendError(response.getStatus());
        } else
            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    authException.getMessage()
            );
    }
}
