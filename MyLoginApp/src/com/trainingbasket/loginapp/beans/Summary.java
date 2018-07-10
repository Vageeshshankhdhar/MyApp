package com.trainingbasket.loginapp.beans;

public class Summary {

	private String m_extension;
	private String m_date;
	private String m_totalCallTime;
	private String m_totalCallNumber;
	private String m_averageCall;
	private String m_firstCall;
	private String m_lastCall;

	public String getAverageCall() {
		return m_averageCall;
	}
	public void setAverageCall(String averageCall) {
		m_averageCall = averageCall;
	}
	public String getDate() {
		return m_date;
	}
	public void setDate(String date) {
		m_date = date;
	}
	public String getExtension() {
		return m_extension;
	}
	public void setExtension(String extension) {
		m_extension = extension;
	}
	public String getFirstCall() {
		return m_firstCall;
	}
	public void setFirstCall(String firstCall) {
		m_firstCall = firstCall;
	}
	public String getLastCall() {
		return m_lastCall;
	}
	public void setLastCall(String lastCall) {
		m_lastCall = lastCall;
	}
	public String getTotalCallNumber() {
		return m_totalCallNumber;
	}
	public void setTotalCallNumber(String totalCallNumber) {
		m_totalCallNumber = totalCallNumber;
	}
	public String getTotalCallTime() {
		return m_totalCallTime;
	}
	public void setTotalCallTime(String totalCallTime) {
		m_totalCallTime = totalCallTime;
	}


}
