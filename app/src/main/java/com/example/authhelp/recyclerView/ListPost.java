package com.example.authhelp.recyclerView;

public class ListPost {
    public String buyORsell;
    public String finish_price;
    public String day;
    public String key;
    public String number_material;
    public String price_once;

    public ListPost(String buyORsell, String finish_price, String day, String key,
                    String number_material, String price_once) {
        this.buyORsell = buyORsell;
        this.finish_price = finish_price;
        this.day = day;
        this.key = key;
        this.number_material = number_material;
        this.price_once = price_once;
    }



    public ListPost(){

    }



}
