package test.ktrips;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.sql.Types.NULL;

public class LoginActivity extends AppCompatActivity {

    protected EditText username_login;
    protected EditText password_login;
    protected Button btn_login;
    protected Button btn_signup;
    protected DBHandler dbh; //(Ahmed)
    Toast toast; //(Ahmed)

    String theUsername; //(Ahmed)
    String thePassword; //(Ahmed)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbh = new DBHandler(this, null, null, 1); //Create new DBHandler (Ahmed)

        if(dbh.userExist()) { //Check if there exists a user (Ahmed)
            if (dbh.getSessionStatus() == 0) { //Check if the session if OFF (Ahmed)

                username_login = (EditText) findViewById(R.id.email_login);
                password_login = (EditText) findViewById(R.id.password_login);
                btn_login = (Button) findViewById(R.id.btn_login);
                btn_signup = (Button) findViewById(R.id.btn_signup);



            }else{
                gotoHomeActivity();
            }
        }else{
            gotoRegisterActivity();
        }


        //Set button listeners
       /* btn_login.setOnClickListener(onClickbtn_login);
        btn_signup.setOnClickListener(onClickbtn_signup);*/
    }


    public void loginBtnClicked(View v){ //onClick method for login button (Ahmed)

        theUsername = username_login.getText().toString(); //(Ahmed)
        thePassword = password_login.getText().toString(); //(Ahmed)

        if(!theUsername.isEmpty()){
            if(!thePassword.isEmpty()){
                if((dbh.validateUser(theUsername, thePassword))==true){ //Validate username and password (Ahmed)
                    if(dbh.openSession()==true){ //Open session (Ahmed)
                        gotoHomeActivity();
                    }else{
                        toast = Toast.makeText(getApplicationContext(), "FAILED TO LOGIN", Toast.LENGTH_SHORT); //(Ahmed)
                        toast.show(); //(Ahmed)
                    }
                }else{
                    toast = Toast.makeText(getApplicationContext(), "WRONG Username or Password!", Toast.LENGTH_SHORT); //(Ahmed)
                    toast.show(); //(Ahmed)
                }

            }else{
                toast = Toast.makeText(getApplicationContext(), "Please enter your password!", Toast.LENGTH_SHORT); //(Ahmed)
                toast.show(); //(Ahmed)
            }
        }else{
            toast = Toast.makeText(getApplicationContext(), "Please enter your username!", Toast.LENGTH_SHORT); //(Ahmed)
            toast.show(); //(Ahmed)
        }

    }

    public void forgotBtnClicked(View v) { //onClick method for Forgot Password button (Ahmed)
        gotoForgotActivity();
    }


 /*   //Listeners
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


            //Validate input
            if(email.isEmpty() && password.isEmpty()){
                Context context = getApplicationContext();
                CharSequence text = "Please enter your email and password!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else if(email.isEmpty()){
                Context context = getApplicationContext();
                CharSequence text = "Please enter your email!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            else if(password.isEmpty()){
                Context context = getApplicationContext();
                CharSequence text = "Please enter your password!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else{
                //Pass email and password to validator
            *//*if(db.validateUser(email, password)){
                //Get the session
            }*//*

                //NOTE: For the purposes of the demo, we will automatically bring the user to the home page
                Intent changeToActivityHome = new Intent(LoginActivity.this, HomeActivity.class);
                LoginActivity.this.startActivity(changeToActivityHome);
            }



        }
    };*/

    @Override
    public void onResume(){ //Override the onResume method to redirect the user to the appropriate activity. (Ahmed)
        super.onResume();
        if(!dbh.userExist()){ //Check if there exists a user (Ahmed)
            gotoRegisterActivity();
        }
        if(dbh.getSessionStatus()==1){ //Check if the session if ON (Ahmed)
            gotoHomeActivity();
        }

    }

    private Button.OnClickListener onClickbtn_signup = new Button.OnClickListener(){
        //Whenever signup button is clicked, the method below is called
        public void onClick(View v){

            Intent changeToActivityRegister = new Intent(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(changeToActivityRegister);
        }
    };

    public void gotoHomeActivity(){ //Public method that opens LoginActivity (Ahmed)
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void gotoRegisterActivity(){ //Public method that opens RegisterActivity (Ahmed)
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void gotoForgotActivity(){ //Public method that opens FrogotPassword Activity (Ahmed)
        Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
        startActivity(intent);
    }

}
