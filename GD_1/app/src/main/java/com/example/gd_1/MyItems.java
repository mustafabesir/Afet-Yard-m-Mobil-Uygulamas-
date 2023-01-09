package com.example.gd_1;

public class MyItems {
    private  final String kullanıcı,durum,konum,afet,mesaj,saglik;

    public MyItems(String kullanıcı, String durum, String konum, String afet, String mesaj,String saglik) {
        this.kullanıcı = kullanıcı;
        this.durum = durum;
        this.konum = konum;
        this.afet = afet;
        this.mesaj = mesaj;
        this.saglik = saglik;
    }

    public String getKullanıcı() {
        return kullanıcı;
    }

    public String getDurum() {
        return durum;
    }

    public String getKonum() {
        return konum;
    }

    public String getAfet() {
        return afet;
    }

    public String getMesaj() {
        return mesaj;
    }

    public String getSaglik() {
        return saglik;
    }
}