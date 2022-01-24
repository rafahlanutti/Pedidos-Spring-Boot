package com.rafael.estudos.springboot.utils;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class URL {

	public static List<Integer> decoreIntList(String ids) {
		return Arrays.asList(ids.split(",")).stream().map(c -> Integer.parseInt(c)).collect(Collectors.toList());
	}

	public static String decoreParam(String param) {

		try {
			return URLDecoder.decode(param, "UTF-8");

		} catch (Exception e) {

			return "";
		}

	}

	public static Date convertDate(String text, Date defaultDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(text);
		} catch (Exception e) {

			return defaultDate;
		}
	}

}
