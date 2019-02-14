package com.yqx.jurisdiction.util;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultToJackson {
	private ObjectMapper objectMapper;
	Map<String, Object> resultmap = new HashMap<String, Object>();

	public ResultToJackson() {
		objectMapper = new ObjectMapper();
	}
	
	public String getOk() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "true");
		resultmap.put("result", map);
		try {
			return objectMapper.writeValueAsString(resultmap);
		} catch (Exception e) {
			return null;
		}
	}

	public String getFalse(int code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "false");
		map.put("code", String.valueOf(code));
		map.put("message", "null");
		resultmap.put("result", map);
		try {
			return objectMapper.writeValueAsString(resultmap);
		} catch (Exception e) {
			return null;
		}
	}

	public String getFalse(String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "false");
		map.put("code", code);
		map.put("message", "null");
		resultmap.put("result", map);
		try {
			return objectMapper.writeValueAsString(resultmap);
		} catch (Exception e) {
			return null;
		}
	}


	public String getFalse(String code, String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "false");
		map.put("code", code);
		map.put("message", message);
		resultmap.put("result", map);
		try {
			return objectMapper.writeValueAsString(resultmap);
		} catch (Exception e) {
			return null;
		}
	}

	public String getFalseForWeb(String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "false");
		map.put("code", code);
		map.put("message", "null");
		// resultmap.put("result", map);
		try {
			return objectMapper.writeValueAsString(map);
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getFalseForWeb(String code,String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "false");
		map.put("code", code);
		map.put("message", message);
		// resultmap.put("result", map);
		try {
			return objectMapper.writeValueAsString(map);
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getTrueForWeb() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "true");
		try {
			return objectMapper.writeValueAsString(map);
		} catch (Exception e) {
			return null;
		}
	}

	public String getTrueForWeb(int i) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "true" + i);
		try {
			return objectMapper.writeValueAsString(map);
		} catch (Exception e) {
			return null;
		}
	}

	public String getResultFromMap(Map<String, Object> result) {
		try {
			JSONObject json = JSONObject.fromObject(result);
			return json.toString().replaceAll(":null", ":\"\"");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getAppResultFalseCodeMap(String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("status", "false");
		m.put("code", code);
		m.put("message", "null");
		map.put("result", m);
		try {
			return objectMapper.writeValueAsString(map);
		} catch (Exception e) {
			return null;
		}
	}

}
