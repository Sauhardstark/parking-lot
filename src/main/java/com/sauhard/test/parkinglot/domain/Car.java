package com.sauhard.test.parkinglot.domain;

public class Car {

	private String registrationNumber;
	private String color;
	private Integer slotNumber;

	public Car(String registrationNumber, String color, Integer slotNumber) {
		super();
		this.registrationNumber = registrationNumber;
		this.color = color;
		this.slotNumber = slotNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Integer getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}

}