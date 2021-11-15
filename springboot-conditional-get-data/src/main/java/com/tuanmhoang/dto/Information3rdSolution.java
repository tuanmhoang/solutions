package com.tuanmhoang.dto;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("myCustomFilter")
public class Information3rdSolution {
	private String fullName;
	private String dob;
	private String nationality;
	private String passportNo;
	private String dateOfIssue;
	private String address;
	private String phoneNo;
}
