package com.tuanmhoang.enums;

public enum InfoType {
	GENERAL_INFORMATION("general"), 
	LEGAL_INFORMATION("legal"), 
	CONTACT_INFORMATION("contact"), 
	DEFAULT("default");

	private String name;
	
	InfoType(String name){
		this.name=name;
	}

	public static InfoType getByName(String name) {
		if(name == null)
			return InfoType.DEFAULT;
		
		switch (name) {
		case "general":
			return InfoType.GENERAL_INFORMATION;
		case "legal":
			return InfoType.LEGAL_INFORMATION;
		case "contact":
			return InfoType.CONTACT_INFORMATION;
		default:
			return InfoType.DEFAULT;
		}
	}

	public String getName() {
		return name;
	}

}
