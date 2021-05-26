package info.team23h.acc.config.auth;

import info.team23h.acc.service.member.MemberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final MemberServiceImpl memberServiceImpl;


	private final AuthFailureHandler authFailUreHandler;


	@Bean(name = "bCryptPasswordEncoder")
	public BCryptPasswordEncoder bCryptPasswordEncoder(){return new BCryptPasswordEncoder();}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**","/img/**", "/image/**", "/admin/css/**", "/uplaod/**", "/ads.txt");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().headers().frameOptions().disable()// csrf t사용 안함
				.and()
				.authorizeRequests().antMatchers("/", "/bbs/**","/error","/ajax/**","/recode","/teamResult","/teamResult/**"
				,"/api/**","/team23h","/fileUpload","/imageView/**","/ckEditorImgUpload","/calendar","/result","/result/**","/admin/login").permitAll() // 전체권한
				.anyRequest().hasRole(Role.ADMIN.name())  // 위 페이지 외 인증된 유저
				.and() // 로그아웃 설정
				.logout().logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID","remember-me")
				.and() // 로그인 설정
				.formLogin().loginPage("/admin/login").permitAll().passwordParameter("password").usernameParameter("id").defaultSuccessUrl("/admin/main")
				.failureHandler(authFailUreHandler)
				.and().rememberMe().key("team23h").rememberMeParameter("remember-me").tokenValiditySeconds(86400);

		}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberServiceImpl).passwordEncoder(bCryptPasswordEncoder());
	}
}
