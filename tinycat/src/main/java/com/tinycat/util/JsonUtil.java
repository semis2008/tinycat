package com.tinycat.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * 
 * json处理工具类
 * 
 * @author wn
 * @version 1.0.0 JsonUtil.java 2014-5-28 下午3:23:11
 */
public class JsonUtil {
	private static Gson gson = new Gson();
	
	public static void outputDTOToJSON(Object dto, HttpServletResponse response) {
		outputDTOToJSON(dto, true, response);
	}

	public static void outputDTOToJSON(Object dto, Boolean success, HttpServletResponse response) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("success", success);
		outputDTOToJSON(dto, param, response);
	}	
	public static void outputDTOToJSON(Object dto, Map param, HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");  
		response.setHeader("Cache-Control", "no-cache");  
		PrintWriter out;
		try {
			out = response.getWriter();
			Map<String,Object> resultMap = new HashMap<String, Object>();
			resultMap.putAll(param);
			resultMap.put("list", dto);
			out.print(gson.toJson(resultMap));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
