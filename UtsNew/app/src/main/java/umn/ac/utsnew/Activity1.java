package umn.ac.utsnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity1 extends AppCompatActivity {
    private Button button;
    EditText username, password;

    String correct_username = "uasmobile";
    String correct_password = "uasmobilegenap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validasi

                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty((password.getText().toString()))) {
                    Toast.makeText(Activity1.this, "Masukkan Username dan Password", Toast.LENGTH_LONG).show();
                } else if (username.getText().toString().equals(correct_username)) {
                    if (password.getText().toString().equals(correct_password)) {
                        Activity2();
                    } else {
                        Toast.makeText(Activity1.this, "Username Salah", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Activity1.this, "Password Salah", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void Activity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
}