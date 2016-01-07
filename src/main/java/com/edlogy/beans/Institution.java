package com.edlogy.beans;

import java.io.Serializable;

public class Institution implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String name;
	String code;
	String category;
	String address;
	String townName;
	String wardName;
	String schoolArea;
 
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getSchoolArea() {
		return schoolArea;
	}
	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}
	
	@Override
	public String toString() {
		return "Institution [name=" + name + ", code=" + code + ", category="
				+ category + ", address=" + address + ", townName=" + townName
				+ ", wardName=" + wardName + ", schoolArea=" + schoolArea
				+ ", getName()=" + getName() + ", getCode()=" + getCode()
				+ ", getCategory()=" + getCategory() + ", getAddress()="
				+ getAddress() + ", getTownName()=" + getTownName()
				+ ", getWardName()=" + getWardName() + ", getSchoolArea()="
				+ getSchoolArea() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
