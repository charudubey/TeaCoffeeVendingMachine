package com.tcvm.controller;

import java.util.Scanner;

public class CustomScanner {

	private Scanner scanner;

	public CustomScanner() {
		this(new Scanner(System.in));
	}

	public	CustomScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public String getInputString() {
		return scanner.nextLine();

	}

	public Integer getInputInteger() {

		return scanner.nextInt();
	}

	public Double getInputDouble() {

		return scanner.nextDouble();
	}
	
}
