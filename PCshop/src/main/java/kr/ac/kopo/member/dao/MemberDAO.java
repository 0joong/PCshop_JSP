package kr.ac.kopo.member.dao;

import java.util.List;

import kr.ac.kopo.member.vo.LoginVO;
import kr.ac.kopo.member.vo.MemberVO;

public interface MemberDAO {
	
	MemberVO login(LoginVO login);
	
	boolean register(MemberVO member);

	boolean isAdmin(MemberVO member);
	
	boolean isIdAvailable(String userId);
	
	MemberVO getInfoById(String userId);
	
	String getPasswordByUserId(String userId);
	
	int deleteByUserId(String userId);
	
    List<MemberVO> findAllUsers();

    List<MemberVO> findUsersByKeyword(String keyword);
    
    int deleteUserById(String userId);
    
    //Update user information
    int updateUserInfo(MemberVO user);
}
