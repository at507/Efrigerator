// rat issue #6 : 9/23/2012
// Just created this activity
// rat issue #3 : 9/29/2012
// Display all foods
// rat issue #3 : 9/29/2012
// User can remove food from efrige

package com.efridge.activity;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.efridge.model.FoodModel;

public class MyFridgeActivity extends BaseActivity {

	boolean isDelVisible = false;
	FoodListAdapter foodListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) { // TODO

		setContentView(R.layout.myfridge_layout);
		super.onCreate(savedInstanceState);

		List<FoodModel> foods = FoodModel.getFoods();
		ListView foodListView = (ListView) findViewById(R.id.foodsList);

		foodListAdapter = new FoodListAdapter(foods);
		foodListView.setAdapter(foodListAdapter);
		
		if(foods.size() == 0){
			
			Intent intent = new Intent(MyFridgeActivity.this, AddFoodActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			this.startActivity(intent);
		}
		
		Button addFoodBtn = (Button) findViewById(R.id.addFoodBtn);
		final Button editFoodBtn = (Button) findViewById(R.id.editFoodBtn);

		addFoodBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(v.getContext(), AddFoodActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				v.getContext().startActivity(intent);
			}
		});

		editFoodBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				ImageButton delBtn = (ImageButton) findViewById(R.id.delBtn);
				isDelVisible = !isDelVisible;

				if (isDelVisible) {
					delBtn.setVisibility(View.VISIBLE);
					editFoodBtn.setText("Cancel");
				} else {
					delBtn.setVisibility(View.INVISIBLE);
					editFoodBtn.setText("Edit");
				}
				
				foodListAdapter.notifyDataSetChanged();
			}
		});
	}

	class FoodListAdapter extends BaseAdapter {

		List<FoodModel> foodList;

		FoodListAdapter(List<FoodModel> foodList) {
			this.foodList = foodList;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.foodList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(R.layout.foodlist_layout, parent, false);

			FoodModel food = this.foodList.get(position);

			TextView foodView = (TextView) row.findViewById(R.id.food);
			foodView.setText(food.getFoodName());

			ImageButton delButton = (ImageButton) row.findViewById(R.id.delBtn);
			if (isDelVisible) {

				delButton.setVisibility(View.VISIBLE);
			} else {
				delButton.setVisibility(View.INVISIBLE);
			}
			
			final long foodId = food.getFoodId();
			final int foodIndex = position;
			ImageButton delBtn = (ImageButton) row.findViewById(R.id.delBtn);
			
			delBtn.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {

					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
					alertDialogBuilder.setTitle("Remove Food");
					alertDialogBuilder.setMessage("Are you sure?");
				
					alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				          public void onClick(DialogInterface dialog, int which) {
				     
				        	  FoodModel.deleteFood(foodId);
				        	  foodList.remove(foodIndex);
				        	  foodListAdapter.notifyDataSetChanged();
				        } });
				    	
					alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				          public void onClick(DialogInterface dialog, int which) {
				     
				        	  Log.v("---  ", " Food not removed.");
				        } });
				    
					alertDialogBuilder.show();
				}
			});
			return row;
		}
	}
}
