package com.spis.rett;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class ImageDownloadAsyncTask extends AsyncTask<String, Void, Bitmap>
{
	ImageView bmImage;
//}
//
//
//new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
//.execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");
//}
//



	
	
	public ImageDownloadAsyncTask(ImageView bmImage) {
	this.bmImage = bmImage;
	}
	@Override
	protected void onPreExecute() {
		bmImage.setImageBitmap(null);
		super.onPreExecute();
	}
	
	protected Bitmap doInBackground(String... urls) {
	String urldisplay = urls[0];
	Bitmap mIcon11 = null;
	try {
	InputStream in = new java.net.URL(urldisplay).openStream();
	mIcon11 = BitmapFactory.decodeStream(in);
	} catch (Exception e) {
	Log.i("Error", e.getMessage());
	e.printStackTrace();
	}
	return mIcon11;
	}

	protected void onPostExecute(Bitmap result) {
	bmImage.setImageBitmap(result);
	}
}