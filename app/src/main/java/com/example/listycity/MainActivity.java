package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    EditText newCityEditText;
    Button addCityButton, confirmButton, deleteCityButton;
    LinearLayout addCityLayout;

    int selectedCity = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        newCityEditText = findViewById(R.id.new_city);
        addCityButton = findViewById(R.id.add_city_button);
        confirmButton = findViewById(R.id.confirm_button);
        deleteCityButton = findViewById(R.id.delete_city_button);
        addCityLayout = findViewById(R.id.add_city_layout);

        dataList = new ArrayList<>();
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Click to select
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = position;
        });

        addCityButton.setOnClickListener(v -> {
            addCityLayout.setVisibility(View.VISIBLE);
        });

        // Confirm button add city
        confirmButton.setOnClickListener(v -> {
            String cityName = newCityEditText.getText().toString().trim();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                newCityEditText.setText("");
                addCityLayout.setVisibility(View.GONE);
            }
        });

        // Delete selected city
        deleteCityButton.setOnClickListener(v -> {
            if (selectedCity >= 0 && selectedCity < dataList.size()) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                selectedCity = -1;
            }
        });
    }
}