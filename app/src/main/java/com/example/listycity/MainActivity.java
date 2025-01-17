package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> cityList;
    ArrayAdapter<String> cityAdapter;
    ListView cityListView;
    EditText newCityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityListView = findViewById(R.id.city_list_view);
        newCityInput = findViewById(R.id.new_city_input);
        Button addCityButton = findViewById(R.id.add_city_button);
        Button deleteCityButton = findViewById(R.id.delete_city_button);

        cityList = new ArrayList<>();
        cityList.add("Edmonton");
        cityList.add("Calgary");
        cityList.add("Vancouver");
        cityList.add("Toronto");


        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityList);
        cityListView.setAdapter(cityAdapter);


        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity = newCityInput.getText().toString().trim();
                if (!newCity.isEmpty()) {
                    cityList.add(newCity);
                    cityAdapter.notifyDataSetChanged();
                    newCityInput.setText(""); // Clear input field
                }
            }
        });

        // Delete the selected city when "DELETE CITY" is clicked
        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Highlight the selected city
                String selectedCity = cityList.get(position);
                deleteCityButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cityList.remove(selectedCity);
                        cityAdapter.notifyDataSetChanged(); // Refresh the ListView
                    }
                });
            }
        });
    }
}
