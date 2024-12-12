package product.mangagement.productm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.mangagement.productm.service.UserService;
import product.mangagement.productm.utils.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

@Component
public class JsonFileter extends OncePerRequestFilter {
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = request.getHeader("Authorization");

        // Check if the token is present and starts with "Bearer "
        if (token != null && token.startsWith("Bearer ")) {
            // Remove the "Bearer " prefix from the token
            token = token.substring(7);

            try {
                // Get username from the token
                String username = jwtUtil.getUsernameFromToken(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Load user details using the UserService
                    UserDetails userDetails = userService.loadUserByUsername(username);

                    if (userDetails != null) {
                        // If user is found, set the authentication in the security context
                        UsernamePasswordAuthenticationToken authenticationToken = 
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } catch (Exception e) {
                // Handle exception (token parsing, expiration, etc.)
                System.out.println("Error processing JWT token: " + e.getMessage());
            }
        }
        // Proceed with the filter chain
        filterChain.doFilter(request, response);
    }
}
