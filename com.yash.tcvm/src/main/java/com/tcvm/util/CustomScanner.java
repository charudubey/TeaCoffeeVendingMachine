package com.tcvm.util;

	import java.util.Scanner;

    public class CustomScanner {

        private final Scanner scanner;

        public CustomScanner()
        {
           this(new Scanner(System.in));
        }

        CustomScanner(Scanner scanner)
        {
            this.scanner  = scanner;
        }

        public String getInputString() {

            return scanner.next();
        }
        
        public Integer getInputInteger() {

            return scanner.nextInt();
        }
        
        public Double getInputDouble() {

            return scanner.nextDouble();
        }
    }
	
