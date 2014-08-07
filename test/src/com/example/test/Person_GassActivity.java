package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.test.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Person_GassActivity extends Activity {

	private TextView fuelTimeView = null;
	private TextView refQuantityintView = null;
	private TextView plateNumView = null;
	private LinearLayout main = null;
	private LinearLayout linear1 = null;
	private TextView info = null;
	private TextView driver = null;
	private TextView pId = null;
	private ArrayList<TextView> listView = null;
	private Button returnBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.person_gas);
        
        main = new LinearLayout(Person_GassActivity.this);
        linear1 = new LinearLayout(Person_GassActivity.this);
        info = new TextView(Person_GassActivity.this);
        returnBtn = new Button(Person_GassActivity.this);
        driver = new TextView(Person_GassActivity.this);
        pId = new TextView(Person_GassActivity.this);
        listView = new ArrayList<TextView>();
        
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT); 
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); 
        LinearLayout.LayoutParams infoParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT); 
        LinearLayout.LayoutParams returnBtnParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams driverParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams pIdParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        infoParam.bottomMargin =15;
        infoParam.weight = 2;
        returnBtnParam.weight = 10;
        driverParam.leftMargin = 40;
        driverParam.rightMargin = 20;
        driverParam.topMargin = 10;
        pIdParam.leftMargin = 40;
        pIdParam.rightMargin = 20;
        pIdParam.topMargin = 10;
        
        info.setPadding(18, 18, 18, 18);
        info.setBackgroundColor(Color.GREEN);
        info.setText("驾驶证信息");
        info.setLayoutParams(infoParam);
        returnBtn.setGravity(Gravity.CENTER);
        returnBtn.setBackgroundColor(Color.WHITE);
        returnBtn.setPadding(18, 18, 18, 18);
        returnBtn.setLayoutParams(returnBtnParam);
        returnBtn.setText("返回");
        driver.setGravity(Gravity.CENTER);
        driver.setText("驾驶人： 张卫红");
        driver.setLayoutParams(driverParam);
        pId.setGravity(Gravity.CENTER);
        pId.setText("身份证：123456199104285781");
        
        linear1.addView(info);
        linear1.addView(returnBtn);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setBackgroundColor(0xF0F0F0);
        main.addView(linear1);
        main.addView(driver);
        main.addView(pId);
        
        Intent intent = getIntent();
        //fuelTimeView = (TextView)findViewById(R.id.fuelTime);
        //refQuantityintView = (TextView)findViewById(R.id.refQuantityint);
        //plateNumView = (TextView)findViewById(R.id.plateNum);
        
		String jsonInfo = intent.getExtras().getString("jsonInfo");
		System.out.println(jsonInfo);
        ArrayList<HashMap<String, Object>> list = Analysis(jsonInfo);
        String test = list.get(0).get("test").toString();        
        String fuelException = list.get(1).get("fuelException").toString();
        String name = list.get(2).get("name").toString();
        String pid=null;
        for(int i = 3; i < list.size(); i++){
        	LinearLayout.LayoutParams viewParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        	viewParam.leftMargin = 40;
        	viewParam.rightMargin = 20;
        	TextView view1 = new TextView(Person_GassActivity.this);
        	TextView view2 = new TextView(Person_GassActivity.this);
        	view1.setLayoutParams(viewParam);
        	view2.setLayoutParams(viewParam);
        	view1.setGravity(Gravity.CENTER);
        	view2.setGravity(Gravity.CENTER);
        	HashMap<String, Object> map = list.get(i);
        	pid= map.get("pId").toString();
        	String fuelTime = map.get("fuelTime").toString();
        	String refQuantityint = map.get("refQuantityint").toString();
        	String plateNum = map.get("plateNum").toString();
        	view1.setText("加油时间： "+fuelTime);
        	view2.setText("加油量（升）：" + refQuantityint + " 车牌号码：" + plateNum);
        	main.addView(view1);
        	main.addView(view2);
        }
        driver.setText("驾驶人："+name);
        pId.setText("身份证："+pid);
        /*HashMap<String, Object> map = list.get(2);
        String pId = map.get("pId").toString();
        String refQuantityint = map.get("refQuantityint").toString();
        String fuelTime = map.get("fuelTime").toString();
        String refName = map.get("refName").toString();
        String plateNum = map.get("plateNum").toString();
        fuelTimeView.setText(fuelTime);*/
        //refQuantityintView.setText(refQuantityint);
        //plateNumView.setText(plateNum);
        super.setContentView(main, mainParams);  
        
	}
	
	/**
     * 解析
     * 
     * @throws JSONException
     */
	private static ArrayList<HashMap<String, Object>> Analysis(String jsonStr) {
		 ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		 try{
			 JSONObject jsonObj = new JSONObject(jsonStr);
			 String test = jsonObj.getString("test");
			 HashMap<String, Object> testMap = new HashMap<String, Object>();
			 testMap.put("test", test);
			 list.add(testMap);
			 HashMap<String, Object> fuelMap = new HashMap<String, Object>();
			 int fuelException = jsonObj.getInt("fuelException");
			 fuelMap.put("fuelException",fuelException);
			 list.add(fuelMap);
			 String name = jsonObj.getString("name");
			 HashMap<String, Object> nameMap = new HashMap<String, Object>();
			 nameMap.put("name", name);
			 list.add(nameMap);
			 JSONArray jsonArray = new JSONArray(jsonObj.getString("personBasic"));
			 for (int i = 0; i < jsonArray.length(); i++){
				HashMap<String, Object> map = new HashMap<String, Object>();
				JSONObject jsonObj2 = jsonArray.getJSONObject(i);
				String pId = jsonObj2.getString("pId");
		   		map.put("pId", pId);
		   		int refQuantityint = jsonObj2.getInt("refQuantityint");
		   		map.put("refQuantityint", refQuantityint);
		   		String fuelTime = jsonObj2.getString("fuelTime");
		   		map.put("fuelTime", fuelTime);
		   		String refName = jsonObj2.getString("refName");
		   		map.put("refName", refName);
		   		int refGun = jsonObj2.getInt("refGun");
		   		map.put("refGun", refGun);
		   		String plateNum = jsonObj2.getString("plateNum");
		   		map.put("plateNum", plateNum);
		   		int angle = jsonObj2.getInt("angle");
		   		map.put("angle", angle);
		   		String photoPath = jsonObj2.getString("photoPath");
		   		map.put("photoPath", photoPath);
		   		list.add(map);
			 }
			
			}catch(Exception e){
				e.printStackTrace();
			}
	       return list;
	    }
}