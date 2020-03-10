package readerblog.mates.readerblog.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import readerblog.mates.readerblog.entities.User;
import readerblog.mates.readerblog.payload.AuthResponse;
import readerblog.mates.readerblog.security.TokenProvider;
import readerblog.mates.readerblog.security.UserPrincipal;
import readerblog.mates.readerblog.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private UserService userService;

    @Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	private TokenProvider tokenProvider;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		String email = userPrincipal.getEmail();
		User user = userService.findByEmail(email);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		String token = tokenProvider.createToken(authentication);
		ResponseEntity.ok(new AuthResponse(token));
		if(!request.getHeader("referer").contains("login")) {
			response.sendRedirect(request.getHeader("referer"));
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}
}
