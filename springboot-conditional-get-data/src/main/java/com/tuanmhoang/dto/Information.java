package com.tuanmhoang.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Information {
	private String fullName;
	private String dob;
	private String nationality;
	private String passportNo;
	private String dateOfIssue;
	private String address;
	private String phoneNo;
}
