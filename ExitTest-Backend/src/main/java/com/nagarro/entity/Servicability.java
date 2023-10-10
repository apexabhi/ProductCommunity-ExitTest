//Servicability Entity
package com.nagarro.entity;
//importing packages
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Servicability {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sId;
	private int pincode;
	private int days;
	@ManyToOne(fetch = FetchType.EAGER)
	private Product product;
	public Servicability() {
		super();
	}
	public Servicability(int sId, int pincode, int days, Product product) {
		super();
		this.sId = sId;
		this.pincode = pincode;
		this.days = days;
		this.product = product;
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
