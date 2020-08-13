package com.asm.booking.bean;

public class BookingDetails {
	
	private String title;
	private int duration;
	private String talk;
	private String roomNo;
	private String startTime;
	private String endTime;
	private int startHr;
	private int startMin;
	private int endHr;
	private int endMin;
	
	
	
	public int getStartHr() {
		return startHr;
	}
	public void setStartHr(int startHr) {
		this.startHr = startHr;
	}
	public int getStartMin() {
		return startMin;
	}
	public void setStartMin(int startMin) {
		this.startMin = startMin;
	}
	public int getEndHr() {
		return endHr;
	}
	public void setEndHr(int endHr) {
		this.endHr = endHr;
	}
	public int getEndMin() {
		return endMin;
	}
	public void setEndMin(int endMin) {
		this.endMin = endMin;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int time) {
		this.duration = time;
	}
	public String getTalk() {
		return talk;
	}
	public void setTalk(String talk) {
		this.talk = talk;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	@Override
	public String toString() {
		return "BookingDetails [title=" + title + ", duration=" + duration + ", talk=" + talk + ", roomNo=" + roomNo
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", startHr=" + startHr + ", startMin="
				+ startMin + ", endHr=" + endHr + ", endMin=" + endMin + "]";
	}
	
	
	
}
