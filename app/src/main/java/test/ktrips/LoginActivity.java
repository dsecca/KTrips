package test.ktrips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    protected EditText username_login;
    protected EditText password_login;
    protected Button btn_login;
    protected Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_login = (EditText) findViewById(R.id.username_login);
        password_login = (EditText) findViewById(R.id.password_login);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signup = (Button) findViewById(R.id.btn_signup);

        //Set button listeners
        btn_login.setOnClickListener(onClickbtn_login);
        btn_signup.setOnClickListener(onClickbtn_signup);
    }

    //Listeners
    private Button.OnClickListener onClickbtn_login = new Button.OnClickListener(){
        //Whenever login button is clicked, the method below is called
        public void onClick(View v){
            //1. Verify credentials (if have time in sprint 1 do this else in sprint 2)
            //2. Get a User object
            //3. Get it from database it in the database
            //4. Start session
        }
    };

    private Button.OnClickListener onClickbtn_signup = new Button.OnClickListener(){
        //Whenever signup button is clicked, the method below is called
        public void onClick(View v){

            //1. Verify credentials (if have time in spritn 1 do this else in sprint 2)
            //2. create an User object
            //3. put it in the database
            //4. Start session
        }
    };

}
