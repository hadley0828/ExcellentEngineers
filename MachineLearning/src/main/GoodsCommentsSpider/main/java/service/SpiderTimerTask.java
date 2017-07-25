package service;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.context.ApplicationContext;

/**
 * @author 凡
 * 定时任务
 */
public class SpiderTimerTask implements Runnable{

	
	private SpiderRunnable spiderRunnable;
	private int second;
	
	public SpiderTimerTask(SpiderRunnable bean,int second) {
		spiderRunnable = bean;
		this.second = second;
	}

	@Override
	public void run() {
		Timer timer = new Timer();
		
		//每隔second更新一次
		timer.scheduleAtFixedRate(spiderRunnable, 0, second*1000); 
	}
}
