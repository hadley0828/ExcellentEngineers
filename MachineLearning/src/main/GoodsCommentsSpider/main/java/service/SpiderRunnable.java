package service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
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
		Elements goodlist = document.select("a[class=a-link-normal s-access-detail-page  s-color-twister-title-link a-text-normal]");
		//LinkedList<GoodComment> list = new LinkedList<>();
		List<String> unique = new ArrayList<>();
		for (Element element : goodlist) {
			String name = element.attr("title");
			String goodUrl = element.attr("href");
			if (unique.contains(name)) {
				continue;
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
				errorLog(keyword+" : "+httpurl);
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
				errorLog(keyword+" : "+httpurl);
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
		Elements comments = document.select("span[data-hook=review-body]");
		for (Element comment : comments) {
			GoodComment goodComment = new GoodComment(goodName,0,null,comment.text(),star+1);
			result.add(goodComment);
		}
		
		return result;
		
	}
	void spideToFile() {
		String url ="https://www.amazon.com/BLU-R1-HD-Exclusive-Lockscreen/product-reviews/B01H2E0J5M/ref=cm_cr_dp_d_show_all_btm?ie=UTF8&reviewerType=avp_only_reviews&sortBy=helpful&filterByStar=one_star"; 
		Document document = spide(url);
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
		new SpiderRunnable("Cell Phones", 30).spideList();
//		new SpiderRunnable("Cell Phones", 30).spideToFile();
	}
}
