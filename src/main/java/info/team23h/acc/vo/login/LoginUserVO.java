package info.team23h.acc.vo.login;

import info.team23h.acc.vo.member.MemberVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
public class LoginUserVO  extends User {

	/* 시퀀스 */
	private long seq;
	/* 아이디 */
	private String id;
	/* 회원종류 */
	private String role;
	/* 로그인 시간 */
	private String loginTime;

	public LoginUserVO(String username,
					   String password,
					   Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public static LoginUserVO of (MemberVO memberVO,
								  List<GrantedAuthority> grantedAuthorityList){
		LoginUserVO loginUserVO = new LoginUserVO(memberVO.getId(), memberVO.getPassword(), grantedAuthorityList);
		loginUserVO.setId(memberVO.getId());
		loginUserVO.setRole(memberVO.getRole());
		loginUserVO.setSeq(memberVO.getSeq());
		loginUserVO.setLoginTime(memberVO.getLoginTime());
		return loginUserVO;
	}

}
