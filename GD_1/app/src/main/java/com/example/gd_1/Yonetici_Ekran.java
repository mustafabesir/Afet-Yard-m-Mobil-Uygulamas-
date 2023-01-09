package com.example.gd_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Yonetici_Ekran extends AppCompatActivity {


TextView txt,txtgeri;
Button guvende,degil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonetici_ekran);
          txt=findViewById(R.id.textView22);
          guvende = findViewById(R.id.button18);
          degil=findViewById(R.id.button16);
txtgeri=findViewById(R.id.textView26);
        Intent a = getIntent();
        String kullanıcıadı =a.getStringExtra("kullanici_adi");

        txt.setText("Hoş Geldin --- "+kullanıcıadı);

        txtgeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Yonetici_Ekran.this,Giris.class);
                startActivity(a);
            }
        });
guvende.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent a = new Intent(Yonetici_Ekran.this,Yonetici_ekran_guvende_olanlar.class);
        startActivity(a);
    }
});
degil.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent a = new Intent(Yonetici_Ekran.this,Yonetci_ekran_guvende_olamaynlar.class);
        startActivity(a);
    }
});

    }
}