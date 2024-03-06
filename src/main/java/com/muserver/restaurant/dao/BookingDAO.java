package com.muserver.restaurant.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import com.muserver.restaurant.model.Booking;

public class BookingDAO {
	
	private static final List<String> CAT_TIME = Arrays.asList("10:00", "12:00", "14:00", "16:00", "18:00", "20:00");
	private static Map<Integer, Booking> bookingMap = new HashMap<>();
	
	public List<Booking> getAll(){
		return bookingMap.entrySet().stream()
				.map(entry -> entry.getValue())
				.toList();
	}
	
	public List<Booking> getBookingsByDate(Date thisDate) {
		return bookingMap.entrySet().stream()
				.map(entry -> entry.getValue())
				.filter(booking -> DateUtils.isSameDay(booking.getDate(), thisDate))
				.toList();
	}
	
	public List<Booking> getBookingsByDateTime(Date thisDate, String time) {
		return bookingMap.entrySet().stream()
				.map(entry -> entry.getValue())
				.filter(booking -> DateUtils.isSameDay(booking.getDate(), thisDate))
				.filter(booking -> booking.getTime().equals(time))
				.toList();
	}
	
	public Booking getBookingById(Integer id) {
		return bookingMap.get(id);
	}
	
	public boolean addBooking(Booking booking) {
		boolean result = false;
		booking.setId(bookingMap.size()+1); // Assign an id
		if (bookingMap.get(booking.getId()) == null) {			
			bookingMap.put(booking.getId(), booking);
			result = true;
		}
		return result;
	}
	
	public boolean updateBooking(Booking booking) {
		boolean result = false;
		if (bookingMap.get(booking.getId()) != null) {			
			bookingMap.put(booking.getId(), booking);
			result = true;
		}
		return result;
	}
	
	public boolean calcelBooking(Integer id) {
		boolean result = false;
		if (bookingMap.get(id) != null) {
			bookingMap.remove(id);
			result = true;
		}
		return result;
	}
	
	public List<String> getAllAvailableTimes(Date date){
		// Get the already reserved times by date
		List<String> reserved = bookingMap.entrySet().stream()
			.map(entry -> entry.getValue())
			.filter(booking -> DateUtils.isSameDay(booking.getDate(), date))
			.map(booking -> booking.getTime())
			.toList();
		
		// Discard already reserved times
		return CAT_TIME.stream().filter(time -> !reserved.contains(time)).toList();
	}
	
}
