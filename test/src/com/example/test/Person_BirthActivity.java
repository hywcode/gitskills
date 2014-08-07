package com.example.test;

import java.util.HashMap;

import org.json.JSONObject;

import com.example.test.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Person_BirthActivity extends Activity {

	private TextView name = null;
	private TextView sex = null;
	private TextView nation = null;
	private TextView pId = null;
	private TextView birthday = null;
	private Button returnBtn = null;
	private TextView address=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_birth);
		Intent intent = getIntent();
		final String jsonInfo = intent.getExtras().getString("jsonInfo");
		final HashMap<String, Object> map = Analysis(jsonInfo);
		name = (TextView)findViewById(R.id.name);
		name.setText(map.get("name").toString());
		sex = (TextView)findViewById(R.id.sex);
		if(map.get("sex").toString().equals("1")){
			sex.setText("ÄÐ");
		}else{
			sex.setText("Å®");
		}
		nation = (TextView)findViewById(R.id.nation);
		nation.setText(map.get("nation").toString());
		pId = (TextView)findViewById(R.id.pId);
		pId.setText(map.get("pId").toString());
		birthday = (TextView)findViewById(R.id.birthday);
		birthday.setText(map.get("birthday").toString());
		address = (TextView)findViewById(R.id.address);
		address.setText(map.get("address").toString());
		
		returnBtn = (Button)findViewById(R.id.returnBtn);
		returnBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent =new Intent();
				intent.putExtra("pid", map.get("pId").toString());
				
				if (map.get("crime").toString().equals("1")) {
					intent.putExtra("crime", "1");
				}else{
					intent.putExtra("crime", "0");
				}
				
				intent.setClass(Person_BirthActivity.this, Person_messageActivity.class);
				startActivity(intent);
				System.out.println("Ö´ÐÐÁËintent.setClass(Person_BirthActivity.this, Person_messageActivity.class);");
			}
		});
	}
	
	private static HashMap<String, Object> Analysis(String jsonStr) {
   	 HashMap<String, Object> map = new HashMap<String, Object>();
   	 try{
   		 JSONObject jsonObj = new JSONObject(jsonStr);
   		 String test = jsonObj.getString("test");
   		 map.put("test", test);
   		 String crime = jsonObj.getString("crime");
   		 System.out.println("crime==="+crime);
   		 map.put("crime", crime);
   		 JSONObject jsonObj2 = new JSONObject(jsonObj.getString("personBasic"));
   		 String birthday = jsonObj2.getString("birthday");
   		 map.put("birthday", birthday);
   		 String pId = jsonObj2.getString("pId");
   		 map.put("pId", pId);
   		 int sex = jsonObj2.getInt("sex");
   		 map.put("sex", sex);
   		 String name = jsonObj2.getString("name");
   		 map.put("name", name);
   		 String avatar = jsonObj2.getString("avatar");
   		 map.put("avatar", avatar);
   		 String nation = jsonObj2.getString("nation");
  		 map.put("nation", nation);
   		 String address = jsonObj2.getString("address");
   		 map.put("address", address);
   		 //System.out.println(test + "--------" + perType);
		}catch(Exception e){
			e.printStackTrace();
		}
       return map;
    }

}