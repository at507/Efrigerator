package com.efridge.model;

import java.util.List;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.efridge.dao.FoodDao;
import com.efridge.dbutils.DbHelper;

public class FoodModel {

	private long foodId;
	private String foodName;
	private String foodDescription;
	private long expiryDate;
	
	public long getFoodId() {
		return foodId;
	}

	public void setFoodId(long foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodDescription() {
		return foodDescription;
	}

	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}

	public long getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(long expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean saveToDb() {

		boolean transactionSucceeded = false;
		
		SQLiteDatabase db = DbHelper.sharedDbHelper().getWritableDatabase();
		db.beginTransaction();

		try {
			
			FoodDao foodDao = new FoodDao(db);
			foodDao.insertFood(this);

			db.setTransactionSuccessful();
			transactionSucceeded = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e("Error", e.getMessage());
		} finally {
			db.endTransaction();
			db.close();
		}

		return transactionSucceeded;
	}
	
	public boolean updateFoodDetails() {
		
		boolean transactionSucceeded = false;
		
		SQLiteDatabase db = DbHelper.sharedDbHelper().getWritableDatabase();
		db.beginTransaction();

		try {
			
			FoodDao foodDao = new FoodDao(db);
			foodDao.updateFoodDetails(this);

			db.setTransactionSuccessful();
			transactionSucceeded = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e("Error", e.getMessage());
		} finally {
			db.endTransaction();
			db.close();
		}

		return transactionSucceeded;
	}
	
	public static List<FoodModel> getFoods() {

		SQLiteDatabase db = DbHelper.sharedDbHelper().getReadableDatabase();
		FoodDao foodDao = new FoodDao(db);
		List<FoodModel> foodList = foodDao.getFoods();

		db.close();

		return foodList;
	}
	
	public static FoodModel getFoodById(long foodId) {

		SQLiteDatabase db = DbHelper.sharedDbHelper().getReadableDatabase();
		FoodDao foodDao = new FoodDao(db);
	
		FoodModel food = foodDao.getFoodById(foodId);
		db.close();

		return food;
	}
	
	public static void deleteFood(long id){
		
		SQLiteDatabase db = DbHelper.sharedDbHelper().getWritableDatabase();
		db.beginTransaction();

		try {
			FoodDao foodDao = new FoodDao(db);
			foodDao.deleteFood(id);

			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e("Error", e.getMessage());
		} finally {
			db.endTransaction();
			db.close();
		}
	}
}
