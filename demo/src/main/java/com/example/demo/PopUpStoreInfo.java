package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;


// model에 해당하는 부분, DB에 적재할 데이터
public class PopUpStoreInfo {

	private Long id;
	private String name;
	private String region;
	private String address;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private int ticketPrice;
	private String RefundPolicy;

	public PopUpStoreInfo(Long id, String name, String region, String address, LocalDate startDate,
		LocalDate endDate, LocalTime startTime, LocalTime endTime, int ticketPrice,
		String refundPolicy) {
		this.id = id;
		this.name = name;
		this.region = region;
		this.address = address;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.ticketPrice = ticketPrice;
		RefundPolicy = refundPolicy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getRefundPolicy() {
		return RefundPolicy;
	}

	public void setRefundPolicy(String refundPolicy) {
		RefundPolicy = refundPolicy;
	}
}
