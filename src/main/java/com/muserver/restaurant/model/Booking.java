package com.muserver.restaurant.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Booking {
	private Integer id;
	private String name;
	private int seats;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Mexico_City")
	private Date date;
	private String time;
	
	public Booking() {
		super();
	}

	public Booking(String name, int seats, Date date, String time) {
		super();
		this.name = name;
		this.seats = seats;
		this.date = date;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
