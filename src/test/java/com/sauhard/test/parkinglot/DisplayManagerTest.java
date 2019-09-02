package com.sauhard.test.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sauhard.test.parkinglot.domain.Car;

@RunWith(JUnitPlatform.class)
public class DisplayManagerTest {

	private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static final PrintStream originalOut = System.out;

	@BeforeAll
	public static void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@AfterAll
	public static void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	public void displayManagerCheckForEmptyMap() throws IOException {
		outContent.reset();
		DisplayManager displayManager = new DisplayManager();
		Map<Integer, Car> slotsMap = new HashMap<>();
		displayManager.printStatus(slotsMap);
		String actual = outContent.toString().trim();
		assertEquals("Slot No.    Registration No    Colour", actual);
	}

	@Test
	public void displayManagerShouldPrintAllInformationForStatus() throws IOException {
		outContent.reset();
		DisplayManager displayManager = new DisplayManager();
		Map<Integer, Car> slotsMap = new HashMap<>();
		slotsMap.put(1, new Car("KA-01-HH-1234", "White", 1));
		slotsMap.put(2, new Car("KA-01-HH-9999", "Black", 2));
		slotsMap.put(3, new Car("KA-01-BB-0001", "Red", 3));
		displayManager.printStatus(slotsMap);
		String actual = outContent.toString().trim();
		assertEquals("Slot No.    Registration No    Colour\n" + "1           KA-01-HH-1234      White\n"
				+ "2           KA-01-HH-9999      Black\n" + "3           KA-01-BB-0001      Red", actual);
	}

	@Test
	public void displayManagerShouldPrintAllSlotsForColor() throws IOException {
		outContent.reset();
		DisplayManager displayManager = new DisplayManager();
		Map<Integer, Car> slotsMap = new HashMap<>();
		slotsMap.put(1, new Car("KA-01-HH-1234", "White", 1));
		slotsMap.put(2, new Car("KA-01-HH-9999", "Black", 2));
		slotsMap.put(3, new Car("KA-01-BB-0001", "White", 3));
		displayManager.printSlotNumbersForColor(slotsMap, "White");
		String actual = outContent.toString().trim();
		assertEquals("1, 3", actual);
	}

	@Test
	public void displayManagerShouldPrintNotFoundSlotForWrongColor() throws IOException {
		outContent.reset();
		DisplayManager displayManager = new DisplayManager();
		Map<Integer, Car> slotsMap = new HashMap<>();
		slotsMap.put(1, new Car("KA-01-HH-1234", "White", 1));
		slotsMap.put(2, new Car("KA-01-HH-9999", "Black", 2));
		slotsMap.put(3, new Car("KA-01-BB-0001", "White", 3));
		displayManager.printSlotNumbersForColor(slotsMap, "Green");
		String actual = outContent.toString().trim();
		assertEquals("Not found", actual);
	}

	@Test
	public void displayManagerShouldPrintAllRegistrationNosForColor() throws IOException {
		outContent.reset();
		DisplayManager displayManager = new DisplayManager();
		Map<Integer, Car> slotsMap = new HashMap<>();
		slotsMap.put(1, new Car("KA-01-HH-1234", "White", 1));
		slotsMap.put(2, new Car("KA-01-HH-9999", "Black", 2));
		slotsMap.put(3, new Car("KA-01-BB-0001", "White", 3));
		displayManager.printRegistrationNumersForColor(slotsMap, "White");
		String actual = outContent.toString().trim();
		assertEquals("KA-01-HH-1234, KA-01-BB-0001", actual);
	}

	@Test
	public void displayManagerShouldPrintNotFoundForWrongColor() throws IOException {
		outContent.reset();
		DisplayManager displayManager = new DisplayManager();
		Map<Integer, Car> slotsMap = new HashMap<>();
		slotsMap.put(1, new Car("KA-01-HH-1234", "White", 1));
		slotsMap.put(2, new Car("KA-01-HH-9999", "Black", 2));
		slotsMap.put(3, new Car("KA-01-BB-0001", "White", 3));
		displayManager.printRegistrationNumersForColor(slotsMap, "Red");
		String actual = outContent.toString().trim();
		assertEquals("Not found", actual);
	}

	@Test
	public void displayManagerPrintSlotNumberForRegistrationNumber() throws IOException {
		outContent.reset();
		DisplayManager displayManager = new DisplayManager();
		Map<Integer, Car> slotsMap = new HashMap<>();
		slotsMap.put(1, new Car("KA-01-HH-1234", "White", 1));
		slotsMap.put(2, new Car("KA-01-HH-9999", "Black", 2));
		slotsMap.put(3, new Car("KA-01-BB-0001", "White", 3));
		displayManager.printSlotNumberForRegistrationNumber(slotsMap, "KA-01-BB-0001");
		String actual = outContent.toString().trim();
		assertEquals("3", actual);
	}

	@Test
	public void displayManagerPrintNotFoundForWrongRegistrationNumber() throws IOException {
		outContent.reset();
		DisplayManager displayManager = new DisplayManager();
		Map<Integer, Car> slotsMap = new HashMap<>();
		slotsMap.put(1, new Car("KA-01-HH-1234", "White", 1));
		slotsMap.put(2, new Car("KA-01-HH-9999", "Black", 2));
		slotsMap.put(3, new Car("KA-01-BB-0001", "White", 3));
		displayManager.printSlotNumberForRegistrationNumber(slotsMap, "MH-01-BB-0001");
		String actual = outContent.toString().trim();
		assertEquals("Not found", actual);
	}

}