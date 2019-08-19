package com.itheima;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GeneralMethod {

	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
				}
			}
		return true;
	}
	
	public static String getUser(int n) throws IOException {
		ArrayList<Account> arr = new ArrayList<Account>();
		BufferedReader br = new BufferedReader(new FileReader("account.txt"));
		String line;
		
		while((line=br.readLine())!=null){
			String[] strArray = line.split(",");
			Account a = new Account();
			a.setUsername(strArray[0]);
			a.setPassword(strArray[1]);
			arr.add(a);
		}
		
		br.close();
		Account a = arr.get(n);
		return a.getUsername();
	}
	
	public static void writeLog(StringBuilder sbLog) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Log.txt"));
		BufferedWriter bwLog = new BufferedWriter(new FileWriter("Log.txt", true));
		String line;
		if((line=br.readLine())!=null){
			bwLog.newLine();
		}
		
		bwLog.write(sbLog.toString());
		bwLog.close();
	}
	
	public static String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		return df.format(System.currentTimeMillis());
	}
	
	public static void readAccountList(String filename, ArrayList<Account> arr) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		
		while((line=br.readLine())!=null){
			String[] strArray = line.split(",");
			Account a = new Account();
			a.setUsername(strArray[0]);
			a.setPassword(strArray[1]);
			arr.add(a);
		}
		
		br.close();
	}
	
	public static void readData(String filename, ArrayList<Product> arr) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("product.txt"));
		String line;
		
		while((line=br.readLine())!=null){
			String[] strArray = line.split(",");
			Product p = new Product();
			p.setName(strArray[0]);
			p.setPrice(strArray[1]);
			p.setStock(strArray[2]);
			arr.add(p);
		}
		
		br.close();
	}
	
	public static void writeData(String filename, ArrayList<Product> arr) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("product.txt"));
		for(int x=0; x<arr.size(); x++) {
			Product p = arr.get(x);
			StringBuilder sb = new StringBuilder();
			sb.append(p.getName()).append(",").append(p.getPrice()).append(",").append(p.getStock());
			if(x == arr.size()-1) {
				bw.write(sb.toString());
			}else {
				bw.write(sb.toString());
				bw.newLine();
			}
		}
		bw.close();
	}
}
