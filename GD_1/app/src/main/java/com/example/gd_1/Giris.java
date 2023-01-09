package com.example.gd_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Giris extends AppCompatActivity {
    Button btn1;
    EditText maail,sifre;
    TextView kayıtekran,yonetici,hakkında;
    FirebaseAuth yetki;
    String emaildata;
    String sıfredata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        btn1=findViewById(R.id.button2);
        maail=findViewById(R.id.gırısedıttext);
        sifre=findViewById(R.id.sıfreedıttext);
        kayıtekran=findViewById(R.id.textView3);
        yonetici=findViewById(R.id.textView14);
        hakkında=findViewById(R.id.textView);
        yetki=FirebaseAuth.getInstance();



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                emaildata=maail.getText().toString();
                sıfredata=sifre.getText().toString();

                if (emaildata.isEmpty())
                {
                    Toast.makeText(Giris.this,"EMAİL girişi boş bırakılmaz",Toast.LENGTH_SHORT).show();

                }
                else if (sıfredata.isEmpty())
                {
                    Toast.makeText(Giris.this,"Şifre girişi boş bırakılmaz",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    girisyap();
                }
            }
        });


        kayıtekran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x=new Intent(Giris.this,Kayit.class);
                startActivity(x);
            }
        });
          yonetici.setOnClickListener(new View.OnClickListener() {
             @Override
           public void onClick(View view) {
        Intent x=new Intent(Giris.this,Yonetici_Giris.class);
        startActivity(x);
            }
         });
        hakkında.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x=new Intent(Giris.this,Hakkinda.class);
                startActivity(x);
            }
        });

    }

    private  void girisyap() {

        yetki.signInWithEmailAndPassword(emaildata, sıfredata).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> girisgorev) {

                if (girisgorev.isSuccessful()) {
                    Intent x = new Intent(Giris.this, MainActivity.class);

                     x.putExtra("email",emaildata);
                    startActivity(x);


                } else {
                    Toast.makeText(Giris.this, "BİLGİLERİ KONTROL EDİN", Toast.LENGTH_SHORT).show();
                }

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception hata) {
                Toast.makeText(Giris.this, " " + hata, Toast.LENGTH_SHORT).show();
            }
        });
    }

}