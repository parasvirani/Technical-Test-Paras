package com.example.technicaltestparas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.technicaltestparas.databinding.ActivityMainBinding;
import com.example.technicaltestparas.databinding.SlotItemBinding;
import com.example.technicaltestparas.model.GetSlotsDetailsRes;
import com.example.technicaltestparas.model.TimeModel;
import com.example.technicaltestparas.view_model.SlotsDetailsViewModel;
import com.google.android.material.chip.ChipGroup;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private View selectedItem = null;

    private SlotsDetailsViewModel slotsDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        slotsDetailsViewModel = new ViewModelProvider(this).get(SlotsDetailsViewModel.class);

        slotsDetailsViewModel.getSlotsDetailsObserver().observe(this, new Observer<GetSlotsDetailsRes>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(GetSlotsDetailsRes getSlotsDetailsRes) {
                binding.progressbar.setVisibility(View.GONE);
                binding.scrollview.setVisibility(View.VISIBLE);
                if (getSlotsDetailsRes != null) {
                    updateUI(getSlotsDetailsRes);
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.progressbar.setVisibility(View.VISIBLE);
        binding.scrollview.setVisibility(View.GONE);
        slotsDetailsViewModel.getSlotDetails();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updateUI(GetSlotsDetailsRes getSlotsDetailsRes) {
        for (TimeModel timeModel : getSlotsDetailsRes.getMsg().get(0).getTimeList()) {
            slotsDetailsViewModel.generateTimeSlots(timeModel.getStartTime(), timeModel.getEndTime(), timeModel.getDuration());
        }
        addData(slotsDetailsViewModel.getMorningSlots(), binding.morningChipGroup);
        addData(slotsDetailsViewModel.getAfternoonSlots(), binding.afternoonChipGroup);
        addData(slotsDetailsViewModel.getEveningSlots(), binding.eveningChipGroup);
        addData(slotsDetailsViewModel.getNightSlots(), binding.nightChipGroup);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addData(ArrayList<String> timeList, ChipGroup chipGroup) {
        for (String time : timeList) {
            SlotItemBinding slotItemBinding = SlotItemBinding.inflate(LayoutInflater.from(this), null, false);
            slotItemBinding.tvTime.setText(time);
            slotItemBinding.main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedItem != null) {
                        selectedItem.setSelected(false);
                    }
                    slotItemBinding.main.setSelected(true);
                    binding.btnBookAppointment.setText(getString(R.string.book_appointment_by) + " " + slotItemBinding.tvTime.getText());
                    selectedItem = slotItemBinding.main;
                }
            });
            chipGroup.addView(slotItemBinding.getRoot());
        }
    }
}
