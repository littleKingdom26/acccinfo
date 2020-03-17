package info.team23h.acc.dao;

import info.team23h.acc.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSession sql;

	private final String PREFIX = "memberSql";


	public MemberVO getMember(String id) {
		return sql.selectOne(PREFIX+".getMember",id);
	}
}
