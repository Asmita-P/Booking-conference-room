package com.asm.booking.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.asm.booking.bean.BookingDetails;

@Service
public class BookingServiceImpl implements BookingService {

	private List<String> dupBookingList = new ArrayList<String>();

	@Override
	public String scheduleBooking(String bookings) {
		List<String> bookingList = new ArrayList<String>(Arrays.asList(bookings.split("\\r?\\n")));
		List<BookingDetails> room1 = new ArrayList<BookingDetails>();
		List<BookingDetails> room2 = new ArrayList<BookingDetails>();
		Map<String, Boolean> map = new HashMap<String, Boolean>();

		int startHr1 = 9, startHr2 = 9;
		// int endHr1 = 9, endHr2 = 9;
		int startMin1 = 00, startMin2 = 00;
		// int endMin1 = 60, endMin2 = 60;

		String day = "AM";
		String room = "Room 1";
		System.out.println("Room 1");
		for (String s : bookingList) {
			if (!map.containsKey(s)) {
				int checkCount = 0;
				String duration = null;
				BookingDetails book = new BookingDetails();
				Pattern p = Pattern.compile("\\d{1,2}");
				Matcher m = p.matcher(s);
				while (m.find()) {
					duration = m.group();
					checkCount++;
				}
				if (checkCount == 1) {
					if (startHr1 == 12 && startMin1 == 00) {
						startHr1 = 1;
						System.out.println("12:00 PM Lunch");
					}
					if (startHr1 == 5) {
						System.out.println("Room 2");
						startHr1 = 9;
						startMin1 = 00;
					}
					book.setDuration(Integer.parseInt(duration));
					book.setStartTime(startHr1 + ":" + startMin1 + " " + day);
					book.setTitle(s);
					room1.add(book);
					map.put(s, true);
					System.out.println(book.getStartTime() + " " + book.getTitle());
					if (startMin1 + book.getDuration() >= 60) {
						if ((startHr1 + 1 == 12) && (startMin1 + book.getDuration()) % 60 > 0) {
							startHr1 = 1;
							startMin1 = 00;
							continue;
						}
						if ((startHr1 + 1 == 5) && (startMin1 + book.getDuration()) % 60 > 0) {
							System.out.println("Room 2");
							startHr1 = 9;
							startMin1 = 00;
							continue;
						} else {
							startHr1++;
							startMin1 = (startMin1 + book.getDuration()) % 60;
						}

					} else {
						// endHr1 = startHr1;
						// endMin1 = book.getDuration();
						startMin1 = startMin1 + book.getDuration();
					}
					if (startHr1 >= 12) {
						day = "PM";
					}

				}
			}
		}
		System.out.println("room1 ----- " + room1);

		return null;
	}

