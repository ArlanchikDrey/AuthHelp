package com.example.authhelp;

public class ItemsForCardView {
    private String day;
    private String firstItem;
    private String secondItem;
    private String thirdItem;

    public ItemsForCardView(String day, String firstItem, String secondItem, String thirdItem) {
        this.day = day;
        this.firstItem = firstItem;
        this.secondItem = secondItem;
        this.thirdItem = thirdItem;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFirstItem() {
        return firstItem;
    }

    public void setFirstItem(String firstItem) {
        this.firstItem = firstItem;
    }

    public String getSecondItem() {
        return secondItem;
    }

    public void setSecondItem(String secondItem) {
        this.secondItem = secondItem;
    }

    public String getThirdItem() {
        return thirdItem;
    }

    public void setThirdItem(String thirdItem) {
        this.thirdItem = thirdItem;
    }
}
