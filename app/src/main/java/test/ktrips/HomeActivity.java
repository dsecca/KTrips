package test.ktrips;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {

    protected ImageView imagebyXML;
    protected LinearLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);
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
                //goToPlanner();
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
}
