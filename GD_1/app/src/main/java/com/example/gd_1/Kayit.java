package com.example.gd_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Kayit extends AppCompatActivity {

    Button kayit,geri;
    EditText maail,sifre;
    FirebaseAuth yetki;
    String emaildata;
    String sıfredata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        kayit=findViewById(R.id.button);
        geri=findViewById(R.id.button4);
        maail=findViewById(R.id.dsfds);
        sifre=findViewById(R.id.asdasd);


        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                yetki=FirebaseAuth.getInstance();
                emaildata=maail.getText().toString();
                sıfredata=sifre.getText().toString();

                if (emaildata.isEmpty())
                {
                    Toast.makeText(Kayit.this,"EMAİL girişi boş bırakılmaz",Toast.LENGTH_SHORT).show();

                }
                else if (sıfredata.isEmpty())
                {
                    Toast.makeText(Kayit.this,"Şifre girişi boş bırakılmaz",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    kaydol();
                }
            }
        });
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x=new Intent(Kayit.this,Giris.class);
                startActivity(x);
            }
        });
    }
    private  void kaydol()
    {
        yetki.createUserWithEmailAndPassword(emaildata,sıfredata).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> kaydolma) {

                if (kaydolma.isSuccessful())
                {
                    Toast.makeText(Kayit.this,"KAYIT BAŞARILI",Toast.LENGTH_SHORT).show();
                    Intent x=new Intent(Kayit.this,Giris.class);
                    startActivity(x);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception hata) {
                Toast.makeText(Kayit.this,"Şifre 6 haneden kısa olamaz ",Toast.LENGTH_SHORT).show();
            }
        });

    }

}