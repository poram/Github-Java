/*
 * Assignment : In Class 01
 * File Name: PartOne.java
 * Student Names: 
 * 		Peter Oram
 * 		Sushmitha Yalla
 * 		Udipta Roy
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class PartOne {
	
	public static void main(String[] args) {
		readFileAtPath("userList1.txt");
	}
	
	public static void readFileAtPath(String filename) {
		// Lets make sure the file path is not empty or null
		if (filename == null || filename.isEmpty()) {
			System.out.println("Invalid File Path");
			return;
		}
		String filePath = filename;
		BufferedReader inputStream = null;
		HashSet<User> hashSet1 = new HashSet<>();
		HashSet<User> hashSet2 = new HashSet<>();
		// We need a try catch block so we can handle any potential IO errors
		try {
			try {
				inputStream = new BufferedReader(new FileReader(filePath));
				String lineContent = null;
				// Loop will iterate over each line within the file.
				// It will stop when no new lines are found.
				while ((lineContent = inputStream.readLine()) != null) {
					System.out.println("Found the line: " + lineContent);
					User user = new User(lineContent);
					if(hashSet1.contains(user)){
						hashSet2.add(user);
					}else{
						hashSet1.add(user);
					}
				}
			}
			// Make sure we close the buffered reader.
			finally {
				if (inputStream != null)
					inputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Duplicate Records");
		for (User user : hashSet2) {
			System.out.println(user.toString());
		}
		ArrayList<User> arrayList = new ArrayList<>(hashSet2);
		Collections.sort(arrayList);
		
		System.out.println("Sorted Duplicate Records");
		for (User user : arrayList) {
			System.out.println(user.toString());
		}
	}// end of method
}
