package com.tcvm.contoller;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.runners.MockitoJUnitRunner;

import com.tcvm.controller.CustomScanner;

import java.util.Scanner;

public class CustomScannerTest {

	@Test
	public void getInputString() {

		String input = "add 5";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		CustomScanner obj = new CustomScanner(new Scanner(System.in));

		String s = obj.getInputString();

		assertEquals("add 5", s);

	}

	@Test
	public void getInputDouble() {

		String input = "5.5";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		CustomScanner obj = new CustomScanner(new Scanner(System.in));
		Double s = obj.getInputDouble(); 

		assertEquals(new Double(5.5), s);

	}

	@Test
	public void getInputInteger() {

		String input = "5";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		CustomScanner obj = new CustomScanner(new Scanner(System.in));
		Integer s = obj.getInputInteger();

		assertEquals(new Integer(5), s);

	}
}
