package com.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author neeraj
 *
 */
@Embeddable
public class Address {
	
	@Column(name="STREET_NAME")
	private String streetName;
	
	@Column(name="CITY_NAME")
	private String cityName;
	
	@Column(name="STATE_NAME")
	private String satateName;
	
	@Column(name="ZIP_CODE")
	private String zipCode;

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getSatateName() {
		return satateName;
	}

	public void setSatateName(String satateName) {
		this.satateName = satateName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Address [streetName=" + streetName + ", cityName=" + cityName + ", satateName=" + satateName
				+ ", zipCode=" + zipCode + "]";
	}
	
	
}
