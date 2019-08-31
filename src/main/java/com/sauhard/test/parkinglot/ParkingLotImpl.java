package com.sauhard.test.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotImpl implements ParkingLot {

	private final Integer size;
	private Integer currSize;
	private Map<Integer, String> slotsMap;

	
	
	public ParkingLotImpl(Integer size) {
		super();
		this.size = size;
		this.currSize = 0;
		this.slotsMap = new HashMap<>();
	}

	public void park(String registrationNumber, String color) {
		
	}

	public void leave(Integer slotNumber) {
		// TODO Auto-generated method stub

	}

	public void status() {
		// TODO Auto-generated method stub

	}

	public void printSlotNumbersForColor(String color) {
		// TODO Auto-generated method stub

	}

	public void printRegistrationNumbersForColor(String color) {
		// TODO Auto-generated method stub

	}

	public void printSlotNumberForRegistrationNumber(String registrationNumber) {
		// TODO Auto-generated method stub

	}

}