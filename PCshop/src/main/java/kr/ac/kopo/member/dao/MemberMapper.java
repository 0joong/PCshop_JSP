package kr.ac.kopo.member.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.kopo.member.vo.LoginVO;
import kr.ac.kopo.member.vo.MemberVO;

@Mapper
public interface MemberMapper {

	MemberVO login(LoginVO login);
	
	boolean isAdmin(MemberVO member);
	
	int register(MemberVO member); 
	
	int countById(String id);
	
	MemberVO getInfoById(String userId);
}
