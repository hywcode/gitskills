package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Car_messageActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
        super.onCreate(savedInstanceState);
		
		setContentView(R.layout.car_message);
		final TextView tv1=(TextView)findViewById(R.id.name);
		final TextView tv2=(TextView)findViewById(R.id.carnum);
		final TextView tv3=(TextView)findViewById(R.id.pId);
		final TextView tv4=(TextView)findViewById(R.id.time);
		final TextView tv5=(TextView)findViewById(R.id.gasnum);
		
 		Intent intent = getIntent();
 		String info = intent.getStringExtra("info");
		System.out.println(info);
		if(info!=null){
			try
			{
				JSONObject json = new JSONObject(info); 
	           
	            JSONArray jsonArray = json.getJSONArray("carReal");  
	            int iSize = jsonArray.length();
	            System.out.println(iSize);
	            String[] pId = new String[iSize];
	            int[] refQuantityint = new int[iSize];
	            String[] regTime = new String[iSize];
	            String[] fuelTime = new String[iSize];
	            String[] refName = new String[iSize]; 
	            String [] regGun = new String[iSize];
	            String[] plateNum = new String[iSize];
	            int[] angle = new int[iSize];
	            String[] photopath = new String[iSize];
	            for (int i = 0; i < iSize; i++) {  
	                JSONObject jsonObj = jsonArray.getJSONObject(i);  
	                pId[i]=jsonObj.get("pId").toString();
	                System.out.println(pId[0]);
	                refQuantityint[i]=jsonObj.getInt("refQuantityint");
	                System.out.println(refQuantityint[0]);
	                fuelTime[i]=jsonObj.get("fuelTime").toString();
	                System.out.println(fuelTime[0]);
	                refName[i]=jsonObj.get("refName").toString();
	                System.out.println(refName[0]);
//	                regGun[i]=jsonObj.get("regGun").toString();
//	                System.out.println(regGun[i]);
	                plateNum[i]=jsonObj.get("plateNum").toString();
	                System.out.println(plateNum[0]);
	                angle[i]=jsonObj.getInt("angle");
	                System.out.println(angle[0]);
	                photopath[i]=jsonObj.get("photoPath").toString();
	                System.out.println(photopath[i]);
	                
	                
	            }
	            String test=json.getString("test");
	            String name=json.getString("name");
	            tv1.setText(name);
	            tv2.setText(plateNum[0]);	         
	            tv3.setText(pId[0]);
	            tv4.setText(fuelTime[0]);
	            String s = refQuantityint[0]+"";
	            tv5.setText(s);
	           
	        } catch (JSONException e) {  
	            e.printStackTrace();  
	        }  
		}
 		
	   
	}
	private static ArrayList<HashMap<String, Object>>  Analysis (String jsonStr) {
		 ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		 try{
			 
			 JSONObject jsonOb=new JSONObject(jsonStr);
			 
			 JSONArray jsonArray = new JSONArray(jsonOb.getString("carReal"));
			 for (int i = 0; i < jsonArray.length(); i++){
				HashMap<String, Object> map = new HashMap<String, Object>();
				JSONObject jsonObj2 = jsonArray.getJSONObject(i);
				String pId = jsonObj2.getString("pId");
		   		map.put("pId", pId);
		   		int  refQuantityint= jsonObj2.getInt("refQuantityint");
		   		map.put("refQuantityint", refQuantityint);
		   		String fulTime = jsonObj2.getString("fulTime");
		   		map.put("fulTime", fulTime);
		   		String refName = jsonObj2.getString("refName");
		   		map.put("refName", refName);
		   		int  refGun= jsonObj2.getInt("refGun");
		   		map.put("refGun", refGun);
		   		String  plateName = jsonObj2.getString("plateName");
		   		map.put("plateName", plateName);
		   		
		   		list.add(map);
			 }
			String test=jsonOb.getString("test");
			String name=jsonOb.getString("name");
			 
			}catch(Exception e){
				e.printStackTrace();
			}
	       return list;
	    }
	}

