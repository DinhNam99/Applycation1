package com.dell.application1.presenter;

import com.dell.application1.model.People;

import java.util.ArrayList;

public interface IPresenter {
    void displayData();
    void displayDataToSearchingWithCategory(ArrayList<People> peopleList,String category);
    void displayDataSearchingAll(ArrayList<People> peopleList,String category,String text, String mail, int active);
}
