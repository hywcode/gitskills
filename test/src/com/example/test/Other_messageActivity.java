package com.example.test;



import java.util.HashMap;

import org.json.JSONObject;

import com.example.test.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Other_messageActivity extends Activity{
	private TextView caseTitle = null;
	private TextView mainCase = null;
	private TextView accCorparation = null;
	private TextView caseState = null;
	private TextView telephone = null;
	private TextView memType = null;
	private TextView caseNum = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_message);
		Intent intent = getIntent();
		String jsonInfo = intent.getExtras().getString("jsonInfo");
		HashMap<String, Object> map = Analysis(jsonInfo);
		caseTitle = (TextView)findViewById(R.id.caseTitle);
		caseTitle.setText(map.get("caseTitle").toString());
		mainCase = (TextView)findViewById(R.id.mainCase);
		mainCase.setText(map.get("mainCase").toString());
		accCorparation = (TextView)findViewById(R.id.accCorparation);
		accCorparation.setText(map.get("accCorparation").toString());
		caseState = (TextView)findViewById(R.id.caseState);
		String caseStateStr = map.get("caseState").toString();
		if(caseStateStr.equals("0")){
			caseState.setText("正常");
		}else {
			caseState.setText("立案");
		}
		
		telephone = (TextView)findViewById(R.id.telephone);
		telephone.setText(map.get("telephone").toString());
		memType = (TextView)findViewById(R.id.memType);
		String memTypeStr = map.get("memType").toString();
		if(memTypeStr.equals("0")){
			memType.setText("正常");
		}else if(memTypeStr.equals("1")){
			memType.setText("嫌疑犯");
		}else{
			memType.setText("在逃犯");
		}
		caseNum = (TextView)findViewById(R.id.caseNum);
		caseNum.setText(map.get("caseNum").toString());
	}
	
	private static HashMap<String, Object> Analysis(String jsonStr) {
	   	 HashMap<String, Object> map = new HashMap<String, Object>();
	   	 try{
	   		JSONObject jsonObj = new JSONObject(jsonStr);
	   		String test = jsonObj.getString("test");
	   		map.put("test", test);
	   		JSONObject jsonObj2 = new JSONObject(jsonObj.getString("cirmeBasic"));
	   		String pId = jsonObj2.getString("pId");
	   		map.put("pId", pId);
	   		String caseTitle = jsonObj2.getString("caseTitle");
	   		map.put("caseTitle", caseTitle);
	   		String mainCase = jsonObj2.getString("mainCase");
	   		map.put("mainCase", mainCase);
	   		String accCorparation = jsonObj2.getString("accCorparation");
	   		map.put("accCorparation", accCorparation);
	   		String caseState = jsonObj2.getString("caseState");
	   		map.put("caseState", caseState);
	   		String telephone = jsonObj2.getString("telephone");
	   		map.put("telephone", telephone);
	   		int memType = jsonObj2.getInt("memType");
	   		map.put("memType", memType);
	   		String caseNum = jsonObj2.getString("caseNum");
	   		map.put("caseNum", caseNum);
			}catch(Exception e){
				e.printStackTrace();
			}
	       return map;
	    }
}

