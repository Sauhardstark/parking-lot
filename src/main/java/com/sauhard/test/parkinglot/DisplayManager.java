package com.sauhard.test.parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sauhard.test.parkinglot.domain.Car;

public class DisplayManager {

	public void printStatus(Map<Integer, Car> slotsMap) {
		System.out.printf("%-12s%-19s%s%n", "Slot No.", "Registration No", "Colour");

		List<Integer> parkedSlotsList = new ArrayList<Integer>(slotsMap.keySet());
		Collections.sort(parkedSlotsList);

		printSlotAndCarInformation(slotsMap, parkedSlotsList);
	}

	private void printSlotAndCarInformation(Map<Integer, Car> slotsMap, List<Integer> parkedSlotsList) {
		for (Integer slot : parkedSlotsList) {
			Car car = slotsMap.get(slot);
			String color = car.getColor();
			String registrationNumber = car.getRegistrationNumber();
			System.out.printf("%-12s%-19s%s%n", slot, registrationNumber, color);
		}
	}

	public void printSlotNumbersForColor(Map<Integer, Car> slotsMap, String color) {
		List<Integer> slots = slotsMap.entrySet().parallelStream()
				.filter(i -> i.getValue().getColor().equalsIgnoreCase(color)).map(i -> i.getKey())
				.collect(Collectors.toList());
		if (null == slots || slots.isEmpty()) {
			System.out.println("Not found");
		} else {
			Collections.sort(slots);
			String slotsString = slots.stream().map(i -> i.toString()).collect(Collectors.joining(", "));
			System.out.println(slotsString);
		}
	}

	public void printRegistrationNumersForColor(Map<Integer, Car> slotsMap, String color) {
		List<String> registrationNumbers = slotsMap.entrySet().stream()
				.filter(i -> i.getValue().getColor().equalsIgnoreCase(color))
				.map(i -> i.getValue().getRegistrationNumber()).collect(Collectors.toList());
		if (null == registrationNumbers || registrationNumbers.isEmpty()) {
			System.out.println("Not found");
		} else {
			String registrationNumbersString = registrationNumbers.stream().map(i -> i.toString())
					.collect(Collectors.joining(", "));
			System.out.println(registrationNumbersString);
		}
	}

	public void printSlotNumberForRegistrationNumber(Map<Integer, Car> slotsMap, String registrationNumber) {
		Optional<Entry<Integer, Car>> slot = slotsMap.entrySet().parallelStream()
				.filter(i -> i.getValue().getRegistrationNumber().equals(registrationNumber)).findAny();
		String resultString = slot.isPresent() ? slot.get().getValue().getSlotNumber().toString() : "Not found";
		System.out.println(resultString);
	}

}