package gulzade.evni.mobilebussapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Addcard extends AppCompatActivity {
    EditText kart;
    Button ekle;
    DatabaseReference mDatabase;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);
        //mDatabase = FirebaseDatabase.getInstance().getReference().child("Kartlarımm");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        kart = findViewById(R.id.kart);
        ekle = findViewById(R.id.ekle);



        /*ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        gosterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Addcard.this,Showcard.class));
                finish();
            }
        });*/



       ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String card = kart.getText().toString();
                if (!TextUtils.isEmpty(card)){
                    add_card(card);
                }
                Toast.makeText(getApplicationContext(),"Eklendi",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Addcard.this,Showcard.class));
            }
        });
    }
    private void add_card(final String card){
        mDatabase.child("myCards").setValue(card);
    }

    /*private void insertData(){
        String card = kart.getText().toString();
        Cards cardss = new Cards(card);
        mDatabase.push().setValue(cardss);
    }*/



}