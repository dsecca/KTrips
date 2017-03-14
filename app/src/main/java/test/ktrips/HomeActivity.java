package test.ktrips;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    protected ImageView imagebyXML;
    protected LinearLayout myLayout;
    ArrayList<String> activities;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);

        // Setting up the ListView

        // Creating List View
        ListView activityList=(ListView)findViewById(R.id.listViewHome);
        activities = new ArrayList<String>();
        getActivities();

        //creating new adapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,activities);

        // Set the adapter
        TextView textView = new TextView(this);
        textView.setText("Acitivities");
        activityList.addHeaderView(textView);

        activityList.setAdapter(arrayAdapter);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //myLayout = (LinearLayout)findViewById(R.id.myLayout);
        imagebyXML = (ImageView)findViewById(R.id.image);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



   public void  gotoPlanner() {

        Intent intent = new Intent(HomeActivity.this, PlanActivity.class);
        HomeActivity.this.startActivity(intent);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();
        switch (item.getItemId()) {

            case R.id.action_activities:
                //goToActivities();
                return true;
            case R.id.action_planner:
                gotoPlanner();
                return true;
            case R.id.action_account:
                //goToAccount();
                return true;
            case R.id.action_trips:
                //goToTrips();
                return true;
            case R.id.action_calendar:
                //goToCalendar();
                return true;
            case R.id.action_howitworks:
                //goToHowitworks();
                return true;
            case R.id.action_signout:
                //goToSignout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    void getActivities() {
        activities.add("Go to Peel Park");
        activities.add("Go to the Museum");
        activities.add("Watch a play");

    }




}
