package com.tuanmhoang.service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.tuanmhoang.dto.ContactInfomation;
import com.tuanmhoang.dto.GeneralInfomation;
import com.tuanmhoang.dto.Information;
import com.tuanmhoang.dto.Information3rdSolution;
import com.tuanmhoang.dto.LegalInfomation;
import com.tuanmhoang.enums.InfoType;

@Service
public class InformationService {

	/**
	 * First approach is to create 4 methods. Simulate a service
	 */
	public Information getInformation(String id) {
		Information searchedResult = new Information("John Doe", "01-01-1990", "VN", "123456", "01-01-2010",
				"123 street", "123456789");
		return searchedResult;
	}

	public GeneralInfomation getGeneralInfomation(String id) {
		GeneralInfomation searchedResult = new GeneralInfomation("John Doe", "01-01-1990", "VN");
		return searchedResult;
	}

	public LegalInfomation getLegalInfomation(String id) {
		LegalInfomation searchedResult = new LegalInfomation("123456", "01-01-2010");
		return searchedResult;
	}

	public ContactInfomation getContactInfomation(String id) {
		ContactInfomation searchedResult = new ContactInfomation("123 street", "123456789");
		return searchedResult;
	}

	/**
	 * Second approach using reflection
	 */

	private List<String> selectedPropsList;

	@Value("#{${data.generalInfo}}")
	private List<String> generalInfoProps;

	@Value("#{${data.legalInfo}}")
	private List<String> legalInfoProps;

	@Value("#{${data.contactInfo}}")
	private List<String> contactInfoProps;

	public Map<String, String> getInfomationDataSecondApproach(String id, InfoType type) {
		Map<String, String> result = new HashMap<>();

		Information information = getInformation(id);
		if (ObjectUtils.isEmpty(type)) {
			ObjectMapper objMapper = new ObjectMapper();
			result = objMapper.convertValue(information, Map.class);
		} else {
			this.withInformationType(type).process(result, information);
		}

		return result;

	}

	private Map<String, String> process(Map<String, String> result, Information information) {
		selectedPropsList.forEach(prop -> {
			result.put(prop, invokeGetValue(prop, information));
		});
		return result;
	}

	private String invokeGetValue(String prop, Information information) {
		String methodName = "get" + StringUtils.capitalize(prop);
		// use ReflectionUtils.invokeMethod from Spring or use Java reflection
		Method methodToInvoke = ReflectionUtils.findMethod(Information.class, methodName);
		return (String) ReflectionUtils.invokeMethod(methodToInvoke, information);
	}

	private InformationService withInformationType(InfoType type) {
		if (type == null)
			type = InfoType.DEFAULT;

		switch (type) {
		case CONTACT_INFORMATION:
			this.selectedPropsList = contactInfoProps;
			this.filtersToExclude = contactInfoToExcludeProps;
			break;
		case GENERAL_INFORMATION:
			this.selectedPropsList = generalInfoProps;
			this.filtersToExclude = generalInfoToExcludeProps;
			break;
		case LEGAL_INFORMATION:
			this.selectedPropsList = legalInfoProps;
			this.filtersToExclude = legalInfoToExcludeProps;
			break;
		default:
			this.selectedPropsList = Arrays.stream(Information.class.getDeclaredFields())
											.map(f -> f.getName())
											.collect(Collectors.toList());
			break;
		}
		return this;
	}

	/**
	 * Third solution using JsonFilter
	 */
	@Value("#{${data.generalInfo.toExclude}}")
	private Set<String> generalInfoToExcludeProps;

	@Value("#{${data.legalInfo.toExclude}}")
	private Set<String> legalInfoToExcludeProps;

	@Value("#{${data.contactInfo.toExclude}}")
	private Set<String> contactInfoToExcludeProps;

	private Set<String> filtersToExclude;

	public Information3rdSolution getInformation3rdApproach(String id) {
		Information3rdSolution searchedResult = new Information3rdSolution("John Doe", "01-01-1990", "VN", "123456",
				"01-01-2010", "123 street", "123456789");
		return searchedResult;
	}

	public Map<String, String> getInfomationDataThirdApproach(String id, InfoType type) throws JsonProcessingException {
		
		if(type== null)
			type = InfoType.DEFAULT;
		
		ObjectMapper mapper = new ObjectMapper();

		Information3rdSolution information = getInformation3rdApproach(id);
		this.withInformationType(type);
		
		// dynamic field filtering is provided by FilterProvider
		FilterProvider filters = null;
		
		if(ObjectUtils.isEmpty(type) || InfoType.DEFAULT.equals(type)) {
			filters = new SimpleFilterProvider().setFailOnUnknownId(false);
		} else {
			filters = new SimpleFilterProvider().addFilter("myCustomFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(filtersToExclude));
		}
		
		return mapper.setFilterProvider(filters).convertValue(information, Map.class);
	}

}
