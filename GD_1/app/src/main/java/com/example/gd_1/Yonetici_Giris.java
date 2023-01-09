package com.example.gd_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Yonetici_Giris extends AppCompatActivity {


    Button giris,geri;
    EditText kullaniciadi,sifre;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonetici_giris);

        txt=findViewById(R.id.textView12);
        giris=findViewById(R.id.button9);
        geri=findViewById(R.id.button14);
        kullaniciadi=findViewById(R.id.kullanıcıtxt);
        sifre=findViewById(R.id.sıfretxt);




        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Yonetici_Giris.this,"KULLANICI ADI VE ŞİFREYİ YÖNETİCİDEN TALEP EDİNİZ ", Toast.LENGTH_LONG).show();
            }
        });
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Yonetici_Giris.this,Giris.class);
                startActivity(a);
            }
        });
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (kullaniciadi.getText().toString().isEmpty())
                {
                    Toast.makeText(Yonetici_Giris.this,"Kullanıcı Adı boş bırakılmaz",Toast.LENGTH_SHORT).show();

                }
                else if (sifre.getText().toString().isEmpty())
                {
                    Toast.makeText(Yonetici_Giris.this,"Şifre girişi boş bırakılmaz",Toast.LENGTH_SHORT).show();

                }
                else if ((kullaniciadi.getText().toString().equals("admin") ))
                {
                    if ((sifre.getText().toString().equals("123") )) {
                        Intent a = new Intent(Yonetici_Giris.this, Yonetici_Ekran.class);
                        a.putExtra("kullanici_adi",kullaniciadi.getText().toString());
                        startActivity(a);
                        Toast.makeText(Yonetici_Giris.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                    }
                    else{Toast.makeText(Yonetici_Giris.this, "Yanlış Şifre", Toast.LENGTH_SHORT).show();}
                }
                else
                {
                    Toast.makeText(Yonetici_Giris.this, "Kullanıcı Adı Hatalı", Toast.LENGTH_SHORT).show();
                }






            }
        });
    }

}