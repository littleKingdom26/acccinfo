package info.team23h.acc.config.auth;

import info.team23h.acc.service.MemberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final MemberServiceImpl memberServiceImpl;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/image/**", "/admin/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().headers().frameOptions().disable()// csrf t사용 안함
				.and()
				.authorizeRequests().antMatchers("/", "/bbs/*", "/bbs/*/*","/error","/recordPlayerDetailAjax","/recordListAjax","/api/*").permitAll() // 전체권한
				.anyRequest().hasRole(Role.ADMIN.name())  // 위 페이지 외 인증된 유저
				.and() // 로그아웃 설정
				.logout().logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID","remember-me")
				.and() // 로그인 설정
				.formLogin().loginPage("/admin/login").permitAll().passwordParameter("password").usernameParameter("id").defaultSuccessUrl("/admin/main").failureUrl("/admin/login")
				.and().rememberMe().key("team23h").rememberMeParameter("remember-me").tokenValiditySeconds(86400);

		}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberServiceImpl).passwordEncoder(passwordEncoder());
	}
}
