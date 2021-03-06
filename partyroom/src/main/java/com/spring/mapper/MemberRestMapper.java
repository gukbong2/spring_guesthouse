package com.spring.mapper;

import com.spring.domain.MemberVO;

public interface MemberRestMapper {

	public MemberVO idCheck(String email);
	
	public MemberVO emailVerify(MemberVO member);

	public String remove(String name);

	public int delete(String email);

	public MemberVO phoneCheck(String phone);
	
}
