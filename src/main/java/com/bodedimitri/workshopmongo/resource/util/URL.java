package com.bodedimitri.workshopmongo.resource.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8"); //Metodo usado para codificar a nossa pesquisa
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
