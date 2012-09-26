// rat issue #6 : 9/23/2012
// Just created this activity

package com.efridge.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class MyFridgeActivity extends BaseActivity {

	boolean isDelVisible = false;
	FoodListAdapter foodListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) { // TODO

		setContentView(R.layout.myfridge_layout);
		super.onCreate(savedInstanceState);

		Button addFoodBtn = (Button) findViewById(R.id.addFoodBtn);
		Button editFoodBtn = (Button) findViewById(R.id.editFoodBtn);

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

				System.out.println("delBtn : " + delBtn);
				if (isDelVisible)
					delBtn.setVisibility(View.VISIBLE);
				else
					delBtn.setVisibility(View.INVISIBLE);
				
				foodListAdapter.notifyDataSetChanged();
			}
		});

		List<String> foods = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			foods.add("Chicken");
		}

		ListView foodListView = (ListView) findViewById(R.id.foodsList);
		foodListAdapter = new FoodListAdapter(foods);
		foodListView.setAdapter(foodListAdapter);
	}

	class FoodListAdapter extends BaseAdapter {

		List<String> foodList;

		FoodListAdapter(List<String> foodList) {
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

			TextView foodView = (TextView) row.findViewById(R.id.food);
			foodView.setText(this.foodList.get(position));

			ImageButton delButton = (ImageButton) row.findViewById(R.id.delBtn);
			if (isDelVisible) {

				delButton.setVisibility(View.VISIBLE);
			} else {
				delButton.setVisibility(View.INVISIBLE);
			}
			return row;
		}
	}
}
