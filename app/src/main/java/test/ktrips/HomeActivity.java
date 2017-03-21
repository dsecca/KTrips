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
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    protected ImageView imagebyXML;
    protected LinearLayout myLayout;
    ArrayList<String> activities;

    DBHandler dbh; //Declare a DBHandler (Ahmed)
    Toast toast; //(Ahmed)



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);


        dbh = new DBHandler(this, null, null, 1); //Create new DBHandler (Ahmed)

        if(dbh.userExist()){ //Check if there exists a user (Ahmed)
            if(dbh.getSessionStatus()==1){ //Check if the session if ON (Ahmed)

                //Get Create Trip and View Previous Trip buttons (David)

                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                //myLayout = (LinearLayout)findViewById(R.id.myLayout);
                imagebyXML = (ImageView)findViewById(R.id.image);

            }else{
                gotoLoginActivity();
            }
        }else{
            gotoRegisterActivity();
        }
    }


    @Override
    public void onResume(){ //Override the onResume method to redirect the user to the appropriate activity. (Ahmed)
        super.onResume();
        if(!dbh.userExist()){ //Check if there exists a user (Ahmed)
            gotoRegisterActivity();
        }
        if(dbh.getSessionStatus()==0){ //Check if the session if ON (Ahmed)
            gotoLoginActivity();
        }

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
                SignOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public void gotoLoginActivity(){ //Public method that opens LoginActivity (Ahmed)
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void gotoRegisterActivity(){ //Public method that opens RegisterActivity (Ahmed)
        Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void SignOut(){ //Public method that signs the user out (Ahmed)
        if(dbh.destroySession()==true){
            gotoLoginActivity();
        }else{
            toast = Toast.makeText(getApplicationContext(), "FAIL", Toast.LENGTH_SHORT); //(Ahmed)
            toast.show(); //(Ahmed)
        }
    }




}
