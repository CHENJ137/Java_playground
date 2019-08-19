package com.itheima;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManagement {
	public static void PMS(int n) throws IOException {
		ArrayList<Product> array = new ArrayList<Product>();
		
		System.out.println("------Welcome to Product Management System!------");
		
		while(true){
			System.out.println("1.Check Your Products");
			System.out.println("2.Add Product Info");
			System.out.println("3.Delete Product Info");
			System.out.println("4.Update Product Stock");
			System.out.println("5.Update Product Price");
			System.out.println("6.Change Password");
			System.out.println("7.Log Out");
			System.out.println("Please choose an option:");
			
			Scanner sc = new Scanner(System.in);
			String choiceString = sc.nextLine();
			
			switch(choiceString){
				case "1":
					findAllProduct(n);
					break;
				case "2":
					addProduct(n);
					break;
				case "3":
					deleteProduct(n);
					break;
				case "4":
					updateProductStock(n);
					break;
				case "5":
					updateProductPrice(n);
					break;
				case "6":
					chagePassword(n);
					break;
				case "7":
					String[] a = new String[0];
					Login.main(a);
				default:
					System.out.println("Invalid option. Please try again.");
					break;
			}
		}
	}
	
	public static void PMSadmin(int n) throws IOException {
		ArrayList<Product> array = new ArrayList<Product>();
		
		System.out.println("------Welcome to Product Management System!------");
		
		while(true){
			System.out.println("1.Check Your Products");
			System.out.println("2.Add Product Info");
			System.out.println("3.Delete Product Info");
			System.out.println("4.Update Product Stock");
			System.out.println("5.Update Product Price");
			System.out.println("6.Change Password");
			System.out.println("7.Check Log");
			System.out.println("8.Log Out");
			System.out.println("Please choose an option:");
			
			Scanner sc = new Scanner(System.in);
			String choiceString = sc.nextLine();
			
			switch(choiceString){
				case "1":
					findAllProduct(n);
					break;
				case "2":
					addProduct(n);
					break;
				case "3":
					deleteProduct(n);
					break;
				case "4":
					updateProductStock(n);
					break;
				case "5":
					updateProductPrice(n);
					break;
				case "6":
					chagePassword(n);
					break;
				case "7":
					checkLog();
					break;
				case "8":
					String[] a = new String[3];
					Login.main(a);
				default:
					System.out.println("Invalid option. Please try again.");
					break;
			}
		}
	}
	
	private static void checkLog() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Log.txt"));
		String line;
		
		while((line=br.readLine())!=null){
			System.out.println(line);
		}
		
		br.close();
	}

	private static void chagePassword(int n) throws IOException {
		//注意，希望循环某个功能，但是可能会有if，可以先建立一个布尔变量，用循环包装整个流程，用if（该布尔变量）包装功能语句
		ArrayList<Account> arr = new ArrayList<Account>();
		String username, password;
		
		GeneralMethod.readAccountList("account.txt", arr);
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter new password:");
			String newPassword = sc.nextLine();
			boolean flag = true;
			
			if (GeneralMethod.isNumeric(newPassword)) {
				BufferedWriter bw = new BufferedWriter(new FileWriter("account.txt"));
				arr.get(n).setPassword(newPassword);
				for(int x=0; x<arr.size(); x++) {
					if(flag) {
						StringBuilder sb = new StringBuilder();
						sb.append(arr.get(x).getUsername()).append(",").append(arr.get(x).getPassword());
						
						if(x == arr.size()-1) {
							bw.write(sb.toString());
						}else {
							bw.write(sb.toString());
							bw.newLine();
						}
					}
				}
			bw.close();
			System.out.println("Password changed.");
			break;
			}else {
				System.out.println("Invalid input. Please try again.");
			}
		}
	}

	private static void updateProductPrice(int n) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<Product> arr = new ArrayList<Product>();
		Product updateP = null;
		
		GeneralMethod.readData("product.txt", arr);
		
		System.out.println("Please enter the product you want to update:");
		String name = sc.nextLine();
		
		boolean flag = false;
		
		for (int x=0; x<arr.size(); x++) {
			if (arr.get(x).getName().equals(name)) {
					flag = true;
					updateP = arr.get(x);
					break;
				}
			}
		
		if (flag == false) {
			System.out.println("This product is not in database. Please try again.");
		}
		
		System.out.println("Please enter new price:");
		String price = sc.nextLine();
		if(isNumeric(price) && Integer.parseInt(price)>0) {
			updateP.setPrice(price);

			if (flag) {
				BufferedWriter bw = new BufferedWriter(new FileWriter("product.txt"));
				GeneralMethod.writeData("product.txt", arr);

				System.out.println("Update successfully!");
				StringBuilder sbLog = new StringBuilder();
				sbLog.append(GeneralMethod.getTime()).append("\t").append(GeneralMethod.getUser(n)).append(" updated product ").append(name).append("'s price.");
				GeneralMethod.writeLog(sbLog);
			}
		}else {
			System.out.println("Invalid input. Please try again.");
		}
		
