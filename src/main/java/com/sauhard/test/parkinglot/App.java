package com.sauhard.test.parkinglot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

	private static final String park = "park";
	private static final String leave = "leave";
	private static final String status = "status";
	private static final String exit = "exit";
	private static final String registrationNoColor = "registration_numbers_for_cars_with_colour";
	private static final String slotColor = "slot_numbers_for_cars_with_colour";
	private static final String slotRegistrationNo = "slot_number_for_registration_number";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		if (args.length > 0) {
			String fileName = args[0];
			String filePath = "./target/".concat(fileName);
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				manageInput(br);
			}
		} else {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			manageInput(br);
		}
	}

	private static void manageInput(BufferedReader br) throws IOException {
		String line = br.readLine();
		Integer size = Integer.parseInt(line.split("\\s+")[1]);
		ParkingLot parkingLot = new ParkingLotImpl(size);
		while ((line = br.readLine()) != null) {
			String[] words = line.split("\\s+");
			String identifier = words[0];
			if (identifier.equals(park)) {
				parkingLot.park(words[1], words[2]);
			} else if (identifier.equals(leave)) {
				parkingLot.leave(Integer.parseInt(words[1]));
			} else if (identifier.equals(status)) {
				parkingLot.status();
			} else if (identifier.equals(registrationNoColor)) {
				parkingLot.printRegistrationNumbersForColor(words[1]);
			} else if (identifier.equals(slotColor)) {
				parkingLot.printSlotNumbersForColor(words[1]);
			} else if (identifier.equals(slotRegistrationNo)) {
				parkingLot.printSlotNumberForRegistrationNumber(words[1]);
			} else if (identifier.equals(exit)) {
				return;
			}
		}
	}

}