	@Override
	public LinkedHashMap<List<BookingDetails>, String> scheduleBooking2(String bookings) {
		LinkedHashMap<List<BookingDetails>, String> returnMap = new LinkedHashMap<List<BookingDetails>, String>();
		List<String> bookingList = new ArrayList<String>(Arrays.asList(bookings.split("\\r?\\n")));
		dupBookingList.addAll(bookingList);
		List<BookingDetails> room1Slot1 = new ArrayList<BookingDetails>();
		List<BookingDetails> room1Slot2 = new ArrayList<BookingDetails>();
		List<BookingDetails> room2Slot1 = new ArrayList<BookingDetails>();
		List<BookingDetails> room2Slot2 = new ArrayList<BookingDetails>();
		Map<String, Boolean> map = new HashMap<String, Boolean>();

		// for room 1 slot 1
		room1Slot1 = scheduleMeeting(bookingList, 180);
		System.out.println("room1Slot1 ---- " + room1Slot1);
		room1Slot1 = setStartTimeAndEndTime(room1Slot1, 9, 0, "AM");
		System.out.println("room1Slot1 after ---- " + room1Slot1);
		
		// for room 1 slot 2
		bookingList.clear();
		bookingList.addAll(dupBookingList);
		room1Slot2 = scheduleMeeting(bookingList, 240);
		System.out.println("room1Slot2 ---- " + room1Slot2);
		room1Slot2 = setStartTimeAndEndTime(room1Slot2, 1, 0, "PM");
		System.out.println("room1Slot2 after ---- " + room1Slot2);

		// for room 2 slot 1
		bookingList.clear();
		bookingList.addAll(dupBookingList);
		room2Slot1 = scheduleMeeting(bookingList, 180);
		System.out.println("room2Slot1 ---- " + room2Slot1);
		room2Slot1 = setStartTimeAndEndTime(room2Slot1, 9, 0, "AM");
		System.out.println("room2Slot1 after ---- " + room2Slot1);

		// for room 2 slot 2
		bookingList.clear();
		bookingList.addAll(dupBookingList);
		room2Slot2 = scheduleMeeting(bookingList, 240);
		System.out.println("room2Slot2 ---- " + room2Slot2);
		room2Slot2 = setStartTimeAndEndTime(room2Slot2, 1, 0, "PM");
		System.out.println("room2Slot2 after ---- " + room2Slot2);
		
		returnMap.put(room1Slot1, "Room 1");
		returnMap.put(room1Slot2, "12:00 PM Lunch");
		returnMap.put(room2Slot1, "Room 2");
		returnMap.put(room2Slot2, "12:00 PM  Lunch");

		return returnMap;
	}

	public List<BookingDetails> scheduleMeeting(List<String> bookingList, int totalCount) {
		List<BookingDetails> list = new ArrayList<BookingDetails>();
		int room1Slot1Total = 0;
		for (String s : bookingList) {
			int index = bookingList.indexOf(s);
			String duration = checkCountOfNumbers(s, bookingList, index);
			if (duration != null) {
				if (Integer.parseInt(duration) + room1Slot1Total <= totalCount) {
					room1Slot1Total = room1Slot1Total + Integer.parseInt(duration);
					BookingDetails book = new BookingDetails();
					book.setDuration(Integer.parseInt(duration));
					book.setTitle(s);
					list.add(book);
					dupBookingList.remove(dupBookingList.indexOf(s));
				}

			} else {
				continue;
			}
		}
		return list;
	}

	public List<BookingDetails> setStartTimeAndEndTime(List<BookingDetails> room1, int startHr1, int startMin1,
			String day) {
		for (BookingDetails book : room1) {
			book.setStartHr(startHr1);
			book.setStartMin(startMin1);
			if(startMin1==0) {
				book.setStartTime(startHr1 + ":" + "00" + " " + day);
			}else
			if(startMin1<10) {
				book.setStartTime(startHr1 + ":" + "0"+startMin1 + " " + day);
			}else {
				book.setStartTime(startHr1 + ":" + startMin1 + " " + day);
			}
			
			System.out.println(book.getStartTime() + " " + book.getTitle());
			book.setTalk(book.getStartTime() + " " + book.getTitle());
			if (startMin1 + book.getDuration() >= 60) {
				startHr1++;
				startMin1 = (startMin1 + book.getDuration()) % 60;

			} else {
				startMin1 = startMin1 + book.getDuration();
			}
			if (startHr1 >= 12) {
				day = "PM";
			}

			room1.set(room1.indexOf(book), book);
			//dupBookingList.remove(dupBookingList.indexOf(book));
		}

		return room1;
	}


	public String checkCountOfNumbers(String str, List<String> bookList, int index) {
		String duration = null;
		int checkCount = 0;
		Pattern p = Pattern.compile("\\d{1,2}");
		Matcher m = p.matcher(str);
		while (m.find()) {
			duration = m.group();
			checkCount++;
		}
		if (checkCount == 1) {
			return duration;
		} else {
			dupBookingList.remove(index);
		}
		return null;
	}

}
