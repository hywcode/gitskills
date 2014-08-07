package com.example.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.data.test.data;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Button but1=null;
	private String crime = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()       
        .detectDiskReads()       
        .detectDiskWrites()       
        .detectNetwork()         
        .penaltyLog()       
        .build());       
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()       
        .detectLeakedSqlLiteObjects()    
        .penaltyLog()       
        .penaltyDeath()       
        .build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText edt1=(EditText)findViewById(R.id.ed1);
        final EditText edt2=(EditText)findViewById(R.id.ed2);
        final EditText edt4=(EditText)findViewById(R.id.ed4);
        final Button but2=(Button)findViewById(R.id.bt2);
        final Button but3=(Button)findViewById(R.id.bt3);
        final Button but4=(Button)findViewById(R.id.bt4);
        final Button but7=(Button)findViewById(R.id.other);
        final Button but5=(Button)findViewById(R.id.bt5);
        Intent intent = getIntent();
        String crime = intent.getStringExtra("crime");
        String pid = intent.getStringExtra("pid");
       
        if(pid != null){
        	edt1.setText(pid);
        }
        if((crime != null)&&crime.equals("1")){
        	System.out.println("执行了setsetVisibility方法");
        	but7.setVisibility(0);
        	but7.setTextColor(Color.RED);
        }

        
        but3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
					Intent intent =new Intent();
					String baseUrl = data.URLSTR+"CACPro/rest/cac/personFuel";
					try {
						URL url = new URL(baseUrl);
						String content = edt1.getText().toString().trim();
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setConnectTimeout(5000);
						conn.setDoOutput(true);//设置允许输出
						conn.setRequestMethod("POST");
						conn.setRequestProperty("Content-Type", "text/plain");
						OutputStream os = conn.getOutputStream();
						os.write(content.getBytes());
						os.close();
						int code = conn.getResponseCode();
						if(code == 200){
							InputStream is = conn.getInputStream();
							String json = NetUtils.readString(is);
							System.out.println(json);
							intent.putExtra("jsonInfo", json);
						}
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					intent.setClass(MainActivity.this, Person_GassActivity.class);
					startActivity(intent);
				}

			
		});
        
        but4.setOnClickListener(new OnClickListener() {
        	private String baseUrl=data.URLSTR+"CACPro/rest/cac/carBasic";
    	    URL url;
    		String result;
			public void onClick(View v) {
				String content = edt1.getText().toString().trim();
				String carBand1=null;
		        String plateName1=null;
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
				Intent intent =new Intent();
				intent.putExtra("pId1", result);
				intent.setClass(MainActivity.this, Car_GassActivity.class);
				startActivity(intent);
				
			}
		});
        but5.setOnClickListener(new OnClickListener() {
        	 private String baseUrl=data.URLSTR+"CACPro/rest/cac/carReal";
	    	 URL url;
		     String result;
			public void onClick(View v) {
            String content = "AL1234";
				
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
					System.out.println(result);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent =new Intent();
				intent.putExtra("info", result);
				intent.setClass(MainActivity.this, Car_messageActivity.class);
				startActivity(intent);
				
			}
		});

        but7.setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
				Intent intent =new Intent();
				String baseUrl = data.URLSTR+"CACPro/rest/cac/crimeBasic";
				try {
					URL url = new URL(baseUrl);
					String content = edt1.getText().toString().trim();
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5000);
					conn.setDoOutput(true);//设置允许输出
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "text/plain");
					OutputStream os = conn.getOutputStream();
					os.write(content.getBytes());
					os.close();
					int code = conn.getResponseCode();
					if(code == 200){
						InputStream is = conn.getInputStream();
						String json = NetUtils.readString(is);
						System.out.println(json+"==========");
						intent.putExtra("jsonInfo", json);
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				intent.setClass(MainActivity.this, Other_messageActivity.class);
				startActivity(intent);
			}

				
			
		});
        but2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.putExtra("one", edt1.getText().toString());
			    intent.putExtra("two", edt2.getText().toString());
				intent.setClass(MainActivity.this, Person_messageActivity.class);
				startActivity(intent);
				
			}
		});
    }



   
    
}
