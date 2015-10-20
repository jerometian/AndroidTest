package com.example.jerometian.listviewtest;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

   /* private String[] data = { "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,data);

        ListView lv = (ListView)findViewById(R.id.list_view);
        lv.setAdapter(adapter);

    }*/

    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter  = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);

        ListView lv = ( ListView)findViewById(R.id.list_view);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    private void initFruits() {
        Fruit apple = new Fruit("Apple", R.drawable.banana);
        fruitList.add(apple);
        Fruit banana = new Fruit("Banana", R.drawable.banana);
        fruitList.add(banana);
        Fruit orange = new Fruit("Orange", R.drawable.banana);
        fruitList.add(orange);
        Fruit watermelon = new Fruit("Watermelon", R.drawable.banana);
        fruitList.add(watermelon);
        Fruit pear = new Fruit("Pear", R.drawable.bread_pic);
        fruitList.add(pear);
        Fruit grape = new Fruit("Grape", R.drawable.cake_pic);
        fruitList.add(grape);
        Fruit pineapple = new Fruit("Pineapple", R.drawable.capsicum);
        fruitList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.drawable.tomato_pic);
        fruitList.add(strawberry);
        Fruit cherry = new Fruit("Cherry", R.drawable.tomato_pic);
        fruitList.add(cherry);
        Fruit mango = new Fruit("Mango", R.drawable.tomato_pic);
        fruitList.add(mango);
    }
}
