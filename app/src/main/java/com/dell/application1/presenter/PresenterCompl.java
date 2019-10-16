package com.dell.application1.presenter;

import android.util.Log;

import com.dell.application1.model.People;
import com.dell.application1.view.ViewInterface;

import java.util.ArrayList;
import java.util.Random;

public class PresenterCompl implements IPresenter {

    ViewInterface.MainView mainView;
    public PresenterCompl(ViewInterface.MainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void displayData() {
        ArrayList<People> peopleList = new ArrayList<>();
        for(int i=0; i<5; i++){
            People people = new People(i,"X"+i,1110002220+i,"Khách hàng",(1110002220+i)+"@gmail.com",1);
            peopleList.add(people);
        }
        for(int i=5; i<10; i++){
            People people = new People(i,"D"+i,1113332220+i,"Nhà cung cấp",(1113332220+i)+"@gmail.com",0);
            peopleList.add(people);
        }
        for(int i=10; i<15; i++){
            People people = new People(i,"K"+i,1230002220+i,"Tất cả",(1230002220+i)+"@gmail.com",0);
            peopleList.add(people);
        }
        for(int i=19; i>14; i--){
            People people = new People(i,"W"+i,1110005670+i,"Khách hàng",(1110005670+i)+"@gmail.com",1);
            peopleList.add(people);
        }
        for(int i=20; i<25; i++){
            People people = new People(i,"O"+i,1230002310+i,"Tất cả",(1230002310+i)+"@gmail.com",0);
            peopleList.add(people);
        }
        for(int i=25; i<30; i++){
            People people = new People(i,"A"+i,1420002230+i,"Khách hàng",(1420002230+i)+"@gmail.com",0);
            peopleList.add(people);
        }
        for(int i=35; i>29; i--){
            People people = new People(i,"T"+i,1130003110+i,"Nhà cung cấp",(1130003110+i)+"@gmail.com",1);
            peopleList.add(people);
        }
        mainView.loadData(peopleList);
    }

    @Override
    public void displayDataToSearchingWithCategory(ArrayList<People> peopleList,String category) {
        ArrayList<People> peopleListSearch = new ArrayList<>();
        Log.e("SE",peopleList.get(3).getName()+"");
        for(int i = 0; i<peopleList.size(); i++) {
            if (peopleList.get(i).getCategory() == category) {
                peopleListSearch.add(peopleList.get(i));
            }
        }
        for(int i = 0; i<peopleList.size(); i++) {
            if (peopleList.get(i).getCategory() == "Tất cả") {
                peopleListSearch.add(peopleList.get(i));
            }
        }
        mainView.searchOption(peopleListSearch);
    }

    @Override
    public void displayDataSearchingAll(ArrayList<People> peopleList, String category, String text, String mail, int active) {
        Log.e("TEXT",text+"");
        ArrayList<People> peopleListSearch = new ArrayList<>();
        for(int i = 0; i<peopleList.size(); i++) {
            People people = peopleList.get(i);
            String numberPhone = String.valueOf(people.getPhoneNumber());
            String id = String.valueOf(people.getId());
            if(text.isEmpty() && mail.isEmpty()&&people.getCategory().equals(category) && people.getActive() == active) {
                peopleListSearch.add(people);
            }else if(!text.isEmpty() && mail.isEmpty() &&(people.getName().equals(text) || numberPhone.equals(text) || id.equals(text)) && people.getCategory().equals(category) && people.getActive() == active){
                peopleListSearch.add(people);
            }else if(text.isEmpty() && !mail.isEmpty()&&people.getMail().equals(mail) && people.getCategory().equals(category) && people.getActive() == active){
                peopleListSearch.add(people);
            }else if(!text.isEmpty() && !mail.isEmpty() && (people.getName().equals(text) || numberPhone.equals(text) || id.equals(text)) && people.getMail().equals(mail) && people.getCategory().equals(category) && people.getActive() == active){
                peopleListSearch.add(people);
            }
        }
        mainView.searchOption(peopleListSearch);
    }
}
