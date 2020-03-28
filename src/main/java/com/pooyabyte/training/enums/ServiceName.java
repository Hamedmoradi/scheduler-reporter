package com.pooyabyte.training.enums;

public enum ServiceName {
	CUSTOMER_REGISTRATION("Customer_Registration");
	private String name;

ServiceName(String name) {
	this.name = name;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
}
