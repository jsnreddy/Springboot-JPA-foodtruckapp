/**
 * 
 */
package com.jsn.app;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Surendranath Reddy
 *
 */

@Entity
public class FoodTruck {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String locationid;
	private String applicant;
	private String facilitytype;
	private String cnn;

	@Column(length = 500)
	private String locationdescription;
	private String address;
	private String blocklot;
	private String block;
	private String lot;
	private String permit;
	private String status;

	@Column(length = 500)
	private String fooditems;
	private Double x;
	private Double y;
	private Double latitude;
	private Double longitude;

	@Column(length = 300)
	private String schedule;
	private String dayshours;
	private Date noisent;
	private Date approved;
	private Date received;
	private String priorpermit;
	private Date expirationdate;
	private String location;

	public FoodTruck() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getFacilitytype() {
		return facilitytype;
	}

	public void setFacilitytype(String facilitytype) {
		this.facilitytype = facilitytype;
	}

	public String getCnn() {
		return cnn;
	}

	public void setCnn(String cnn) {
		this.cnn = cnn;
	}

	public String getLocationdescription() {
		return locationdescription;
	}

	public void setLocationdescription(String locationdescription) {
		this.locationdescription = locationdescription;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBlocklot() {
		return blocklot;
	}

	public void setBlocklot(String blocklot) {
		this.blocklot = blocklot;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFooditems() {
		return fooditems;
	}

	public void setFooditems(String fooditems) {
		this.fooditems = fooditems;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getDayshours() {
		return dayshours;
	}

	public void setDayshours(String dayshours) {
		this.dayshours = dayshours;
	}

	public Date getNoisent() {
		return noisent;
	}

	public void setNoisent(Date noisent) {
		this.noisent = noisent;
	}

	public Date getApproved() {
		return approved;
	}

	public void setApproved(Date approved) {
		this.approved = approved;
	}

	public Date getReceived() {
		return received;
	}

	public void setReceived(Date received) {
		this.received = received;
	}

	public String getPriorpermit() {
		return priorpermit;
	}

	public void setPriorpermit(String priorpermit) {
		this.priorpermit = priorpermit;
	}

	public Date getExpirationdate() {
		return expirationdate;
	}

	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
