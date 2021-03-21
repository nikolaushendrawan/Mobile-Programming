package umn.ac.utsnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button loginbtn,profilebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Login");

        profilebtn = (Button) findViewById(R.id.profilebtn);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile();
            }
        });
        loginbtn = (Button) findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity1();

            }
        });
    }
    public void Activity1() {
        Intent intent = new Intent(this, Activity1.class);
        startActivity(intent);
    }
    public void Profile(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}