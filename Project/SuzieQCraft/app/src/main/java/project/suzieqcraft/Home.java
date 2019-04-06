package project.suzieqcraft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Home extends AppCompatActivity {

    Button regBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

        regBtn = findViewById( R.id.registerBtn );
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Home.this, Login.class));
            }
        });

        regBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(Home.this, Register.class));
            }
        });
    }
}
