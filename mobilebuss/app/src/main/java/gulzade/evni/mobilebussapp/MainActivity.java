package gulzade.evni.mobilebussapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;
    TextView mtext;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtext = findViewById(R.id.mtext);
        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Register.class));
                //finish();
            }
        });

        /*if (auth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),Qr.class));

            finish();
        }*/


    }
}