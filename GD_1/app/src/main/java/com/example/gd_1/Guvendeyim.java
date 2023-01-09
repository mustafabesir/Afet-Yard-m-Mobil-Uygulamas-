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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Guvendeyim extends AppCompatActivity {

    Button gonder;
    EditText yazi;
    Spinner sp;
    TextView txt;
    RadioButton saglikli,yarali,agiryarali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guvendeyim);


        gonder=(Button) findViewById(R.id.button5);
        yazi=findViewById(R.id.editTextTextPersonName);
        sp=findViewById(R.id.spinner2);
        saglikli=findViewById(R.id.radioButton3);
        yarali=findViewById(R.id.radioButton4);
        agiryarali=findViewById(R.id.radioButton5);
        txt=findViewById(R.id.textView23);


        Intent a = getIntent();
        String gelenenlem = a.getStringExtra("enlem");
        String gelenboylam = a.getStringExtra("boylam");
        String gelenmail = a.getStringExtra("email");





        gonder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (saglikli.isChecked()) {
                    txt.setText("Normal");
                } else if (yarali.isChecked()) {
                    txt.setText("Hafif Yaralı");
                } else if (agiryarali.isChecked()) {
                    txt.setText("Ağır Yaralı");
                } else {
                    txt.setText("Belirsiz ");
                }

                if (yazi.getText().toString().isEmpty()) {
                    Toast.makeText(Guvendeyim.this, "Lütfen Bir Mesaj Giriniz....", Toast.LENGTH_SHORT).show();
                } else {


                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Güvendeyim");
                    String key =myRef.push().getKey();
                    DatabaseReference YenimyRef = database.getReference("Güvendeyim");
                    if (sp.getSelectedItemId() != 0) {

                        YenimyRef.child("İhbar İD"+key).child("Kullanıcı").setValue("Kullanıcı..: "+gelenmail);
                        YenimyRef.child("İhbar İD"+key).child("Durum").setValue("Durum......: "+"Güvende");
                        YenimyRef.child("İhbar İD"+key).child("Afet Durumu").setValue("Afet Türü..: "+sp.getSelectedItem().toString());
                        YenimyRef.child("İhbar İD"+key).child("Konum").setValue("Konum......: "+gelenenlem+"/"+gelenboylam);
                        YenimyRef.child("İhbar İD"+key).child("Sağlık Durum").setValue("Sağlık Durumu..: "+txt.getText().toString());
                        YenimyRef.child("İhbar İD"+key).child("Kullanıcı Mesajı").setValue("Kullanıcı Mesajı..: "+yazi.getText().toString());

                        Toast.makeText(Guvendeyim.this, "Durumunuz ve Mesajınız Kontrol Merkezine e Gerekli Birimlere İletilmiştir. En kısa sürede birimler yardımınıza ulaşacaktır ..", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Guvendeyim.this, "AFET TÜRÜ SEÇİNİZ !!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }




}