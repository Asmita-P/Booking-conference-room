package com.asm.booking.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.asm.booking.bean.BookingDetails;

public interface BookingService {
	
	public String scheduleBooking(String bookingList);

	public LinkedHashMap<List<BookingDetails>, String> scheduleBooking2(String bookings);

}
