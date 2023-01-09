package com.example.gd_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Degilim extends AppCompatActivity {

    Button gonder;
    Spinner sp;
    EditText et1;
    TextView txt , txtmail;
    RadioButton saglikli,yarali,agiryarali;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degilim);

        gonder = findViewById(R.id.button10);

        sp=findViewById(R.id.spinner);
        et1=findViewById(R.id.editTextTextPersonName3);
        saglikli=findViewById(R.id.radioButton3);
        yarali=findViewById(R.id.radioButton4);
        agiryarali=findViewById(R.id.radioButton5);
        txt=findViewById(R.id.textView11);
        txtmail=findViewById(R.id.textView20);
        Intent a = getIntent();
        String gelenenlem =a.getStringExtra("enlem");
        String gelenboylam = a.getStringExtra("boylam");
        String gelenmail = a.getStringExtra("email");

        txtmail.setText(gelenmail);


        gonder.setOnClickListener(new View.OnClickListener() {

            final String meil = txtmail.getText().toString();
            @Override
            public void onClick(View view) {

                txtmail.setText(gelenmail);

                if (saglikli.isChecked()) {
                    txt.setText("Normal");
                } else if (yarali.isChecked()) {
                    txt.setText("Hafif Yaralı");
                } else if (agiryarali.isChecked()) {
                    txt.setText("Ağır Yaralı");
                } else {
                    txt.setText("Belirsiz ");
                }

                if (et1.getText().toString().isEmpty()) {
                    Toast.makeText(Degilim.this, "Lütfen Bir Mesaj Giriniz....", Toast.LENGTH_SHORT).show();
                } else {


                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();
                    String key =myRef.push().getKey();
                    DatabaseReference YenimyRef = database.getReference("Güvende Değilim");
                    if (sp.getSelectedItemId() != 0) {

                        YenimyRef.child("İhbar İD"+key).child("Kullanıcı").setValue("Kullanıcı..: "+gelenmail);
                        YenimyRef.child("İhbar İD"+key).child("Durum").setValue("Durum......: "+"Güvende Değil");
                        YenimyRef.child("İhbar İD"+key).child("Afet Durumu").setValue("Afet Türü..: "+sp.getSelectedItem().toString());
                        YenimyRef.child("İhbar İD"+key).child("Konum").setValue("Konum......: "+gelenenlem+"/"+gelenboylam);
                        YenimyRef.child("İhbar İD"+key).child("Sağlık Durum").setValue("Sağlık Durumu..: "+txt.getText().toString());
                        YenimyRef.child("İhbar İD"+key).child("Kullanıcı Mesajı").setValue("Kullanıcı Mesajı..: "+et1.getText().toString());

                        Toast.makeText(Degilim.this, "Durumunuz ve Mesajınız Kontrol Merkezine e Gerekli Birimlere İletilmiştir. En kısa sürede birimler yardımınıza ulaşacaktır ..", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Degilim.this, "AFET TÜRÜ SEÇİNİZ !!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }




}