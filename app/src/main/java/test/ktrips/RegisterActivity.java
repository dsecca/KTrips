package test.ktrips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    protected EditText email_register;
    protected EditText password_register;
    protected EditText verify_password_register;
    protected Button btn_create_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //1. Verify credentials (if have time in spritn 1 do this else in sprint 2)
        //2. create an User object
        //3. put it in the database
        //4. Start session
    }
}
