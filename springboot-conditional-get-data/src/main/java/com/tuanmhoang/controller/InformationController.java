package com.tuanmhoang.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuanmhoang.dto.request.InformationRequest;
import com.tuanmhoang.enums.InfoType;
import com.tuanmhoang.service.InformationService;

@RestController
public class InformationController {

	private final InformationService informationService;

	public InformationController(InformationService informationService) {
		this.informationService = informationService;
	}

	/**
	 * Get by ResponseEntity, easiest way
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/information/v1")
	public ResponseEntity<?> getInfomationFirstApproach(@RequestBody InformationRequest req) {
		InfoType type = InfoType.getByName(req.getInfoType());
		String id = req.getId();
		switch (type) {
		case CONTACT_INFORMATION:
			return ResponseEntity.ok(informationService.getContactInfomation(id));
		case GENERAL_INFORMATION:
			return ResponseEntity.ok(informationService.getGeneralInfomation(id));
		case LEGAL_INFORMATION:
			return ResponseEntity.ok(informationService.getLegalInfomation(id));
		default:
			return ResponseEntity.ok(informationService.getInformation(id));
		}
	}

	@PostMapping("/information/v2")
	public ResponseEntity<?> getInfomationSecondApproach(@RequestBody InformationRequest req) {
		InfoType type = InfoType.getByName(req.getInfoType());
		Map<String, String> infomationData = informationService.getInfomationDataSecondApproach(req.getId(), type);
		return ResponseEntity.ok(infomationData);
	}
	
	@PostMapping("/information/v3")
	public ResponseEntity<?> getInfomationThirdApproach(@RequestBody InformationRequest req) throws JsonProcessingException {
		InfoType type = InfoType.getByName(req.getInfoType());
		Map<String, String> infomationData = informationService.getInfomationDataThirdApproach(req.getId(), type);
		return ResponseEntity.ok(infomationData);
	}
	
}
