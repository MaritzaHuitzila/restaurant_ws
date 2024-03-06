package com.muserver.restaurant;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.muserver.restaurant.model.Booking;
import com.muserver.restaurant.service.BookingService;

@Path("/booking")
public class BookingHandler {
	
	BookingService service = new BookingService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Booking> getAll() {
		return service.getAll();
	}
	
	@GET
	@Path("/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Booking> searchByDate(@PathParam("date") String date) {
		return service.getBookingsByDate(date);
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean addBooking(Booking booking) {
		return service.addBooking(booking);
	}
	
	@GET
	@Path("/times/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getAvailableTimesByDate(@PathParam("date") String date) {
		return service.getAllAvailableTimes(date);
	}
	
}
