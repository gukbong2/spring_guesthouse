package com.spring.mapper;

import com.spring.domain.MemberVO;

public interface MemberMapper {

	public MemberVO login(MemberVO member);
	
	public void insert(MemberVO member);
	
	public void insertSelectKey(MemberVO member);
	
	public int delete(Long idx);
	
	public MemberVO getMember(String email);
	
	public String getSaltById(String email);

	public void updateAuthkey(MemberVO member);

	public void updateAuthstatus(MemberVO member);

	public int getAuth(String email);

	public int modifyPassword(MemberVO member);

	public void updatePassword(MemberVO member);
	
}
