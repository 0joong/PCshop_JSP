package kr.ac.kopo.member.service;

import java.util.List;

import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.LoginVO;
import kr.ac.kopo.member.vo.MemberVO;

public class MemberService {

	private MemberDAO memberDao;

	public MemberService(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}

	public MemberVO login(LoginVO login) {
		return memberDao.login(login);
	}

	public boolean isAdmin(MemberVO member) { 
		boolean result = memberDao.isAdmin(member); 
		return result;
	}

	public boolean register(MemberVO member) {
		return memberDao.register(member);
	}

	public boolean isIdAvailable(String userId) {
		return memberDao.isIdAvailable(userId); // DAO를 통해 아이디 중복 여부 확인
	}

	public MemberVO getMemberInfo(String userId) {
		return memberDao.getInfoById(userId);
	}

	public boolean validatePassword(String userId, String inputPassword) {
		String savedPassword = memberDao.getPasswordByUserId(userId); // DB에서 암호화된 비밀번호 가져오기
		return savedPassword != null && savedPassword.equals(inputPassword); // 비밀번호 확인 (암호화 적용 시 수정 필요)
	}

	public boolean deleteAccount(String userId) {
		int rows = memberDao.deleteByUserId(userId);
		return rows > 0;
	}

	// Get all users
	public List<MemberVO> getAllUsers() {
		return memberDao.findAllUsers();
	}

	// Search users by keyword
	public List<MemberVO> searchUsers(String keyword) {
		return memberDao.findUsersByKeyword(keyword);
	}

	// Delete user by ID
	public boolean deleteUserById(String userId) {
		int rowsAffected = memberDao.deleteUserById(userId);
		return rowsAffected > 0;
	}

	// Update user information
	public boolean updateUserInfo(MemberVO user) {
		int rowsAffected = memberDao.updateUserInfo(user);
		return rowsAffected > 0;
	}
}
