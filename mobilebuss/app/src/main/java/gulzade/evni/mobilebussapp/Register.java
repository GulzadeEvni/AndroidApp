package gulzade.evni.mobilebussapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {
    FirebaseAuth auth;
    EditText firstName,lastName,email,password;
    ImageView imageView1;
    Button registerbtn;
    TextView RegisterLogintext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerbtn = findViewById(R.id.registerbtn);
        RegisterLogintext = findViewById(R.id.RegisterLogintext);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first = firstName.getText().toString().trim();
                String last = lastName.getText().toString().trim();
                String user = email.getText().toString().trim();
                String pass = password.getText().toString().trim();


                if(user.isEmpty()){
                    email.setError("Bu Alan Boş Geçilemez");
                }
                if (pass.isEmpty()){
                    password.setError("Bu Alan Boş Geçilemez");
                }else{
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Register.this,"Kayıt Başarılı",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, Login.class));
                            }else{
                                Toast.makeText(Register.this,"Kayıt Oluşturulamadı" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        RegisterLogintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
    }
}