package gulzade.evni.mobilebussapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText loginEmail, LoginPassword;
    private Button Loginbtn;
    ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.loginEmail);
        LoginPassword = findViewById(R.id.LoginPassword);
        Loginbtn = findViewById(R.id.Loginbtn);


        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Anamenu.class));
            finish();
        }

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString().trim();
                String pass = LoginPassword.getText().toString().trim();



                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if (!pass.isEmpty()){
                        auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Login.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this,Anamenu.class));
                                finish();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Giriş Yapılamadı",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }else{
                        LoginPassword.setError("Bu Alan Boş Geçilemez");
                    }
                }else if (email.isEmpty()){
                    loginEmail.setError("Bu Alan Boş Geçilemez");
                }else{
                    loginEmail.setError("Lütfen Geçerli Bir E-mail Girin");
                }
            }
        });


    }

}