package com.example.gd_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Yonetci_ekran_guvende_olamaynlar extends AppCompatActivity {


Button geri;


    private final  DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private final List<MyItems> myItemsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonetci_ekran_guvende_olamaynlar);


        final RecyclerView rev =findViewById(R.id.recyclerView);
geri=findViewById(R.id.button17);

        rev.setHasFixedSize(true);
        rev.setLayoutManager(new LinearLayoutManager(Yonetci_ekran_guvende_olamaynlar.this));
geri.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent a = new Intent(Yonetci_ekran_guvende_olamaynlar.this,Yonetici_Ekran.class);
        startActivity(a);
    }
});
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                myItemsList.clear();;
                for (DataSnapshot user :snapshot.child("Güvende Değilim").getChildren())
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



                rev.setAdapter(new RecyclerViewAdapter(myItemsList,Yonetci_ekran_guvende_olamaynlar.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}

