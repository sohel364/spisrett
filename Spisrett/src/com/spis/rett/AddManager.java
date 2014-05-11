package com.spis.rett;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.widget.ImageView;

public class AddManager {

	ImageView imageViewAd;
	Handler uiHandler;
	Timer timer=null;
	private final long delay= 10*1000;
	public final int totalImage=2;
	private String[] imgLink=new String[totalImage];
	int currentImage;
	
	public AddManager(ImageView imageViewAd,Handler mainHandler)
	{
		this.uiHandler=mainHandler;
		this.imageViewAd=imageViewAd;
	}
	public void showRandomAd()
	{
		currentImage=0;
		imgLink[0]="http://teknofolk.com/spisrett_admin/slave/banner/banner.png";
		imgLink[1]="http://teknofolk.com/spisrett_admin/slave/banner/banner2.png";
		stopShowingAd();
		timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if (currentImage==totalImage) {
					currentImage=0;
				}
				uiHandler.post(new Runnable() {
					
					@Override
					public void run() {
						new ImageDownloadAsyncTask(imageViewAd).execute(imgLink[currentImage]);
					}
				});
				
			}
		},0 , delay);
	}
	public void stopShowingAd()
	{
		if(timer!=null)
		{
			timer.cancel();
			timer.purge();
		}
	}
}
