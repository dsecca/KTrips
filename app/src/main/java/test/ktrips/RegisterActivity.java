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

    protected EditText emailRegister;
    protected EditText passwordRegister;
    protected EditText confirmPasswordRegister;
    protected Button btn_create_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //1. Verify credentials (if have time in spritn 1 do this else in sprint 2)
        //2. create an User object
        //3. put it in the database
        //4. Start session
        emailRegister = (EditText) findViewById(R.id.emailRegister);
        passwordRegister = (EditText) findViewById(R.id.passwordRegister);
        confirmPasswordRegister = (EditText) findViewById(R.id.confirmPasswordRegister);
        btn_create_account = (Button) findViewById(R.id.btn_createAccount);
        btn_create_account.setOnClickListener(onClickCreateAccount);
    }

    private Button.OnClickListener onClickCreateAccount = new Button.OnClickListener(){
        //Whenever create account button is clicked, the method below is called
        String email, password, passwordConfirmation;

        //email = emailRegister.getText().toString();

        public void onClick(View v){
            //Here we wil have the method for creating the account in the user database and THEN we go to Home Activity
            Intent changeToActivityRegister = new Intent(RegisterActivity.this, HomeActivity.class);
            RegisterActivity.this.startActivity(changeToActivityRegister);
        }
    };

}
