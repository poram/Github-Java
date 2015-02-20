/**
 * Assignment: In Class 06
 * FileName: Photo.Java
 * Names:
 * 	Udipta Roy
 * 	Sushmitha Yalla	
 *	Peter Oram 
 */

package com.example.inclass07;

import java.io.Serializable;

public class Photo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long _id;
	private String name;
	private String url;
	private String fullName;

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return name;
	}

}
