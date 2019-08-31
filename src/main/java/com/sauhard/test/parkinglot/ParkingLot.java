package com.sauhard.test.parkinglot;

public interface ParkingLot {

	void park(String registrationNumber, String color);
	
	void leave(Integer slotNumber);
	
	void status();
	
	void printSlotNumbersForColor(String color);
	
	void printRegistrationNumbersForColor(String color);
	
	void printSlotNumberForRegistrationNumber(String registrationNumber);

}