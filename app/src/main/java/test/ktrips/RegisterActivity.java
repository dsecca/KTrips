package test.ktrips;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    User theUser; //(Ahmed)

    DBHandler dbh; //Declare a DBHandler (Ahmed)
    Toast toast; //(Ahmed)

    String theUsername; //(Ahmed)
    String theEmail; //(Ahmed)
    String thePassword; //(Ahmed)
    String theConfirmPassword; //(Ahmed)
    String theCountry; //(Ahmed)
    String theDateOfBirth; //(Ahmed)
    String theMonth; //(Ahmed)
    String theDay; //(Ahmed)
    String theYear; //(Ahmed)

    EditText username; //Declare EditText  (Ahmed)
    EditText email; //Declare EditText (Ahmed)
    EditText password; //Declare EditText  (Ahmed)
    EditText ConfirmPassword; //Declare EditText  (Ahmed)
    EditText country; //Declare EditText  (Ahmed)
    EditText month; //Declare EditText  (Ahmed)
    EditText day; //Declare EditText  (Ahmed)
    EditText year; //Declare EditText  (Ahmed)

    Button createBtn; //Declare Button (Ahmed)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username); //Set it to username (Ahmed)
        email = (EditText) findViewById(R.id.email); //Set it to email (Ahmed)
        password = (EditText) findViewById(R.id.password); //Set it to password (Ahmed)
        ConfirmPassword = (EditText) findViewById(R.id.confirmPassword); //Set it to password (Ahmed)
        country = (EditText) findViewById(R.id.country); //Set it to country (Ahmed)
        year = (EditText) findViewById(R.id.year); //Set it to dateofbirth (Ahmed)
        day = (EditText) findViewById(R.id.day); //Set it to dateofbirth (Ahmed)
        month = (EditText) findViewById(R.id.month); //Set it to dateofbirth (Ahmed)

        //createBtn = (Button) findViewById(R.id.create_btn); //Set it to create_btn (Ahmed)

        theUser = new User(); //(Ahmed)

        dbh = new DBHandler(this, null, null, 1); //Create new DBHandler (Ahmed)

    }


    public void createBtnClicked(View v){ //onClick button for create account button (Ahmed)

        theUsername = username.getText().toString(); //(Ahmed)
        theEmail = email.getText().toString(); //(Ahmed)
        thePassword = password.getText().toString(); //(Ahmed)
        theConfirmPassword = ConfirmPassword.getText().toString(); //(Ahmed)
        theCountry = country.getText().toString(); //(Ahmed)
        theMonth = month.getText().toString(); //(Ahmed)
        theDay = day.getText().toString(); //(Ahmed)
        theYear = year.getText().toString(); //(Ahmed)
        theDateOfBirth = theMonth + "-" + theDay + "-" + theYear; //(Ahmed)

        if(!theUsername.isEmpty()) { //(Ahmed)
            if (!theEmail.isEmpty()) { //(Ahmed)
                if (!thePassword.isEmpty()) { //(Ahmed)
                    if (!theConfirmPassword.isEmpty()) { //(Ahmed)
                        if (!theCountry.isEmpty()) { //(Ahmed)
                            if (!theMonth.isEmpty()) { //(Ahmed)
                                if (!theYear.isEmpty()) { //(Ahmed)
                                    if (!theDay.isEmpty()) { //(Ahmed)
                                        if(thePassword.equals(theConfirmPassword)){ //(Ahmed)

                                            theUser.setUsername(theUsername); //(Ahmed)
                                            theUser.setEmail(theEmail); //(Ahmed)
                                            theUser.setPassword(thePassword);
                                            theUser.setCountry(theCountry); //(Ahmed)
                                            theUser.setDateOfBirth(theDateOfBirth); //(Ahmed)

                                            if((dbh.CreateUser(theUser))==2){ //(Ahmed)

                                                toast = Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT); //(Ahmed)
                                                toast.show(); //(Ahmed)
                                                gotoHomeActivity(); //(Ahmed)


                                            }else{
                                                toast = Toast.makeText(getApplicationContext(), "FAIL", Toast.LENGTH_SHORT); //(Ahmed)
                                                toast.show();//(Ahmed)
                                            }

                                        }else{

                                            toast = Toast.makeText(getApplicationContext(), "The passwords do not match.", Toast.LENGTH_SHORT); //(Ahmed)
                                            toast.show(); //(Ahmed)

                                        }

                                    }else{

                                        toast = Toast.makeText(getApplicationContext(), "Please enter your day of birth.", Toast.LENGTH_SHORT); //(Ahmed)
                                        toast.show(); //(Ahmed)

                                    }
                                }else{

                                    toast = Toast.makeText(getApplicationContext(), "Please enter your year of birth.", Toast.LENGTH_SHORT); //(Ahmed)
                                    toast.show(); //(Ahmed)

                                }

                            }else{

                                toast = Toast.makeText(getApplicationContext(), "Please enter your month of birth.", Toast.LENGTH_SHORT); //(Ahmed)
                                toast.show(); //(Ahmed)

                            }
                        }else{

                            toast = Toast.makeText(getApplicationContext(), "Please enter your country.", Toast.LENGTH_SHORT); //(Ahmed)
                            toast.show(); //(Ahmed)

                        }
                    }else{

                        toast = Toast.makeText(getApplicationContext(), "You must confirm your password.", Toast.LENGTH_SHORT); //(Ahmed)
                        toast.show(); //(Ahmed)

                    }
                }else{
                    toast = Toast.makeText(getApplicationContext(), "Please enter a password.", Toast.LENGTH_SHORT); //(Ahmed)
                    toast.show(); //(Ahmed)
                }
            }else{
                toast = Toast.makeText(getApplicationContext(), "Please enter your email address.", Toast.LENGTH_SHORT); //(Ahmed)
                toast.show(); //(Ahmed)
            }
        }else{

            toast = Toast.makeText(getApplicationContext(), "Please enter a username.", Toast.LENGTH_SHORT); //(Ahmed)
            toast.show(); //(Ahmed)

        }
    }

    @Override
    public void onResume(){ //Override the onResume method to redirect the user to the appropriate activity. (Ahmed)
        super.onResume();
        if(dbh.userExist()){ //Check if there exists a user (Ahmed)
            gotoLoginActivity();
        }
        if(dbh.getSessionStatus()==1){ //Check if the session if ON (Ahmed)
            gotoHomeActivity();
        }

    }

    public void gotoHomeActivity(){ //Public method that opens LoginActivity (Ahmed)
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void gotoLoginActivity(){ //Public method that opens RegisterActivity (Ahmed)
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
