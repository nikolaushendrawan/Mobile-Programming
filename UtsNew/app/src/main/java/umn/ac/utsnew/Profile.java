package umn.ac.utsnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.setTitle("Profile");
        TextView textView=(TextView)findViewById(R.id.textView6);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

}