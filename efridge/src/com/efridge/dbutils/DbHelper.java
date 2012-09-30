package com.efridge.dbutils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	private static DbHelper dbHelper;
	
	public static boolean isDbPresent = false;

	public Context context;

	private static Properties dbProperties = null;
 
	public static final String DB_PATH = "/data/data/com.efridge.activity/databases/";
	
	static {
		try {
			InputStream propertiesInputStream = DbHelper.class.getResourceAsStream("/database.properties");
			dbProperties = new Properties();
			dbProperties.load(propertiesInputStream);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new ConfigurationException("Invalid database.properties");
		}
	}

	public static DbHelper sharedDbHelper() {
		if (dbHelper == null) {
			throw new IllegalStateException("DbHelper is not initialized. Did you forget to call DbHelper.init(Context context) method?");
		}
		return dbHelper;
	}

	public static void init(Context context) {
		/*if(isDbPresent){
			dbHelper = new DbHelper(context, getDbName());
		}*/
		if(isDbPresent){
			dbHelper = new DbHelper(context, getDbName());
		} else {
			dbHelper = new DbHelper(context, "tempdb");
		}
	}

	public DbHelper(Context context, String dbName) {
		super(context, dbName, null, getDbVersion());
		this.context = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {

		System.out.println("isDbPresent " + isDbPresent);
		if(!isDbPresent){
			try {
				System.out.println("----");
				copydatabase();
				isDbPresent = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public static String getDbName() {
		return dbProperties.getProperty("db.name");
	}

	private static int getDbVersion() {
		String versionStr = dbProperties.getProperty("db.vesion");

		try {
			int dbVersion = Integer.parseInt(versionStr);
			return dbVersion;
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			throw new ConfigurationException("Invalid db.vesion");
		}
	}
	
    private void copydatabase() throws IOException {

        // Open your local db as the input stream
        InputStream myinput = this.context.getResources().getAssets().open(DbHelper.getDbName()); 

        System.out.println("myinput " + myinput);
        // Path to the just created empty db
        String outfilename = DbHelper.DB_PATH + DbHelper.getDbName();

        // Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        
        while ((length = myinput.read(buffer)) > 0) {
        	
            myoutput.write(buffer, 0, length);
        }

        // Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();

    }
}
