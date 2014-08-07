package com.example.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Type;
import android.util.JsonReader;
import android.util.Log;
import android.widget.TextView;

public class Car_licenseActivity extends Activity {

	private Object list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_license);
		final TextView tv1=(TextView)findViewById(R.id.tvw1);
		final TextView tv2=(TextView)findViewById(R.id.tvw2);
		final TextView tv3=(TextView)findViewById(R.id.tvw3);
		final TextView tv4=(TextView)findViewById(R.id.tvw4);
		String regTime = null;
		String birthday=null;
		
        int state = 0;
        int perType=0;
        Intent intent = getIntent();
		String info = intent.getStringExtra("info"); 
	   
		if(info!=null){
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(info);
			birthday=jsonObject.getString("birthday");
			String test=jsonObject.getString("test");
			JSONObject jsonObject1 = jsonObject.getJSONObject("drivingBasic");
			String pId = jsonObject1.getString("pId");
			System.out.println(pId);
			regTime =jsonObject1.getString("regTime");
			state = jsonObject1.getInt("state");
			perType = jsonObject1.getInt("perType");
			String dId = jsonObject1.getString("dId");
			
			
			
			
			tv3.setText(regTime);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//下面的Person是一个bean,里面有username,和 age
		
		tv1.setText(birthday);
		tv2.setText(regTime);
		if(state==1) tv3.setText("A");
		else if(state==2) tv3.setText("B");	
		else if(state==3) tv3.setText("C");
		if(perType==1) tv4.setText("A");
		else if(perType==2) tv4.setText("B");	
		else if(perType==3) tv4.setText("C");
		
	}	
	}

}

/**
      * 访问数据库并返回JSON数据字符串
      * 
      * @param params 向服务器端传的参数
      * @param url
      * @return
      * @throws Exception
      *//*
     public static String doPost(List<NameValuePair> params, String url)
             throws Exception {
         String result = null;
         // 获取HttpClient对象
         HttpClient httpClient = new DefaultHttpClient();
         // 新建HttpPost对象
         HttpPost httpPost = new HttpPost(url);
         if (params != null) {
             // 设置字符集
             HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
             // 设置参数实体
             httpPost.setEntity(entity);
         }

         // 连接超时
         httpClient.getParams().setParameter(
                 CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
         // 请求超时
         httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                 3000);
         // 获取HttpResponse实例
         HttpResponse httpResp = httpClient.execute(httpPost);
         // 判断是够请求成功
         if (httpResp.getStatusLine().getStatusCode() == 200) {
             // 获取返回的数据
             result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
         } else {
             Log.i("HttpPost", "HttpPost方式请求失败");
         }

         return result;
     }*/
     
	/*public String getJson(){
		
		String urlStr = "http://172.16.8.36:8080/a/servlet/DetailServlet";
		HttpPost request = new HttpPost(urlStr);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "getJson"));
		
		String result=null;
		try {
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = new DefaultHttpClient().execute(request);
			result = EntityUtils.toString(response.getEntity());
			
		} catch (Exception ex) {
		
		}
		return result;
		
	}
     
     private void resultJson() {
         try {
             allData = Analysis(readParse(url));
             Iterator<HashMap<String, Object>> it = allData.iterator();
             while (it.hasNext()) {
                 Map<String, Object> ma = it.next();
                 if ((Integer) ma.get("id") == id) {
                     tv1.setText((String) ma.get("biaoTi"));
                     yuanJia.setText((String) ma.get("yuanJia"));
                     xianJia.setText((String) ma.get("xianJia"));
                 }
             }
         } catch (JSONException e) {
             e.printStackTrace();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }*/


