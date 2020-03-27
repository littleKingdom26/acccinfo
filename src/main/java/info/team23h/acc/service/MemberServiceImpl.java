package info.team23h.acc.service;

import info.team23h.acc.config.auth.Role;
import info.team23h.acc.dao.MemberDAO;
import info.team23h.acc.vo.LoginUserVO;
import info.team23h.acc.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService, UserDetailsService {
	@Autowired
	MemberDAO memberDAO;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberVO memberVO = memberDAO.getMember(id);
		List<GrantedAuthority> authorityList = new ArrayList<>();
		if(Role.ADMIN.getKey().equals(memberVO.getRole())){
			authorityList.add(new SimpleGrantedAuthority(Role.ADMIN.getKey()));
		}else{
			authorityList.add(new SimpleGrantedAuthority(Role.USER.getKey()));
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return LoginUserVO.of(memberVO, authorityList);
	}
}
