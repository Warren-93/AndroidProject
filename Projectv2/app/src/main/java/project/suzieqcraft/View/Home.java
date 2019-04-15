package project.suzieqcraft.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import project.suzieqcraft.R;


public class Home extends AppCompatActivity {

    Button  menuBtn, regBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

        regBtn = findViewById( R.id.registerBtn );
        loginBtn = findViewById(R.id.loginBtn);
        menuBtn = findViewById( R.id.menuBtn );

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

        menuBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Home.this, AppMenu.class));
            }
        });
    }
}