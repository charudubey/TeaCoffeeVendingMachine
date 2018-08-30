package com.tcvm.util;

	import java.util.Scanner;

    public class InputOutput {

        private final Scanner scanner;

        public InputOutput()
        {
           this(new Scanner(System.in));
        }

        InputOutput(Scanner scanner)
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
	
