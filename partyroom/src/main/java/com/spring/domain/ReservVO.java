package com.spring.domain;

import java.util.Date;

import lombok.Data;
@Data
public class ReservVO {

	private Long reserv_idx;
	private String breakfast;
	private Long price;
	private String roomnum;
	private String reserv_name;
	private Date start_date;
	private Date end_date;
	private Long count;
}