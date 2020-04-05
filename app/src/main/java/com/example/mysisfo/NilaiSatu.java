package com.example.mysisfo;

public class NilaiSatu {
    String kodemk="", namamk="", sks="", huruf="", akhir="";
    public NilaiSatu(String kodemk, String namamk, String sks, String huruf, String akhir){
        this.kodemk = kodemk;
        this.namamk = namamk;
        this.sks = sks;
        this.huruf = huruf;
        this.akhir = akhir;
    }

    public String getKodemk() {
        return kodemk;
    }

    public String getNamamk() {
        return namamk;
    }

    public String getSks() {
        return sks;
    }

    public String getHuruf() {
        return huruf;
    }
    public String getAkhir() {
        return akhir;
    }
}
