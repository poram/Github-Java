/*
 * Assignment : In Class 01
 * File Name: PartTwo.java
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
import java.util.Comparator;
import java.util.HashMap;

public class PartTwo {

	public static void main(String[] args) {
		HashMap<Integer, User> hashMap1 = readFileAtPath("userList1.txt");
		HashMap<Integer, User> hashMap2 = readFileAtPath("userList2.txt");

		hashMap1.keySet().retainAll(hashMap2.keySet());
		for (Integer hashCode : hashMap1.keySet()) {
			System.out.println(hashMap1.get(hashCode).toString());
		}

		System.out.println("Overlapping user count: " + hashMap1.size());
		ArrayList<User> sortedArray = new ArrayList<>(hashMap1.values());
		Collections.sort(sortedArray, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				if (o1.getAge() < o2.getAge()) {
					return -1;
				} else if (o1.getAge() > o2.getAge()) {
					return 1;
				}
				return 0;
			}
		});

		System.out.println("Sorted Duplicate Records");
		for (User user : sortedArray) {
			System.out.println(user.toString());
		}
	}

	public static HashMap<Integer, User> readFileAtPath(String filename) {
		// Lets make sure the file path is not empty or null
		if (filename == null || filename.isEmpty()) {
			System.out.println("Invalid File Path");
			return null;
		}
		String filePath = filename;
		BufferedReader inputStream = null;
		HashMap<Integer, User> hashMap = new HashMap<>();
		// We need a try catch block so we can handle any potential IO errors
		try {
			try {
				inputStream = new BufferedReader(new FileReader(filePath));
				String lineContent = null;
				// Loop will iterate over each line within the file.
				// It will stop when no new lines are found.
				while ((lineContent = inputStream.readLine()) != null) {
					User user = new User(lineContent);
					hashMap.put(user.hashCode(), user);
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
		return hashMap;
	}// end of method
}
