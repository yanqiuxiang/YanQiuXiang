package com.yqx.jurisdiction.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;


public class Util {
	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = md5Bytes[i] & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */ 
	public static String convertMD5(String inStr){

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}
	
	/**
	 * 生成随机密码
	 * @param length
	 * @return
	*/
	 public static String getRandomString(int length) { 
	 StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz"); 
		 StringBuffer sb = new StringBuffer(); 
		 Random r = new Random(); 
		 int range = buffer.length(); 
		 for (int i = 0; i < length; i ++) { 
			 sb.append(buffer.charAt(r.nextInt(range)));
		 }
		 	return sb.toString(); 
		}
	 
	//MD5 值计算
	public final  static String MD5(String s) {
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

	public final static String MD5Code(String s)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		try
		{
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static short makeShortFromByte(byte a, byte b)
	{
		short sa = a;
		short sb = b;
		if (sa < 0) sa += 256;
		if (sb < 0) sb += 256;
		return (short) (sa + (sb << 8));
	}


	public static String getSafeVerifyCode(String strValue)
	{
		byte[] buf = null;
		try
		{
			buf = strValue.getBytes("UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		if (buf == null) return strValue;
		StringBuilder sb = new StringBuilder();
		long n=0;
		for (int i = 0; i < buf.length; i++)
		{
			short v = buf[i];
			if (v < 0) v += 256;// 转化成无符号
			v ^= buf.length % 256;
			v += i * 2;
			v = (short) (v % 256);
			sb.append(String.format("%02X", v));
			n+=v;
		}

		String strResult=MD5Code(sb.toString());
		
		if(n<=0) n=1;
		for(int i=0;i<n%3;i++)
			strResult=MD5Code(strResult);
		return strResult;
	}
	
	
	public static String makeDeviceCode()
	{
			long now = System.currentTimeMillis();
			BigInteger value = new BigInteger("1529000000000");
			now -= value.longValue();
			
			StringBuilder sb=new StringBuilder();
			sb.append(String.format("%d", now));
			
			while(sb.length()<15)
			{
					byte ch =(byte) ( (now+(int)(Math.random()*100))%10);
					sb.insert(0, (char)(ch+'0'));
			}
			
			
			return sb.toString();
	}
	
	
	public static String encoderAuthString(String password)
	{
		String str = String.format("%s,%d,%d", password, System.currentTimeMillis() / 1000, (new Date()).getTime() % 10000);

		byte[] buf = str.getBytes();
		byte ch1 = buf[buf.length - 1];
		byte ch2 = buf[buf.length - 2];

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buf.length - 2; i++)
		{
			buf[i] ^= ch1;
			buf[i] += ch2;
			sb.append(String.format("%02x", buf[i]));
		}
		sb.append(String.format("%02x", ch2));
		sb.append(String.format("%02x", ch1));
		return (sb.toString());
	}
	
	
	/* 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 * 发送请求的 URL
	 * @param param
	 * 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param)
	{
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try
		{
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(10 * 1000);// 设置超时

			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			// 获取URLConnection对象对应的输出流，设置utf-8编码
	        out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
			
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null)
			{
				result += line;
			}
		}
		catch (Exception e)
		{
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
			return "";
		}
		// 使用finally块来关闭输出流、输入流
		finally
		{
			try
			{
				if (out != null)
				{
					out.close();
				}
				if (in != null)
				{
					in.close();
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
				return "";
			}
		}
		
		return result;
	}
	
	//查询图片本地路径
	public static String toLocalImageFile(String localImagePath, String imageUrl)
	{
		String[] list = imageUrl.split(",");
		
		StringBuilder sb =new StringBuilder();
		
		for(int i=0;i<list.length;i++)
		{
			String image = list[i];
			int m = image.lastIndexOf('/');
			if (m < 0) return "";
			int n = image.lastIndexOf('/', m - 1);
			if (n < 0) return "";
			image = image.substring(n+1, image.length());
			String localImage = localImagePath + System.getProperty("file.separator") + image;
			if(i!=0) sb.append(",");
			sb.append(localImage);
		}
		return sb.toString();
	}
}
