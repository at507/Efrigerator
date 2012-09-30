// rat issue #2 : 9/23/2012
// Add tab for navigation with option
// All other activities will inherit this activity

package com.efridge.activity;

import java.io.File;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import com.efridge.dbutils.DbHelper;
import com.efridge.listener.TabListener;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		String dbPath = DbHelper.DB_PATH + DbHelper.getDbName();
		File file = new File(dbPath);

		System.out.println("db name  " + DbHelper.getDbName());
		System.out.println("Exists " + file.exists());
		
		DbHelper.isDbPresent = file.exists();
		
		DbHelper.init(this);
		SQLiteDatabase db = null;
		
//		if(DbHelper.isDbPresent){
			db = DbHelper.sharedDbHelper().getReadableDatabase();	
		/*} else {
			
			DbHelper dbHelper = new DbHelper(this, "");
			db = dbHelper.getWritableDatabase();
		}*/
		 
		if(db != null){
			db.close();
		}

		DbHelper.init(this);
		
        Button calendarTab = (Button) findViewById(R.id.calendarTab);
        Button myFridgeTab = (Button) findViewById(R.id.myFridgeTab);
        Button settingTab = (Button) findViewById(R.id.settingTab);
        
        TabListener tabListener = new TabListener();
        
        calendarTab.setOnClickListener(tabListener);
        myFridgeTab.setOnClickListener(tabListener);
        settingTab.setOnClickListener(tabListener);
	}
}
