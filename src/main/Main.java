package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Legal;
import model.entities.Natural;
import model.entities.Payer;

public class Main {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		List<Payer> payers = new ArrayList<>();
		try (Scanner input = new Scanner(System.in)) {
			System.out.print("Enter the number of tax payers : ");
			Integer taxPayersLength = input.nextInt();
			
			for(Integer i = 0; i < taxPayersLength; i++) {
				
				System.out.printf("Tax payer #%d data: \n", i+1);
				System.out.print("Natural or Legal (n/l)? ");
				Character type = input.next().charAt(0);
				input.nextLine();
				System.out.print("Name : ");
				String name = input.nextLine();
				System.out.print("Anual Income : ");
				Double anulaIncome = input.nextDouble();
				
				if(type == 'n') {
					System.out.print("Health Expenditures : ");
					Double health = input.nextDouble();
					Payer natural = new Natural(name, anulaIncome, health); 
					payers.add(natural);
				}
				if (type == 'l') {
					System.out.print("Number of Employees : ");
					Integer numberEmp = input.nextInt();
					Payer legal = new Legal(name, anulaIncome, numberEmp);
					payers.add(legal);
				}
			}
			
			 System.out.println();
			 showTaxes(payers);
			 System.out.println();
			 
			 System.out.print("Type here the path to save this information in file : ");
			 input.nextLine();
			 String path = input.next();
			 
		 	File sourceFile = new File(path);
			
			new File(sourceFile + "\\response").mkdir();
			
			String targetFile = sourceFile + "\\response\\response.csv";
			 
			 try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))){
				 infoTaxes(payers, bw, targetFile);
			} catch (IOException e) {
				System.out.println("File Error" + e.getMessage());
			}
			
		} catch (InputMismatchException e) {
			System.out.println("Error : " + e.getMessage());
		}
	}
	
	public static void showTaxes(List<Payer> payers) {
		System.out.println("TAXES PAID");
		 for(Payer payer : payers) {
			 System.out.println(payer.getName() + " : $ " + String.format("%.2f", payer.tax()));
		 }
	}
	
	public static Double sumTaxes(List<Payer> payers) {
		Double sum = 0.0;
		for(Payer payer : payers) { sum += payer.tax(); }
		return sum;
	}
	
	public static void infoTaxes(List<Payer> payers, BufferedWriter bw, String targetFile) {
		try {
			 bw.write("TAXES PAID: ");
			 bw.newLine();
			 bw.newLine();
			 for(Payer payer : payers) {
				 bw.write(payer.getName() + ": $ " + String.format("%.2f", payer.tax()));
				 bw.newLine();
			 }
			 bw.newLine();
			 bw.write("TOTAL TAXES: $ " + String.format("%.2f", sumTaxes(payers)));
			 System.out.println(targetFile + "  CREATED !!");
		} catch (IOException e) {
			System.out.println("IO ERROR" + e.getMessage());
		}
	}
}
