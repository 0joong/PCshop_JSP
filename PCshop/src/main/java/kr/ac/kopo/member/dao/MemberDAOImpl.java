package kr.ac.kopo.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.ac.kopo.member.vo.LoginVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.mybatis.MyConfig;

public class MemberDAOImpl implements MemberDAO {

	private SqlSession sqlSession;
	private MemberMapper memberMapper;

	public MemberDAOImpl() {
		sqlSession = new MyConfig().getInstance();
		this.memberMapper = sqlSession.getMapper(MemberMapper.class);
	}

	@Override
	public MemberVO login(LoginVO login) {
		return memberMapper.login(login);
	}

	@Override
	public boolean register(MemberVO member) {
		// MyBatis의 insert 메서드를 통해 데이터 삽입
		// int rowsAffected =
		// sqlSession.insert("kr.ac.kopo.member.dao.MemberMapper.register", member);
		int rowsAffected = memberMapper.register(member);
		return rowsAffected > 0; // 성공 여부 반환
	}

	@Override
	public boolean isAdmin(MemberVO member) {
		sqlSession.clearCache();
		return memberMapper.isAdmin(member);
	}

	@Override
	public boolean isIdAvailable(String userId) {
		// Mapper의 countById 메서드를 사용하여 아이디 중복 여부를 확인합니다.
		sqlSession.clearCache();
		return memberMapper.countById(userId) == 0; // 존재하지 않으면 true, 존재하면 false
	}

	@Override
	public MemberVO getInfoById(String userId) {
		sqlSession.clearCache();
		// return sqlSession.selectOne("kr.ac.kopo.member.dao.MemberDAO.getInfoById",
		// userId);
		return memberMapper.getInfoById(userId);
	}

	public String getPasswordByUserId(String userId) {
		sqlSession.clearCache();
		return sqlSession.selectOne("kr.ac.kopo.member.dao.MemberMapper.getPasswordByUserId", userId);
	}

	public int deleteByUserId(String userId) {
		sqlSession.clearCache();
		return sqlSession.delete("kr.ac.kopo.member.dao.MemberMapper.deleteByUserId", userId);
	}

	@Override
	public List<MemberVO> findAllUsers() {
		sqlSession.clearCache();
		return sqlSession.selectList("kr.ac.kopo.member.dao.MemberMapper.findAllUsers");
	}

	@Override
	public List<MemberVO> findUsersByKeyword(String keyword) {
		sqlSession.clearCache();
		return sqlSession.selectList("kr.ac.kopo.member.dao.MemberMapper.findUsersByKeyword", keyword);
	}
	
	@Override
    public int deleteUserById(String userId) {
		sqlSession.clearCache();
        return sqlSession.delete("kr.ac.kopo.member.dao.MemberMapper.deleteUserById", userId);
    }
	
	@Override
    public int updateUserInfo(MemberVO member) {
		sqlSession.clearCache();
        return sqlSession.update("kr.ac.kopo.member.dao.MemberMapper.updateUserInfo", member);
    }
}
