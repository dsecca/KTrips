package test.ktrips;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {

    // Initializing Variables
     ArrayList<String> locations;
    public static String newline = System.getProperty("line.separator");


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        // Creating List View
        ListView locationList=(ListView)findViewById(R.id.listview);
        locations = new ArrayList<String>();
        getLocations();

        //creating new adapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,locations);

        // Set the adapter
        locationList.setAdapter(arrayAdapter);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoShopping();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();




            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    // When Clicking the Fab, it sends you to the next Actity
    public void  gotoShopping() {

        Intent intent = new Intent(PlanActivity.this, ShoppingCartActivity.class);
        PlanActivity.this.startActivity(intent);
    }


void getLocations()
{


    locations.add("Activity : Museum " + newline + "Time: 14:30" + newline + "Cost : $ 5.00" +  newline +"Direction");
    locations.add("Activity : Museum " + newline + "Time: 14:30" + newline + "Cost : $ 5.00" +  newline +"Direction");
    locations.add("Activity : Museum " + newline + "Time: 14:30" + newline + "Cost : $ 5.00" +  newline +"Direction");
    locations.add("Activity : Museum " + newline + "Time: 14:30" + newline + "Cost : $ 5.00" +  newline +"Direction");


}



}
