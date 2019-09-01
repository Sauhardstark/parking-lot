package com.sauhard.test.parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.sauhard.test.parkinglot.domain.Car;

public class ParkingLotImpl implements ParkingLot {

	private final Integer size;
	private Integer currSize;
	private Map<Integer, Car> slotsMap;
	
	public ParkingLotImpl(Integer size) {
		super();
		this.size = size;
		this.currSize = 0;
		this.slotsMap = new HashMap<>();
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
		System.out.println("Slot No.\t" + "Registration No\t" + "Colour");

		List<Integer> parkedSlotsList = new ArrayList<Integer>(slotsMap.keySet());
		Collections.sort(parkedSlotsList);

		printSlotAndCarInformation(parkedSlotsList);
	}

	private void printSlotAndCarInformation(List<Integer> parkedSlotsList) {
		for (Integer slot : parkedSlotsList) {
			Car car = slotsMap.get(slot);
			String color = car.getColor();
			String registrationNumber = car.getRegistrationNumber();
			System.out.println(slot + "\t" + registrationNumber + "\t" + color);
		}
	}

	@Override
	public void printSlotNumbersForColor(String color) {
		List<Integer> slots =slotsMap.entrySet().parallelStream().filter(i -> i.getValue().getColor().equals(color)).map(i -> i.getKey())
				.collect(Collectors.toList());	
		if (null == slots || slots.isEmpty()) {
			System.out.println("Not found");
		}
		else {
			Collections.sort(slots);
			String slotsString = slots.stream().map(i -> i.toString()).collect(Collectors.joining(", "));
			System.out.println(slotsString);
		}
	}

	@Override
	public void printRegistrationNumbersForColor(String color) {
		
	}

	@Override
	public void printSlotNumberForRegistrationNumber(String registrationNumber) {
		// TODO Auto-generated method stub

	}

}