package project.suzieqcraft.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.suzieqcraft.Controller.Connection;
import project.suzieqcraft.R;


public class Login extends AppCompatActivity {

    EditText edtLogEmail, logPassword;
    Button logBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        edtLogEmail = findViewById(R.id.edtLogEmail);
        logPassword = findViewById(R.id.logPassword);
        logBtn = findViewById(R.id.logBtn);

        logBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtLogEmail.getText().toString().trim();
                String password = logPassword.getText().toString().trim();
                String type = "login";


                if (TextUtils.isEmpty( email )) {
                    Toast.makeText( getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT ).show();
                    return;
                }

                if (TextUtils.isEmpty( password )) {
                    Toast.makeText( getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT ).show();
                    return;
                }

                if (password.length() < 8) {
                    Toast.makeText( getApplicationContext(), "Password too short, Password must be at least 8 characters long", Toast.LENGTH_SHORT ).show();
                    return;
                }
                Connection conn = new Connection(Login.this);
                conn.execute(type, email, password);

            }
        });
    }
}