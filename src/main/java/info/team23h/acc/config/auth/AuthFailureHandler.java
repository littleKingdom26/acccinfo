package info.team23h.acc.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response,
										AuthenticationException e) throws IOException, ServletException {
		String userName = request.getParameter("username");
		log.debug("userName > " + userName);
		log.error("관리자 로그인 실패 ");
		log.error(" e" +e.getLocalizedMessage() );

		if(e instanceof UsernameNotFoundException){
			super.setDefaultFailureUrl("/admin/login?error=noUser");
		}else if(e instanceof BadCredentialsException){
			super.setDefaultFailureUrl("/admin/login?error=bad");
		}
		super.onAuthenticationFailure(request, response, e);
	}
}
