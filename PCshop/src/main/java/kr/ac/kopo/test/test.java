package kr.ac.kopo.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.dao.MemberDAOImpl;
import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.LoginVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.mybatis.MyConfig;


class test {

	@Test
	void MyConfigTest() throws Exception {
		new MyConfig();
	}

	@Test
	void sqlSessionTest() throws Exception {
		SqlSession session = new MyConfig().getInstance();
		assertNotNull(session);
	}
	
	@Test
	void login() throws Exception {
		LoginVO login = new LoginVO("USER1", "1234");
		MemberDAO memberDao = new MemberDAOImpl();
		MemberService memberService = new MemberService(memberDao);
		System.out.println(memberService.login(login).toString());
		
	}
	
	@Test
	public void testIsAdmin() {
	    // 테스트용 MemberVO 데이터 설정
	    MemberVO member = new MemberVO("admin", "password", "Admin User", "admin@example.com", "admin");
	    
	    // isAdmin 메서드 호출
	    MemberDAO memberDao = new MemberDAOImpl();
	    MemberService memberService = new MemberService(memberDao);

	    // 중간 확인
	    System.out.println("Member ID: " + member.getId());
	    SqlSession session = new MyConfig().getInstance();
	    Boolean isAdmin = session.selectOne("kr.ac.kopo.member.dao.MemberMapper.isAdmin", member);


	    boolean test = memberService.isAdmin(member);

	    // 중간 결과 확인
	    System.out.println("isAdmin 결과: " + test);

	    // 결과 검증
	    assertTrue("관리자 계정임을 확인합니다.", test);
	}

}
