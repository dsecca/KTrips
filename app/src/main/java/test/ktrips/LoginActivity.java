package test.ktrips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    protected EditText email_login;
    protected EditText password_login;
    protected Button btn_login;
    protected Button btn_signup;
    protected DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_login = (EditText) findViewById(R.id.email_login);
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
            String email, password;
            //1. Verify credentials (if have time in sprint 1 do this else in sprint 2)
            //2. Get a User object
            //3. Get it from database it in the database
            //4. Start session

           //Get the user input
            email = email_login.getText().toString();
            password = password_login.getText().toString();

            //Pass email and password to validator
            /*if(db.validateUser(email, password)){
                //Get the session
            }*/

            //NOTE: For the purposes of the demo, we will automatically bring the user to the home page
            Intent changeToActivityHome = new Intent(LoginActivity.this, HomeActivity.class);
            LoginActivity.this.startActivity(changeToActivityHome);



        }
    };

    private Button.OnClickListener onClickbtn_signup = new Button.OnClickListener(){
        //Whenever signup button is clicked, the method below is called
        public void onClick(View v){

            Intent changeToActivityRegister = new Intent(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(changeToActivityRegister);
        }
    };

}
