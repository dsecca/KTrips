package test.ktrips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ShoppingCartActivity extends AppCompatActivity {

   // Creating Buttons
    protected Button viewLocations;
    protected Button viewDataLocations;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        viewLocations = (Button) findViewById(R.id.view_location);

        OnClickButtonListenerMaps();



    }


    public void OnClickButtonListenerMaps(){
        //Creates Listener
        viewLocations.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent  = new Intent(ShoppingCartActivity.this,MapsActivity.class);
                        ShoppingCartActivity.this.startActivity(intent);
                    }
                }
        );
    }




}
