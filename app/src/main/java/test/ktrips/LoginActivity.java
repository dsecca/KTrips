package test.ktrips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    }
}
