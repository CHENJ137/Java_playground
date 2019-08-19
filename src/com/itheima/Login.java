package com.itheima;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("account.txt"));
		Scanner sc = new Scanner(System.in);

		System.out.println("----------Welcome---------");
		
		while(true){
			System.out.println("1.Login");
			System.out.println("2.Creat New Account");
			System.out.println("3.Exit");
			System.out.println("Please choose an option:");
			String choiceString = sc.nextLine();
			
			switch(choiceString){
			case "1":
					ArrayList<Account> arr = new ArrayList<Account>();
					String line;
					String username, password;
					
					while((line=br.readLine())!=null){
						String[] strArray = line.split(",");
						Account a = new Account();
						a.setUsername(strArray[0]);
						a.setPassword(strArray[1]);
						arr.add(a);
					}
					
					br.close();
					
					for (int x=0; x<3; x++) {
					
						System.out.println("Please input your username:");
						username = sc.nextLine();
						System.out.println("Please input your password:");
						password = sc.nextLine();
						
						for (int n=0; n<arr.size(); n++) {
							Account a = arr.get(n);
							
							if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
								System.out.println("You've logged in successfully.");
								
								if(username.equals("Admin")) {
									ProductManagement.PMSadmin(n);
								}else {
									ProductManagement.PMS(n);
								break;
								}
							}
							
						}
							
						if (x == 2) {
							System.out.println("Your account is locked.");
							System.exit(0);
						} else if (x == 1) {
							System.out.println("Wrong username or password, you still have one more chance.");
						} else {
							System.out.println("Wrong username or password, you still have two more chances.");
						}
					}
				break;
			case "2":
				creatAccount();
				break;
			case "3":
				System.out.println("Thank you for using PMS.");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		
		}
	}
	
	public static void creatAccount() throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new FileWriter("account.txt", true));
		ArrayList<Account> arr = new ArrayList<Account>();
		String username, password;
		
		GeneralMethod.readAccountList("account.txt", arr);
		
		while(true) {
			System.out.println("Please enter username:");
			username = sc.nextLine();
			boolean flag = false;
			
			for (int n=0; n<arr.size(); n++) {
				if (arr.get(n).getUsername().equals(username)) {
					flag = true;
					break;
				}
			}
			
			if (flag) {
				System.out.println("The account is exist. Please try again.");
			} else {
				break;
			}
		}
		
		while(true) {
			System.out.println("Please enter password:");
			password = sc.nextLine();
			boolean flag = false;
			
			if (GeneralMethod.isNumeric(password)) {
				flag = true;
				StringBuilder sb = new StringBuilder();
				sb.append(username).append(",").append(password);
			
				bw.newLine();
				bw.write(sb.toString());
				bw.close();
			}
			
			if(flag) {
				System.out.println("Added successfully!");
				break;
			}else {
				System.out.println("Invalid input. Please try again.");
			}
		}
	}
}
