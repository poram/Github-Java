/*
 * Assignment 3
 * FileName: Student.Java
 * Names:
 * 	Udipta Roy
 *  Pete Oram
 *  Sushmitha Yalla
 */

package com.example.inclass03;

import java.io.Serializable;

public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String emailAddress;
	private String language;
	private boolean accountState;
	private int mood;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public boolean isAccountState() {
		return accountState;
	}
	public void setAccountState(boolean accountState) {
		this.accountState = accountState;
	}
	public int getMood() {
		return mood;
	}
	public void setMood(int mood) {
		this.mood = mood;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
