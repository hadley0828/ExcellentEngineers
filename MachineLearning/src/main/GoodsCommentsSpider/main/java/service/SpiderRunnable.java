package service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import PO.GoodComment;
import dao.DaoImpl;
/**
 * @author 凡
 * 解析界面
 */
@Service
@Scope("prototype")
public class SpiderRunnable extends TimerTask{

	@Autowired
	private DaoImpl daoImpl;
	private String url ;
	private String userAgent ;
	private int reconnectTime;
	private String keyword;
	private int failedTime;
	public SpiderRunnable(String keyword,int second) {
		this.keyword = keyword.trim();
		url = "https://www.amazon.com/s/ref=sr_pg_1?page=1&keywords="+this.keyword.replaceAll(" ", "+");
		userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36 OPR/46.0.2597.32";
		reconnectTime = second/6;	
		failedTime = 0;
	}
	@Override
	public void run() {
		failedTime = 0;
		Calendar calendar = Calendar.getInstance();
		Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR_OF_DAY, 9);
		start.set(Calendar.MINUTE,15);
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 15);
		end.set(Calendar.MINUTE,0);
		
		if (calendar.before(start)||calendar.after(end)) {
			//return;
		}
		spideList();
		
	}

	public void spideList() {
		Document document = spide(url);
		if (document==null) {
			return;
		}
		Elements goodlist = document.select("a[class=a-link-normal s-access-detail-page  s-color-twister-title-link a-text-normal]");
		//LinkedList<GoodComment> list = new LinkedList<>();
		List<String> unique = new ArrayList<>();
		for (Element element : goodlist) {
			String name = element.attr("title");
			String goodUrl = element.attr("href");
			if (unique.contains(name)) {
				continue;
			}
			if (!goodUrl.startsWith("https://www.amazon.com")) {
				try {
					goodUrl = URLDecoder.decode(goodUrl.substring(goodUrl.indexOf("url=")+4), "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Running : " + name);
			daoImpl.insertByJDBC(spideComment(goodUrl, name), keyword);
			unique.add(name);
		}
	}
	private Document spide(String httpurl){
		Document document = null;
		
		try {
			document = Jsoup.connect(httpurl).header("User-Agent",userAgent).timeout(5000).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("连接超时");
			failedTime++;
			if (failedTime<reconnectTime) {
				System.out.println("第"+failedTime+"次重连");
				
				return spide(httpurl);
			}else{
				System.out.println("放弃连接");
				errorLog(new Date()+keyword+" : "+httpurl);
				return null;
			}
		}

		if (document==null||document.body()==null) {
			System.out.println("连接超时");
			failedTime++;
			if (failedTime<reconnectTime) {
				System.out.println("第"+failedTime+"次重连");
				
				return spide(httpurl);
			}else{
				System.out.println("放弃连接");
				errorLog(new Date()+keyword+" : "+httpurl);
				return null;
			}
			
		}
		return document;
	/*	File file = new File("amazon.html");
		
		try {
			file.createNewFile();
			OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file));
			BufferedWriter bufferedWriter = new BufferedWriter(outputStream);
			bufferedWriter.write(document.toString());
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	
		
	
		//daoImpl.updateByJDBC((List<GoodComment>)temp);
	
	}
	
	private List<GoodComment> spideComment(String url, String goodName){
		List<GoodComment> result = new LinkedList<GoodComment>();
		Document document =  spide(url);
		if (document==null) {
			return result;
		}
		String commentUrl = "https://www.amazon.com"+document.select("a[data-hook=see-all-reviews-link-foot]").first().attr("href").replaceAll("recent", "helpful")+"&filterByStar=";
		for (int i = 0;i<5;i++) {
			result.addAll(spideCommentByStars(i, commentUrl,goodName));
		}

		return result;
	}
	
	private List<GoodComment> spideCommentByStars(int star,String commentUrl, String goodName) {
		String stars[] = {"one_star","two_star","three_star","four_star","five_star"};
		List<GoodComment> result = new LinkedList<GoodComment>();
		String starUrl = commentUrl+=stars[star];
		Document document = spide(starUrl);
		if (document==null) {
			return result;
		}
		Elements comments = document.select("span[data-hook=review-body]");
		for (Element comment : comments) {
			GoodComment goodComment = new GoodComment(goodName,0,null,comment.text(),star+1);
			result.add(goodComment);
		}
		
		return result;
		
	}
	private void spideToFile() {
		String url ="/gp/slredirect/picassoRedirect.html/ref=pa_sp_btf_aps_sr_pg1_1?ie=UTF8&adId=A00142563I00DJ5IXP7S0&url=https://www.amazon.com/Portable-Bluetooth-Wireless-Charging-Compatible/dp/B01D1X1DIA/ref%3Dsr_1_26/136-8046842-5395102%3Fie%3DUTF8%26qid%3D1501000484%26sr%3D8-26-spons%26keywords%3DCell%2BPhones%26psc%3D1&qualifier=1501000483&id=7897138827262364&widgetName=sp_btf";
		url = url.substring(url.indexOf("url=")+4);
		System.out.println(url);
		Document document = spide(url);
		if (document==null) {
			return;
		}
		File file = new File("amazon.html");
		
		try {
			file.createNewFile();
			OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file));
			BufferedWriter bufferedWriter = new BufferedWriter(outputStream);
			bufferedWriter.write(document.toString());
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void errorLog(String string) {
		File file = new File("errors.txt");
		try {
			OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file,true));
			BufferedWriter bufferedWriter = new BufferedWriter(outputStream);
			bufferedWriter.write(string);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
	//	new SpiderRunnable("Cell Phones", 30).spideList();
		new SpiderRunnable("Cell Phones", 30).spideToFile();
	}
}