//		array.get(index).setPrice(price);
//		System.out.println("Update successfully!");
	}

	private static void updateProductStock(int n) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<Product> arr = new ArrayList<Product>();
		String stock = "";
		Product updateP = null;
		
		GeneralMethod.readData("product.txt", arr);

		System.out.println("Please enter the product you want to update:");
		String name = sc.nextLine();
		
		boolean flag = false;
		
		for (int x=0; x<arr.size(); x++) {
			if (arr.get(x).getName().equals(name)) {
					flag = true;
					updateP = arr.get(x);
					stock = updateP.getStock();
					break;
				}
			}
		
		if (flag == false) {
			System.out.println("This product is not in database. Please try again.");
		}else {
		
//		if (index == -1) {
//			System.out.println("This product is not in database. Please try again.");
//			return;
//		}
		
			System.out.println("1.Add stock");
			System.out.println("2.Remove stock");
			System.out.println("Please enter your option:");
			//switch语句里的输入的选择数据类型，用char型最好
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				System.out.println("How many stocks do you want to add?");
				String add = sc.nextLine();
				int stk1 = Integer.parseInt(stock);
				if(isNumeric(add)) {
					int intAdd = Integer.parseInt(add);
					stk1 += intAdd;
					if(stk1 >= 0) {
						updateP.setStock(stk1+"");
						System.out.println("Update successfully!");
					}else {
						System.out.println("Invalid input. Please try again.");
					}
				}else {
					System.out.println("Invalid input. Please try again.");
				}
				break;
			case "2":
				System.out.println("How many stocks do you want to remove?");
				String remove = sc.nextLine();
				int stk2 = Integer.parseInt(stock);
				if(isNumeric(remove)) {
					int intRemove = Integer.parseInt(remove);
					stk2 -= intRemove;
						if(stk2 >= 0) {
							updateP.setStock(stk2+"");
							System.out.println("Update successfully!");
						}else {
							System.out.println("Invalid input. Please try again.");
						}
					}else {
						System.out.println("Invalid input. Please try again.");
					}
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				return;	
			}
		}
		
		if (flag) {
			GeneralMethod.writeData("product.txt", arr);
			StringBuilder sbLog = new StringBuilder();
			sbLog.append(GeneralMethod.getTime()).append("\t").append(GeneralMethod.getUser(n)).append(" added product ").append(name).append("'s stock.");
			GeneralMethod.writeLog(sbLog);
		}
	}

	private static void deleteProduct(int n) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<Product> arr = new ArrayList<Product>();
		GeneralMethod.readData("product.txt", arr);

		
		System.out.println("Please enter the product you want to delete:");
		String name = sc.nextLine();
		
		boolean flag = false;
		
		for (int x=0; x<arr.size(); x++) {
			if (arr.get(x).getName().equals(name)) {
					flag = true;
					arr.remove(arr.get(x));
					System.out.println("Delete successfully!");
					break;
				}
			}
		
		if (flag == false) {
			System.out.println("This product is not in database. Please try again.");
		}
				
		if (flag) {
			GeneralMethod.writeData("product.txt", arr);
			
			StringBuilder sbLog = new StringBuilder();
			sbLog.append(GeneralMethod.getTime()).append("\t").append(GeneralMethod.getUser(n)).append(" removed product ").append(name).append(".");
			GeneralMethod.writeLog(sbLog);
		}
			
