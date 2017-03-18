package test.ktrips;

/*Create by Ahmed*/

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.AccessController;
import java.security.Provider;
import java.security.Security;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import java.util.Date;
import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ForgotPassword extends AppCompatActivity {

    protected DBHandler dbh; //(Ahmed)
    Toast toast; //(Ahmed)

    EditText email; //(Ahmed)
    EditText NewPassword; //(Ahmed)
    EditText ConfirmNewPassword; //(Ahmed)

    Button send; //(Ahmed)
    Button confirm; //(Ahmed)
    Button create; //(Ahmed)

    User usr; //(Ahmed)

    String theEmail; //(Ahmed)
    String theCode; //(Ahmed)

    String theNewPassword; //(Ahmed)
    String theConfirmNewPassword; //(Ahmed)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        dbh = new DBHandler(this, null, null, 1); //Create new DBHandler (Ahmed)

        if(dbh.userExist()) { //Check if there exists a user (Ahmed)
            if (dbh.getSessionStatus() == 0) { //Check if the session if OFF (Ahmed)


                email = (EditText) findViewById(R.id.email); //(Ahmed)
                NewPassword = (EditText) findViewById(R.id.password); //(Ahmed)
                ConfirmNewPassword = (EditText) findViewById(R.id.ConfirmPassword); //(Ahmed)

                send = (Button) findViewById(R.id.send); //(Ahmed)
                confirm = (Button) findViewById(R.id.confirm); //(Ahmed)
                create = (Button) findViewById(R.id.createNewPass); //(Ahmed)

                confirm.setVisibility(View.GONE); //(Ahmed)
                create.setVisibility(View.GONE); //(Ahmed)
                NewPassword.setVisibility(View.GONE); //(Ahmed)
                ConfirmNewPassword.setVisibility(View.GONE); //(Ahmed)


                email.setVisibility(View.VISIBLE); //(Ahmed)
                send.setVisibility(View.VISIBLE); //(Ahmed)

            }else{
                gotoHomeActivity();
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
        if(dbh.getSessionStatus()==1){ //Check if the session if ON (Ahmed)
            gotoHomeActivity();
        }

    }

    public void createNewPassBtnClicked(View v) { //onClick method for create button (Ahmed)

        theNewPassword = NewPassword.getText().toString(); //(Ahmed)
        theConfirmNewPassword = ConfirmNewPassword.getText().toString(); //(Ahmed)

        if(!theNewPassword.isEmpty()){
            if(!theConfirmNewPassword.isEmpty()){
                if(theNewPassword.equals(theConfirmNewPassword)){
                    if(dbh.changePasswordTo(theNewPassword)){
                        toast = Toast.makeText(getApplicationContext(), "YOUR PASSWORD HAS BEEN CHANGED.", Toast.LENGTH_SHORT); //(Ahmed)
                        toast.show(); //(Ahmed)
                        gotoLoginActivity();
                    }else{
                        toast = Toast.makeText(getApplicationContext(), "AN ERROR HAS OCCURRED! PASSWORD COULD NOT BE CHANGED.", Toast.LENGTH_SHORT); //(Ahmed)
                        toast.show(); //(Ahmed)
                    }
                }else{
                    toast = Toast.makeText(getApplicationContext(), "PASSWORDS DO NOT MATCH!", Toast.LENGTH_SHORT); //(Ahmed)
                    toast.show(); //(Ahmed)
                }
            }else{
                toast = Toast.makeText(getApplicationContext(), "YOU MUST CONFIRM YOUR NEW PASSWORD!", Toast.LENGTH_SHORT); //(Ahmed)
                toast.show(); //(Ahmed)
            }
        }else{
            toast = Toast.makeText(getApplicationContext(), "YOU MUST ENTER A NEW PASSWORD!", Toast.LENGTH_SHORT); //(Ahmed)
            toast.show(); //(Ahmed)
        }

    }

    public void confirmBtnClicked(View v) { //onClick method for send button (Ahmed)

        theCode = email.getText().toString(); //(Ahmed)


        String realCode = dbh.getForgotCode();

        if(theCode.equals(realCode)){

            NewPassword.setVisibility(View.VISIBLE); //(Ahmed)
            ConfirmNewPassword.setVisibility(View.VISIBLE); //(Ahmed)

            create.setVisibility(View.VISIBLE); //(Ahmed)
            confirm.setVisibility(View.GONE); //(Ahmed)

            email.setVisibility(View.GONE); //(Ahmed)


        }else{
            toast = Toast.makeText(getApplicationContext(), "INVALID CODE!", Toast.LENGTH_SHORT); //(Ahmed)
            toast.show(); //(Ahmed)

        }
    }


    public void sendBtnClicked(View v) { //onClick method for send button (Ahmed)

        theEmail = email.getText().toString(); //(Ahmed)

        usr = dbh.getUser();  //(Ahmed)

        String realEmail = usr.getEmail();

        if(!theEmail.isEmpty()){
            if(theEmail.equals(realEmail)){
                if(isConnected()){
                if(dbh.setForgotCode()==true){

                    String c = dbh.getForgotCode(); //Ahmed

                    if(sendMessage(realEmail, c)){

                        confirm.setVisibility(View.VISIBLE); //(Ahmed)

                        send.setVisibility(View.GONE); //(Ahmed)
                        email.setHint("Enter Code");
                        email.setText("");


                        toast = Toast.makeText(getApplicationContext(), "A Confirmation code has been sent to your email.", Toast.LENGTH_SHORT); //(Ahmed)
                        toast.show(); //(Ahmed)

                    }else{
                        toast = Toast.makeText(getApplicationContext(), "Unable to send email!", Toast.LENGTH_SHORT); //(Ahmed)
                        toast.show(); //(Ahmed)
                    }

                }else{
                    toast = Toast.makeText(getApplicationContext(), "Unable to send code!", Toast.LENGTH_SHORT); //(Ahmed)
                    toast.show(); //(Ahmed)

                }

                }else{

                    toast = Toast.makeText(getApplicationContext(), "NOT CONNECTED TO THE INTERNET!", Toast.LENGTH_SHORT); //(Ahmed)
                    toast.show(); //(Ahmed)

                }

            }else{

                toast = Toast.makeText(getApplicationContext(), "INCORRECT Email!", Toast.LENGTH_SHORT); //(Ahmed)
                toast.show(); //(Ahmed)

            }

        }else{

            toast = Toast.makeText(getApplicationContext(), "Please enter your email address!", Toast.LENGTH_SHORT); //(Ahmed)
            toast.show(); //(Ahmed)
        }


    }

    public void gotoHomeActivity(){ //Public method that opens LoginActivity (Ahmed)
        Intent intent = new Intent(ForgotPassword.this, HomeActivity.class);
        startActivity(intent);
    }

    public void gotoRegisterActivity(){ //Public method that opens RegisterActivity (Ahmed)
        Intent intent = new Intent(ForgotPassword.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void gotoLoginActivity(){ //Public method that opens RegisterActivity (Ahmed)
        Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
        startActivity(intent);
    }


    //DO NOT FORGET TO ADD TO MANIFEST

    public boolean isConnected() { //Method that checks if device is connected to the internet (Ahmed: Taken from Android)
        ConnectivityManager ConnecMan = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo Info = ConnecMan.getActiveNetworkInfo();
        if (Info != null && Info.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public Boolean sendMessage(final String email, final String code){ //Public method that sends code to email (Ahmed)

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("ktripsnoreply@gmail.com", "KTripsnoreply123");
                    sender.sendMail("KTrips Code", "Your code is " + code, email, email);
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();

        return true;

    }





}
