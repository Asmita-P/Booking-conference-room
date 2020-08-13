package com.asm.booking.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asm.booking.bean.BookingDetails;
import com.asm.booking.service.BookingServiceImpl;

@Controller
public class BookingController {

	@Autowired
	BookingServiceImpl bookingServiceImpl;

	@RequestMapping(value = "/showBookingForm", method = RequestMethod.GET)
	public ModelAndView showBookingForm() {
		return new ModelAndView("bookingForm", "bookingDetails", new BookingDetails());
	}

	@RequestMapping(value = "/scheduleBooking", method = RequestMethod.POST)
	public ModelAndView scheduleBooking(@RequestParam String bookingList) {
		System.out.println("bookingList === " + bookingList);
		LinkedHashMap<List<BookingDetails>, String> map = bookingServiceImpl.scheduleBooking2(bookingList);
		return new ModelAndView("scheduleBooking", "map", map);
	}
}
