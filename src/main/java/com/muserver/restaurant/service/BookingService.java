package com.muserver.restaurant.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.muserver.restaurant.dao.BookingDAO;
import com.muserver.restaurant.model.Booking;

public class BookingService {
	
	BookingDAO dao = new BookingDAO();

	public List<Booking> getAll(){
		return dao.getAll();
	}
	
	public Booking getBookingById(Integer id) {
		return dao.getBookingById(id);
	}
	
	public List<Booking> getBookingsByDate(String date) {		
		List<Booking> result = new ArrayList<>();
		if(date != null) {
			try {
				result = dao.getBookingsByDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean addBooking(Booking booking) {
		boolean result = false;
		if(booking.getName() != null && booking.getSeats() > 0 && booking.getSeats() < 25 && 
				booking.getDate() != null && booking.getTime() != null) { // Check if all the values were sent
			if(dao.getBookingsByDateTime(booking.getDate(), booking.getTime()).isEmpty()) { // Check if date and time are available
				result = dao.addBooking(new Booking(booking.getName(), booking.getSeats(), booking.getDate(), booking.getTime()));					
			}
		}
		return result;
	}
	
	public boolean addBooking(String name, int seats, String date, String time) {
		boolean result = false;
		if(name != null && seats > 0 && seats < 25 && date != null && time != null) { // Check if all the values were sent
			try {
				Date pickedDate = DateUtils.parseDate(date, "yyyy-MM-dd");
				if(dao.getBookingsByDateTime(pickedDate, time).isEmpty()) { // Check if date and time are available
					result = dao.addBooking(new Booking(name, seats, pickedDate, time));					
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<String> getAllAvailableTimes(String date) {		
		List<String> result = new ArrayList<>();
		if(date != null) {
			try {
				result = dao.getAllAvailableTimes(DateUtils.parseDate(date, "yyyy-MM-dd"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
