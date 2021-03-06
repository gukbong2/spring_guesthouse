package com.spring.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	
	private Long idx;
	private String email;
	private String password;
	private String name;
	private Date regdate;
	private String salt;
	private int auth;
	private String authkey;
	private String phone;
	private String address;
	private String addressDetail;
	private String type;
	
}
