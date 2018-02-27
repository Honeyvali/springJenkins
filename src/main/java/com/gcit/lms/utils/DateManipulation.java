package com.gcit.lms.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateManipulation {
	public String getCurrentDateString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		String dateString = df.format(calobj.getTime());
		return dateString;
	}

	public Timestamp getCurrentDateSQL() throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		String dateString = df.format(calobj.getTime());
		java.util.Date dateJ = df.parse(dateString);
		Timestamp date = new Timestamp(dateJ.getTime());
		return date;
	}

	public Timestamp getDueDateSQL() throws ParseException {
		int noOfDays = 7; // i.e one week
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		String dateString = df.format(calobj.getTime());
		java.util.Date dateJ = df.parse(dateString);

		calobj.setTime(dateJ);
		calobj.add(Calendar.DAY_OF_YEAR, noOfDays);
		dateJ = calobj.getTime();
		Timestamp date = new Timestamp(dateJ.getTime());
		return date;
	}

	public Timestamp getDueDateSQL(int due) throws ParseException {
		int noOfDays = due; // i.e one week
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		String dateString = df.format(calobj.getTime());
		java.util.Date dateJ = df.parse(dateString);

		calobj.setTime(dateJ);
		calobj.add(Calendar.DAY_OF_YEAR, noOfDays);
		dateJ = calobj.getTime();
		Timestamp date = new Timestamp(dateJ.getTime());
		return date;
	}

	public java.util.Date getJavaDate(String str) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = df.parse(str);
		return date;
	}

	public Timestamp getSQLDate(String str) throws ParseException {

		java.util.Date datej = getJavaDate(str);
		Timestamp date = new Timestamp(datej.getTime());
		return date;
	}

	public String getSQLDate1(Timestamp str) throws ParseException {

		Timestamp date = str;
		return date.toString();
	}

	public boolean hasDateIn(String str) throws ParseException {
		Timestamp dateBase = getSQLDate("1900-01-01 00:00:00");
		return getSQLDate(str).after(dateBase);
	}

	public boolean sameDateCheck(String str, String str1) throws ParseException {
		java.util.Date date1 = getJavaDate(str);
		java.util.Date date2 = getJavaDate(str1);
		return date1.equals(date2);
	}

}


