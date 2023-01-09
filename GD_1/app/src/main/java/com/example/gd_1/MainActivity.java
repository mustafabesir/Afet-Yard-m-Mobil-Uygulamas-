package com.example.gd_1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.Provider;

public class MainActivity extends AppCompatActivity implements LocationListener {

    Button guvndeyım, dglmm, flas, sıren, arama;
    MediaPlayer ses;
    CameraManager cam;
    String cameraId;
    Boolean state = false;
    TextView enlem, boylam , mail ;
    LocationManager locationManager;
    String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guvndeyım = findViewById(R.id.button12);
        dglmm = findViewById(R.id.button11);
        flas = findViewById(R.id.button3);
        sıren = findViewById(R.id.button6);
        arama = findViewById(R.id.button7);
        enlem = findViewById(R.id.textView15);
        boylam = findViewById(R.id.textView16);
        mail = findViewById(R.id.textView17);
        Intent a = getIntent();
        String emailbilgi=a.getStringExtra("email");
        mail.setText(emailbilgi.toString());
        locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) { //on location change
        } else {
            enlem.setText("Bulunamadı");
            boylam.setText("Bulunamadı");
        }
        ses = MediaPlayer.create(MainActivity.this, R.raw.ses);

        sıren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ses.isPlaying()) {
                    ses.pause();
                } else {
                    ses.start();
                }

            }
        });
        guvndeyım.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, Guvendeyim.class);
                a.putExtra("enlem",enlem.getText());
                a.putExtra("boylam",boylam.getText());
                a.putExtra("email",mail.getText());
                startActivity(a);
            }
        });
        dglmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, Degilim.class);
                a.putExtra("enlem",enlem.getText());
                a.putExtra("boylam",boylam.getText());
                a.putExtra("email",mail.getText());
                startActivity(a);
            }
        });
        arama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Intent.ACTION_DIAL);
                a.setData(Uri.parse("tel:" + 112));
                startActivity(a);
            }
        });

        flas.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                if (state == false) {
                    try {
                        cam = (CameraManager) getSystemService(CAMERA_SERVICE);
                        cameraId = cam.getCameraIdList()[0];
                        cam.setTorchMode(cameraId, !state);

                        state = true;

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        cam = (CameraManager) getSystemService(CAMERA_SERVICE);
                        cameraId = cam.getCameraIdList()[0];
                        cam.setTorchMode(cameraId, !state);

                        state = false;
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

        double ennlem = location.getLatitude();
        double boyylam = location.getLongitude();
        enlem.setText(String.valueOf(ennlem));
        boylam.setText(String.valueOf(boyylam));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }


    @Override
    protected void onPause() {
        super.onPause();
locationManager.removeUpdates(this);

    }
}


