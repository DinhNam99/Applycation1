package com.dell.application1.view;

import com.dell.application1.model.People;

import java.util.ArrayList;

public class ViewInterface {

    public interface MainView{
        void loadData(ArrayList<People> peopleList);
        void searchOption(ArrayList<People> peopleList);
    }
}
