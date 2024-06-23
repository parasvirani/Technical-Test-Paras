package com.example.technicaltestparas.view_model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.technicaltestparas.apis.ApiInterface;
import com.example.technicaltestparas.apis.RetrofitInstance;
import com.example.technicaltestparas.model.GetSlotsDetailsRes;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlotsDetailsViewModel extends ViewModel {

    private MutableLiveData<GetSlotsDetailsRes> getSlotsDetailsResMutableLiveData;
    private ArrayList<String> morningSlots = new ArrayList<>();
    private ArrayList<String> afternoonSlots = new ArrayList<>();
    private ArrayList<String> eveningSlots = new ArrayList<>();
    private ArrayList<String> nightSlots = new ArrayList<>();

    public SlotsDetailsViewModel() {
        getSlotsDetailsResMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<GetSlotsDetailsRes> getSlotsDetailsObserver() {
        return getSlotsDetailsResMutableLiveData;
    }

    public void getSlotDetails() {
        ApiInterface service = RetrofitInstance.getRetroClient().create(ApiInterface.class);
        Call<GetSlotsDetailsRes> call = service.getSlotsDetails();
        call.enqueue(new Callback<GetSlotsDetailsRes>() {
            @Override
            public void onResponse(Call<GetSlotsDetailsRes> call, Response<GetSlotsDetailsRes> response) {
                if (response.isSuccessful() && response.body() != null) {
                    getSlotsDetailsResMutableLiveData.postValue(response.body());
                } else {
                    getSlotsDetailsResMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetSlotsDetailsRes> call, Throwable t) {
                getSlotsDetailsResMutableLiveData.postValue(null);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void generateTimeSlots(String startTime, String endTime, int duration) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime start = LocalTime.parse(startTime, timeFormatter);
        LocalTime end = LocalTime.parse(endTime, timeFormatter);

        LocalTime morningEnd = LocalTime.of(11, 59);
        LocalTime afternoonStart = LocalTime.of(12, 0);
        LocalTime afternoonEnd = LocalTime.of(15, 59);
        LocalTime eveningStart = LocalTime.of(16, 0);
        LocalTime eveningEnd = LocalTime.of(20, 59);
        LocalTime nightStart = LocalTime.of(21, 0);
        LocalTime nightEnd = LocalTime.of(23, 59);

        while (!start.isAfter(end)) {
            String formattedTime = start.format(outputFormatter);
            if (!start.isAfter(morningEnd)) {
                morningSlots.add(formattedTime);
            } else if (!start.isBefore(afternoonStart) && !start.isAfter(afternoonEnd)) {
                afternoonSlots.add(formattedTime);
            } else if (!start.isBefore(eveningStart) && !start.isAfter(eveningEnd)) {
                eveningSlots.add(formattedTime);
            } else if (!start.isBefore(nightStart) && !start.isAfter(nightEnd)) {
                nightSlots.add(formattedTime);
            }
            start = start.plusMinutes(duration);
        }
    }

    public ArrayList<String> getMorningSlots() {
        return morningSlots;
    }

    public ArrayList<String> getAfternoonSlots() {
        return afternoonSlots;
    }

    public ArrayList<String> getEveningSlots() {
        return eveningSlots;
    }

    public ArrayList<String> getNightSlots() {
        return nightSlots;
    }
}
