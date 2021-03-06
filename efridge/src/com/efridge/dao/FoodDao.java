package com.efridge.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.efridge.model.FoodModel;

public class FoodDao extends AbstractDao {

	public FoodDao(SQLiteDatabase db) {
		this.db = db;
	}
	
	public void insertFood(FoodModel foodModel) throws SQLException {
		ContentValues values = new ContentValues();

		values.put("food_name", foodModel.getFoodName());
		values.put("food_description", foodModel.getFoodDescription());
		values.put("exp_date", foodModel.getExpiryDate());
		db.insertOrThrow("food_details", null, values);
	}
	
	public void updateFoodDetails(FoodModel foodModel) throws SQLException {
		ContentValues resetValue = new ContentValues();

		resetValue.put("food_name", foodModel.getFoodName());
		resetValue.put("food_description", foodModel.getFoodDescription());
		resetValue.put("exp_date", foodModel.getExpiryDate());
		
		String  queryWhere = "food_id = " + foodModel.getFoodId(); 
		db.update("food_details", resetValue, queryWhere, null);
	}
	
	public List<FoodModel> getFoods() {
		List<FoodModel> foodList = new ArrayList<FoodModel>();

		String sql = "SELECT food_id, food_name, food_description, exp_date FROM food_details";
		Cursor cursor = db.rawQuery(sql, null);

		while (cursor.moveToNext()) {
			foodList.add(foodsFromCursor(cursor));
		}
		cursor.close();

		return foodList;
	}
	
	public FoodModel getFoodById(long foodId) {
		FoodModel food = null;

		String sql = "SELECT food_id, food_name, food_description, exp_date FROM food_details WHERE food_id = " + foodId;
		Cursor cursor = db.rawQuery(sql, null);

		while (cursor.moveToNext()) {
			food = foodsFromCursor(cursor);
		}
		cursor.close();

		return food;
	}
	
	private FoodModel foodsFromCursor(Cursor cursor) {
		
		FoodModel food = new FoodModel();
		food.setFoodId(cursor.getLong(0));
		food.setFoodName(cursor.getString(1));
		food.setFoodDescription(cursor.getString(2));
		food.setExpiryDate(cursor.getLong(3));
		
		return food;
	}
	
	public void deleteFood(long id) {
		
		String queryWhere = "food_id = " + id;
		db.delete("food_details", queryWhere, null);
		
	}
}
