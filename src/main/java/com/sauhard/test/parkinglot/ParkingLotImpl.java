package com.sauhard.test.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import com.sauhard.test.parkinglot.domain.Car;

public class ParkingLotImpl implements ParkingLot {

	private final Integer size;
	private Integer currSize;
	private Map<Integer, Car> slotsMap;

	private final DisplayManager displayManager;

	public ParkingLotImpl(Integer size) {
		super();
		this.size = size;
		this.currSize = 0;
		this.slotsMap = new HashMap<>();
		displayManager = new DisplayManager();
		System.out.println("Created a parking lot with " + size + " slots");
	}

	@Override
	public void park(String registrationNumber, String color) {
		if (!currSize.equals(size)) {
			currSize++;
			Integer freeSlot = getSmallestFreeSlot();
			Car car = new Car(registrationNumber, color, freeSlot);
			slotsMap.put(freeSlot, car);
			System.out.println("Allocated slot number: " + freeSlot);
		} else {
			System.out.println("Sorry, parking lot is full");
		}
	}

	private Integer getSmallestFreeSlot() {
		return IntStream.rangeClosed(1, size).filter(i -> !slotsMap.containsKey(i)).min().getAsInt();
	}

	@Override
	public void leave(Integer slotNumber) {
		slotsMap.remove(slotNumber);
		currSize--;
		System.out.println("Slot number " + slotNumber + " is free");
	}

	@Override
	public void status() {
		displayManager.printStatus(slotsMap);
	}

	@Override
	public void printSlotNumbersForColor(String color) {
		displayManager.printSlotNumbersForColor(slotsMap, color);
	}

	@Override
	public void printRegistrationNumbersForColor(String color) {
		displayManager.printRegistrationNumersForColor(slotsMap, color);
	}

	@Override
	public void printSlotNumberForRegistrationNumber(String registrationNumber) {
		displayManager.printSlotNumberForRegistrationNumber(slotsMap, registrationNumber);
	}

}