package com.nttdata.UIClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Orders {
	String _id;
	String customer;
	String product;
	String units;
	String cost; 
	String tracking_number;
	String tracking_status;
	
	public Orders(){
	}
	
	public Orders(String _id, String customer, String product, String units, String cost, String tracking_number, String tracking_status){
		this._id = _id;
		this.customer = customer;
		this.product = product;
		this.units = units;
		this.cost = cost;
		this.tracking_number = tracking_number;
		this.tracking_status = tracking_status;		
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getTracking_number() {
		return tracking_number;
	}

	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}

	public String getTracking_status() {
		return tracking_status;
	}

	public void setTracking_status(String tracking_status) {
		this.tracking_status = tracking_status;
	}	
}
