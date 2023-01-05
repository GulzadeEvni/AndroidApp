package gulzade.evni.mobilebussapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Anamenu extends AppCompatActivity implements View.OnClickListener
 {
    ImageView scanbtnn,logoutbtnn,addcardd,mycardd;
    TextView anatext,textView3,anatextcard,textView2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anamenu);

        anatext = findViewById(R.id.anatext);
        textView2 = findViewById(R.id.anatext2);
        textView3 = findViewById(R.id.anatext3);
        anatextcard = findViewById(R.id.anatext4);
        logoutbtnn = findViewById(R.id.logoutbtnn);
        addcardd = findViewById(R.id.addcardd);
        mycardd =findViewById(R.id.mycardd);
        scanbtnn = findViewById(R.id.scanbtnn);
        scanbtnn.setOnClickListener(this);



        addcardd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Anamenu.this,Addcard.class));
            }
        });

        mycardd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Anamenu.this,Showcard.class));
            }
        });

        //logout gelecek
        logoutbtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();

            }
        });



    }
    @Override
    public void onClick(View view) {
        scanCode();
    }
    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Taranıyor");
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Tarama Sonucu");

                builder.setPositiveButton("Tekrar Tarat", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        scanCode();
                    }
                }).setNegativeButton("Bitti", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                Toast.makeText(this, "Taranacak Alan Bulunamadı", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}