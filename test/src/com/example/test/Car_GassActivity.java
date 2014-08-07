package com.example.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.data.test.data;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.StaticLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Car_GassActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_gas);
		final Button but1=(Button)findViewById(R.id.bt9);
		
		final TextView tv1=(TextView)findViewById(R.id.carband);
		final TextView tv2=(TextView)findViewById(R.id.carid);
		Intent intent = getIntent();
		final String pId2=intent.getStringExtra("pId1");
		System.out.println(pId2);
		final String[] plateNum = new String[100];
		if(pId2!=null){
			try
			{
				JSONObject json = new JSONObject(pId2); 
	            String test=json.get("test").toString();
	            System.out.println(test);
	            JSONArray jsonArray = json.getJSONArray("carBasic");  
	            int iSize = jsonArray.length();
	            String[] pId = new String[iSize];
	            String[] regTime = new String[iSize];
	            String[] carBrand = new String[iSize];
	              
	            for (int i = 0; i < iSize; i++) {  
	                JSONObject jsonObj = jsonArray.getJSONObject(i);  
	                pId[i]=jsonObj.get("pId").toString();
	                regTime[i]=jsonObj.get("regTime").toString();
	                carBrand[i]=jsonObj.get("carBrand").toString();
	                plateNum[i]=jsonObj.get("plateNum").toString();
	            }  
	            tv1.setText(carBrand[0]);
	            tv2.setText(plateNum[0]);
	           
	           
	        } catch (JSONException e) {  
	            e.printStackTrace();  
	        }  
		}
        
	  but1.setOnClickListener(new OnClickListener() {
		  private String baseUrl=data.URLSTR+"CACPro/rest/cac/carReal";
   	     URL url;
	     String result;
		public void onClick(View v) {
       String content = plateNum[0];                                                                    
			
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
			intent.setClass(Car_GassActivity.this, Car_messageActivity.class);
			startActivity(intent);
			
		}
		});
	   
	    
	}
	private static ArrayList<HashMap<String, Object>>  Analysis (String jsonStr) {
		 ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		 try{
			 JSONObject jsonObj = new JSONObject(jsonStr);
			 String test = jsonObj.getString("test");
			 HashMap<String, Object> testMap = new HashMap<String, Object>();
			 testMap.put("test", test);
			 list.add(testMap);
			 JSONArray jsonArray = jsonObj.getJSONArray("carBasic");
			 for (int i = 0; i < jsonArray.length(); i++){
				HashMap<String, Object> map = new HashMap<String, Object>();
				JSONObject jsonObj2 = jsonArray.getJSONObject(i);
				String pId = jsonObj2.getString("pId");
		   		map.put("pId", pId);
		   		String regTime = jsonObj2.getString("regTime");
		   		map.put("regTime", regTime);
		   		String carBand = jsonObj2.getString("carBand");
		   		map.put("carBand", carBand);
		   		String  plateName = jsonObj2.getString("plateName");
		   		map.put("plateName", plateName);
		   		
		   		list.add(map);
			 }
			 
			}catch(Exception e){
				e.printStackTrace();
			}
	       return list;
	    }

}
