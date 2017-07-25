package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import service.SpiderRunnable;
import service.SpiderTimerTask;

public class Main {
	
	/**
	 * @TODO：启动
	 * @param args
	 * 
	 * 
	 *  在resources文件夹中db.properties 中修改数据库相关参数
	 * 
	 */
	public static void main(String[] args) {
		String path = null;
		String propertiesPath = null;
		try {
			String pathPrefix = Main.class.getProtectionDomain().getCodeSource().getLocation().getFile();
			
			if (pathPrefix.contains("jar")) {
				pathPrefix = pathPrefix.substring(1, pathPrefix.lastIndexOf("/")+1);
				path = java.net.URLDecoder.decode(pathPrefix,"utf-8")+"resources/Spring-context.xml";
				propertiesPath = java.net.URLDecoder.decode(pathPrefix,"utf-8")+"resources/db.properties";
			}
			else {
				path = java.net.URLDecoder.decode(pathPrefix,"utf-8")+"Spring-context.xml";
				propertiesPath = java.net.URLDecoder.decode(pathPrefix,"utf-8")+"db.properties";
				
			}
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(path); 
		Properties properties = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(propertiesPath);
			properties.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int second = Integer.parseInt((String)properties.get("period"));
		ArrayList<String> keywords = new ArrayList<>(Arrays.asList("Cell Phones"));
		for (String string : keywords) {
			//Thread thread = new Thread(new SpiderTimerTask(applicationContext.getBean(SpiderRunnable.class,string,second),second));
			Thread thread = new Thread(applicationContext.getBean(SpiderRunnable.class,string,second));
			
			thread.start();
		}
					
	}
}
