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


public class Register extends AppCompatActivity {

    EditText edtRegFName, edtRegSName, edtRegEmail, regPassword;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        edtRegFName = findViewById( R.id.edtRegFName );
        edtRegSName = findViewById( R.id.edtRegSName );
        edtRegEmail = findViewById( R.id.edtRegEmail );
        regPassword = findViewById( R.id.regPassword );
        registerBtn = findViewById( R.id.registerBtn );

        registerBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = edtRegFName.getText().toString().trim();
                String surname = edtRegSName.getText().toString().trim();
                String email = edtRegEmail.getText().toString().trim();
                String password = regPassword.getText().toString().trim();
                String type = "register";

                if (TextUtils.isEmpty( firstname )) {
                    Toast.makeText( getApplicationContext(), "Enter First Name", Toast.LENGTH_SHORT ).show();
                    return;
                }

                if (TextUtils.isEmpty( surname )) {
                    Toast.makeText( getApplicationContext(), "Enter Surname", Toast.LENGTH_SHORT ).show();
                    return;
                }

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
                Connection conn = new Connection( Register.this);
                conn.execute(type, firstname, surname, email, password);
            }
        });
    }
}
