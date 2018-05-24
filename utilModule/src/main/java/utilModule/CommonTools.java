package utilModule;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonTools {
	
	/**
	 * 读取properties文件中的数据,返回结果集map
	 * @param filePath 将要读取的properties文件位置
	 * @return 结果集map
	 */
	public static Map<String, Object> readProperties(String filePath) {
		Properties prop = new Properties();
		try {
			prop.load(new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for(Object key : prop.keySet()) map.put((String)key, prop.get(key));
		return map;
	}
	
	/**
	 *写入数据到指定的properties文件
	 * @param filePath properties文件位置
	 * @param map 写入的数据集
	 */
	public static void writeProperties(String filePath,Map<String, Object> map) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath,true), "utf-8"));
			Properties prop = new Properties();
			for(String key : map.keySet()) prop.setProperty(key, (String)map.get(key));
			prop.store(pw,"");
			pw.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// json专区  使用的Jackson
	
	public static Class jsonStringToBean(String jsonString,Class class1) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		class1 = mapper.readValue(jsonString, Class.class);
		return class1;
	}
	
	@SuppressWarnings("unchecked")
	public static void readJsonFile(String filePath) {
		long l = System.currentTimeMillis();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
			try {
				map = mapper.readValue(new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8")),Map.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		long l2 = System.currentTimeMillis();
		System.out.println(map);
		System.out.println("总共用时:" + (l2 - l) + "毫秒!");
	}
	
	public static void main(String[] args) {
//		String filePath = "demo.properties";
//		Map<String, Object> map = CommonTools.readProperties(filePath);
//		System.out.println(map);
//		CommonTools.writeProperties(filePath, map);
		readJsonFile("demo.json");
	}

}
