package info.team23h.acc.vo.member;

import info.team23h.acc.vo.common.CommonVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberVO extends CommonVO {
	/* 시퀀스 */
	private long seq;
	/* 아이디 */
	private String id;
	/* 비밀번호 */
	private String password;
	/* 회원종류 */
	private String role;
	/* 로그인 시간 */
	private String loginTime;
}
