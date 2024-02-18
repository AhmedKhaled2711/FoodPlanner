package com.example.foodplanner;

// SharedViewModel.java
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodplanner.Model.Meal;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Meal> selectedItemMonday = new MutableLiveData<>();
    private MutableLiveData<Meal> selectedItemTuesday = new MutableLiveData<>();
    private MutableLiveData<Meal> selectedItemWednesday = new MutableLiveData<>();
    private MutableLiveData<Meal> selectedItemThursday = new MutableLiveData<>();
    private MutableLiveData<Meal> selectedItemFriday = new MutableLiveData<>();
    private MutableLiveData<Meal> selectedItemSaturday = new MutableLiveData<>();
    private MutableLiveData<Meal> selectedItemSunday = new MutableLiveData<>();

    // Setter methods for each day
    public void setSelectedItemMonday(Meal meal) {
        selectedItemMonday.setValue(meal);
    }

    public void setSelectedItemTuesday(Meal meal) {
        selectedItemTuesday.setValue(meal);
    }

    public void setSelectedItemWednesday(Meal meal) {
        selectedItemWednesday.setValue(meal);
    }

    public void setSelectedItemThursday(Meal meal) {
        selectedItemThursday.setValue(meal);
    }

    public void setSelectedItemFriday(Meal meal) {
        selectedItemFriday.setValue(meal);
    }

    public void setSelectedItemSaturday(Meal meal) {
        selectedItemSaturday.setValue(meal);
    }

    public void setSelectedItemSunday(Meal meal) {
        selectedItemSunday.setValue(meal);
    }

    // Getter methods for observing in the Fragment for each day
    public LiveData<Meal> getSelectedItemMonday() {
        return selectedItemMonday;
    }

    public LiveData<Meal> getSelectedItemTuesday() {
        return selectedItemTuesday;
    }

    public LiveData<Meal> getSelectedItemWednesday() {
        return selectedItemWednesday;
    }

    public LiveData<Meal> getSelectedItemThursday() {
        return selectedItemThursday;
    }

    public LiveData<Meal> getSelectedItemFriday() {
        return selectedItemFriday;
    }

    public LiveData<Meal> getSelectedItemSaturday() {
        return selectedItemSaturday;
    }

    public LiveData<Meal> getSelectedItemSunday() {
        return selectedItemSunday;
    }
}

