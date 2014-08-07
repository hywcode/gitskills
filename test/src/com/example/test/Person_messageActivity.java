package com.example.test;

import com.data.test.data;

import java.io.BufferedReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.DefaultedHttpContext;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Person_messageActivity extends Activity {
   
    private HttpResponse httpResponse=null;
    private HttpEntity httpEntity=null;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_message);
		final Button but7=(Button)findViewById(R.id.bt7);
		final Button but8=(Button)findViewById(R.id.bt8);
		final Button returnBtn=(Button)findViewById(R.id.returnBtn);
		final Intent intent1 = getIntent();
		final String one=intent1.getStringExtra("one");
		final String two=intent1.getStringExtra("two");
		final String pid = intent1.getStringExtra("pid");
		final String crime;
		if(intent1.getStringExtra("crime") != null){
			crime = intent1.getStringExtra("crime");
		}else{
			crime = null;
		}

		
       returnBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent =new Intent();
				intent.putExtra("pid", pid);
				
				if ((crime != null)&&crime.equals("1")){					
					intent.putExtra("crime", "1");
				}else{
					intent.putExtra("crime", "0");
				}
				intent.setClass(Person_messageActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});

       
		but8.setOnClickListener(new OnClickListener() {
			private String baseUrl=data.URLSTR+"CACPro/rest/cac/drivingBasic";
			URL url;
			String result;
			@Override
			public void onClick(View arg0) {
				String content = two;
				
				try {
				    url = new URL(baseUrl);
				    HttpURLConnection conn;
				
					conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5000);
					// 设置允许输出
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					// 设置User-Agent: Fiddler
					conn.setRequestProperty("ser-Agent", "Fiddler");
					// 设置contentType
					conn.setRequestProperty("Content-Type", "text/plain");
					OutputStream os = conn.getOutputStream();
					os.write(content.getBytes());
					os.close();
					//服务器返回的响应码
					int code = conn.getResponseCode();
					InputStream is = conn.getInputStream();
					result= NetUtils.readString(is);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Intent intent=new Intent();
				intent.putExtra("info", result);
				intent.setClass(Person_messageActivity.this, Car_licenseActivity.class);
				startActivity(intent);
			}
		});

			
				
       but7.setOnClickListener(new OnClickListener() {
    	   private String baseUrl=data.URLSTR+"CACPro/rest/cac/personBasic";
    	   URL url;
			String result;
			@Override
			public void onClick(View arg0) {
				String content;
				   if(one==null)
			           content = pid;
				else content =one;
				
				try {
				    url = new URL(baseUrl);
				    HttpURLConnection conn;
				
					conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5000);
					// 设置允许输出
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					// 设置User-Agent: Fiddler
					conn.setRequestProperty("ser-Agent", "Fiddler");
					// 设置contentType
					conn.setRequestProperty("Content-Type", "text/plain");
					OutputStream os = conn.getOutputStream();
					os.write(content.getBytes());
					os.close();
					//服务器返回的响应码
					int code = conn.getResponseCode();
					InputStream is = conn.getInputStream();
					result= NetUtils.readString(is);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Intent intent=new Intent();
				intent.putExtra("jsonInfo", result);
				intent.setClass(Person_messageActivity.this, Person_BirthActivity.class);
				startActivity(intent);
				
			}
		});
	}
	
	

}
