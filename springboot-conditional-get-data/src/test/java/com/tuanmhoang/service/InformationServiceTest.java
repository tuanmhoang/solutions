package com.tuanmhoang.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.tuanmhoang.enums.InfoType;

@SpringBootTest
@ActiveProfiles("test")
public class InformationServiceTest {
	
	@Autowired
	private InformationService service;
	
	@Test
	void getInfomationDataThirdApproach_typeGeneralInformation_shouldCorrect() throws Exception {
		Map<String, String> data = service.getInfomationDataThirdApproach("id", InfoType.GENERAL_INFORMATION);
		assertNotNull(data.get("fullName"));
		assertNotNull(data.get("dob"));
		assertNotNull(data.get("nationality"));
		assertNull(data.get("passportNo"));
		assertNull(data.get("dateOfIssue"));
		assertNull(data.get("address"));
		assertNull(data.get("phoneNo"));
		assertEquals(data.get("fullName"), "John Doe");
		assertEquals(data.get("dob"), "01-01-1990");
		assertEquals(data.get("nationality"), "VN");
	}
	
	@Test
	void getInfomationDataThirdApproach_typeLegalInformation_shouldCorrect() throws Exception {
		Map<String, String> data = service.getInfomationDataThirdApproach("id", InfoType.LEGAL_INFORMATION);
		assertNull(data.get("fullName"));
		assertNull(data.get("dob"));
		assertNull(data.get("nationality"));
		assertNotNull(data.get("passportNo"));
		assertNotNull(data.get("dateOfIssue"));
		assertNull(data.get("address"));
		assertNull(data.get("phoneNo"));
		assertEquals(data.get("passportNo"), "123456");
		assertEquals(data.get("dateOfIssue"), "01-01-2010");
	}
	
	@Test
	void getInfomationDataThirdApproach_typeContactInformation_shouldCorrect() throws Exception {
		Map<String, String> data = service.getInfomationDataThirdApproach("id", InfoType.CONTACT_INFORMATION);
		assertNull(data.get("fullName"));
		assertNull(data.get("dob"));
		assertNull(data.get("nationality"));
		assertNull(data.get("passportNo"));
		assertNull(data.get("dateOfIssue"));
		assertNotNull(data.get("address"));
		assertNotNull(data.get("phoneNo"));
		assertEquals(data.get("address"), "123 street");
		assertEquals(data.get("phoneNo"), "123456789");
	}
	
	@Test
	void getInfomationDataThirdApproach_typeInformation_shouldCorrect() throws Exception {
		Map<String, String> data = service.getInfomationDataThirdApproach("id", null);
		assertNotNull(data.get("fullName"));
		assertNotNull(data.get("dob"));
		assertNotNull(data.get("nationality"));
		assertNotNull(data.get("passportNo"));
		assertNotNull(data.get("dateOfIssue"));
		assertNotNull(data.get("address"));
		assertNotNull(data.get("phoneNo"));
		assertEquals(data.get("fullName"), "John Doe");
		assertEquals(data.get("dob"), "01-01-1990");
		assertEquals(data.get("nationality"), "VN");
		assertEquals(data.get("passportNo"),"123456");
		assertEquals(data.get("dateOfIssue"),"01-01-2010");
		assertEquals(data.get("address"),"123 street");
		assertEquals(data.get("phoneNo"),"123456789");
	}


}
