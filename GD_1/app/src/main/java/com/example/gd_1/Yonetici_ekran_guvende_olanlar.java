package com.example.gd_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Yonetici_ekran_guvende_olanlar extends AppCompatActivity {


Button geri;

    private final  DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private final List<MyItems> myItemsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonetici_ekran_guvende_olanlar);

geri = findViewById(R.id.button19);
       final RecyclerView rev =findViewById(R.id.recyclerView2);

        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Yonetici_ekran_guvende_olanlar.this,Yonetici_Ekran.class);
                startActivity(a);
            }
        });
        rev.setHasFixedSize(true);
        rev.setLayoutManager(new LinearLayoutManager(Yonetici_ekran_guvende_olanlar.this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                myItemsList.clear();;

                for (DataSnapshot user :snapshot.child("Güvendeyim").getChildren())
                {
                    if (user.hasChild("Kullanıcı")&&user.hasChild("Durum")&&user.hasChild("Konum")&&user.hasChild("Sağlık Durum")&&user.hasChild("Kullanıcı Mesajı")&&user.hasChild("Kullanıcı Mesajı")&&user.hasChild("Afet Durumu")) {
                        final String getKullanıcı = user.child("Kullanıcı").getValue(String.class);
                        final String getDurum = user.child("Durum").getValue(String.class);
                        final String getKonum = user.child("Konum").getValue(String.class);
                        final String getSaglikdurum = user.child("Sağlık Durum").getValue(String.class);
                        final String getMesaj = user.child("Kullanıcı Mesajı").getValue(String.class);
                        final String getAfet = user.child("Afet Durumu").getValue(String.class);

                        MyItems myItems = new MyItems(getKullanıcı, getDurum, getKonum, getAfet, getMesaj, getSaglikdurum);

                        myItemsList.add(myItems);
                    }
                }

                rev.setAdapter(new RecyclerViewAdapter(myItemsList,Yonetici_ekran_guvende_olanlar.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}