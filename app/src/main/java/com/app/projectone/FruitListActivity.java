package com.app.projectone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FruitListActivity extends AppCompatActivity {

    ArrayList<FruitData> fruitDataArrayList = new ArrayList<>();

    ListView lv_fruit;

    String[] fruit_name = {"apple","asparagus","aubergine","avocado", "bacon","baguette",
            "banana", "beans", "biscuit", "blueberries", "boiled", "bowl"
            ,"bread", "broccoli", "cake"};

    int[] fruit_image_id = {R.drawable.apple,R.drawable.asparagus,R.drawable.aubergine,R.drawable.avocado,
            R.drawable.bacon,R.drawable.baguette, R.drawable.banana, R.drawable.beans,
            R.drawable.biscuit, R.drawable.blueberries, R.drawable.boiled, R.drawable.bowl,
            R.drawable.bread, R.drawable.broccoli, R.drawable.cake};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_list);

        lv_fruit = (ListView)findViewById(R.id.lv_fruit);

        for (int i = 0; i<fruit_name.length; i++){
            FruitData data = new FruitData();
            data.setF_name(fruit_name[i]);
            data.setF_image(fruit_image_id[i]);
            data.setF_details("This dummy details");
            fruitDataArrayList.add(data);
        }

        FruitListAdapter adapter = new FruitListAdapter(this,fruitDataArrayList);
        lv_fruit.setAdapter(adapter);

        lv_fruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FruitData data = fruitDataArrayList.get(i);
                Toast.makeText(FruitListActivity.this,"Click--"+data.getF_name(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(FruitListActivity.this, FruitDetailsActivity.class);
                intent.putExtra("iFname", data.getF_name());
                intent.putExtra("iFdetails", data.getF_details());
                intent.putExtra("iFimage", data.getF_image());
                startActivity(intent);

            }
        });

    }
}