//		int index = -1;
//		for (int x=0; x<array.size(); x++) {
//			Product p = array.get(x);
//			if (p.getName().equals(name)) {
//				index = x;
//				break;
//			}
//		}
		
//		if (flag) {
//			System.out.println("This product is not in database. Please try again.");
//		}else {
//			array.remove();
//			System.out.println("Delete successfully!");
//		}
	}

	private static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
				}
			}
		return true;
	}

	public static void addProduct(int n) throws IOException {
		Scanner sc = new Scanner(System.in);
		String name;
		BufferedWriter bw = new BufferedWriter(new FileWriter("product.txt", true));
		ArrayList<Product> arr = new ArrayList<Product>();
		GeneralMethod.readData("product.txt", arr);
		
		while(true) {
			System.out.println("Please enter product name:");
			name = sc.nextLine();
			boolean flag = false;
			
			for (int x=0; x<arr.size(); x++) {
				if (arr.get(x).getName().equals(name)) {
					flag = true;
					break;
				}
			}
			
//			for (int x=0; x<array.size(); x++) {
//				if (name.equals(array.get(x).getName())) {
//					flag = true;
//					break;
//				}
//			}
			
			if (flag) {
				System.out.println("The product is already in database. Please try again.");
			} else {
				break;
			}
		}
		
		System.out.println("Please enter product price:");
		String price = sc.nextLine();
		System.out.println("Please enter product stock:");
		String stock = sc.nextLine();
		
		if (isNumeric(price) && isNumeric(stock)) {
			StringBuilder sb = new StringBuilder();
			sb.append(name).append(",").append(price).append(",").append(stock);
			if(arr.size() != 0) {
				bw.newLine();
				bw.write(sb.toString());
			}else {
				bw.write(sb.toString());
			}
		
			bw.close();
			
			StringBuilder sbLog = new StringBuilder();
			sbLog.append(GeneralMethod.getTime()).append("\t").append(GeneralMethod.getUser(n)).append(" added product ").append(name).append(".");

			GeneralMethod.writeLog(sbLog);
		
			System.out.println("Added successfully!");
		}else {
			System.out.println("Invalid input. Please try again.");
		}
		
//		Product p = new Product();
//		p.setName(name);
//		p.setPrice(price);
//		p.setStock(stock);	
//		String p = name+"\t\t"+price+"\t"+stock;
	}
	
	public static void findAllProduct(int n) throws IOException {
		ArrayList<Product> arr = new ArrayList<Product>();
		GeneralMethod.readData("product.txt", arr);
		
		StringBuilder sbLog = new StringBuilder();
		sbLog.append(GeneralMethod.getTime()).append("\t").append(GeneralMethod.getUser(n)).append(" checked products list.");

		GeneralMethod.writeLog(sbLog);
		
		if (arr.size() == 0) {
			System.out.println("There is no product info in database.");
			return;
		}
		
		System.out.printf("%-20s%-12s%s\n","Product Name","Price","Stock");
//		System.out.println("Product Name\tPrice\tStock");
		for (int x=0; x<arr.size(); x++) {
			Product p = arr.get(x);
			System.out.printf("%-20s%-12s%s\n",p.getName(),p.getPrice(),p.getStock());
//			System.out.println(p.getName()+"\t\t"+p.getPrice()+"\t"+p.getStock());
		}
	}
}
