package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.bmicalculator.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.activityMainResultcard.setVisibility(View.GONE);

        binding.activityMainCalculate.setOnClickListener(view -> {
                if (binding.activityMainWeightkgs.length() == 0 || binding.activityMainHeightcm.length() == 0) {
                    Toast.makeText(MainActivity.this, "Populate Weight and Height to Calculate BMI", Toast.LENGTH_SHORT).show();
                } else {
                    double heightInCms = Double.parseDouble(binding.activityMainHeightcm.getText().toString());
                    double weightInKgs = Double.parseDouble(binding.activityMainWeightkgs.getText().toString());
                    double bmi = BMICalcUtil.getInstance().calculateBMIMetric(heightInCms, weightInKgs);
                    displayBMI(bmi);
                    binding.activityMainCalculate.setVisibility(View.INVISIBLE);
                }
        });

        binding.calculateAgain.setOnClickListener(v -> {

            binding.activityMainResultcard.setVisibility(View.INVISIBLE);
            binding.activityMainCalculate.setVisibility(View.VISIBLE);

            binding.activityMainWeightkgs.setText("");
            binding.activityMainHeightcm.setText("");
        });

    }

    @SuppressLint("DefaultLocale")
    private void displayBMI(double bmi) {
        binding.activityMainResultcard.setVisibility(View.VISIBLE);

        binding.activityMainBmi.setText(String.format("%.2f", bmi));

        String bmiCategory = BMICalcUtil.getInstance().classifyBMI(bmi);
        binding.activityMainCategory.setText(bmiCategory);


    }
}
