package com.sauhard.test.parkinglot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class ParkingLotTest {

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
	public void parkShouldAssignSlotForEmptyLot() throws IOException {
		outContent.reset();
		ParkingLot parkingLot = new ParkingLotImpl(5);
		parkingLot.park("Test-Reg", "Black");
		String actual = outContent.toString().trim();
		assertEquals("Allocated slot number: 1", actual);
	}
	
	@Test
	public void parkShouldNotAssignSlotForFullLot() throws IOException {
		outContent.reset();
		ParkingLot parkingLot = new ParkingLotImpl(0);
		parkingLot.park("Test-Reg", "Black");
		String actual = outContent.toString().trim();
		assertEquals("Sorry, parking lot is full", actual);
	}
	
	@Test
	public void parkShouldAssignSlotForMultipleCars() throws IOException {
		outContent.reset();
		ParkingLot parkingLot = new ParkingLotImpl(5);
		parkingLot.park("Test-Reg", "Black");
		parkingLot.park("Test-Reg", "White");
		String actual = outContent.toString().trim();
		assertEquals("Allocated slot number: 1\nAllocated slot number: 2", actual);
	}
	
	@Test
	public void parkShouldEmptySlotForLeave() throws IOException {
		outContent.reset();
		ParkingLot parkingLot = new ParkingLotImpl(5);
		parkingLot.park("Test-Reg", "Black");
		parkingLot.park("Test-Reg", "White");
		outContent.reset();
		parkingLot.leave(1);
		String actual = outContent.toString().trim();
		assertEquals("Slot number 1 is free", actual);
	}

}