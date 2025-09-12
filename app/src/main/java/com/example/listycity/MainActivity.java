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
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText newCityEditText;
    Button addButton, deleteButton, confirmButton;
    int selectedCity = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        newCityEditText = findViewById(R.id.new_city);
        addButton = findViewById(R.id.add_city_button);
        confirmButton = findViewById(R.id.confirm_button);
        deleteButton = findViewById(R.id.delete_city_button);
        View addCityLayout = findViewById(R.id.add_city_layout);

        dataList = new ArrayList<>();
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Remember position when city is clicked
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = position;
        });

        addButton.setOnClickListener(v -> {
            addCityLayout.setVisibility(View.VISIBLE);
        });

        // Add city
        confirmButton.setOnClickListener(v -> {
            String cityName = newCityEditText.getText().toString().trim();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                newCityEditText.setText("");
            }
        });

        // Delete city
        deleteButton.setOnClickListener(v -> {
            if (selectedCity >= 0 && selectedCity < dataList.size()) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                selectedCity = -1; // reset selection
            }
        });
    }
}
