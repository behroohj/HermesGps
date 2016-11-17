package com.hermes.gps;

import com.orm.SugarRecord;

/**
 * Created by imanbahmani on 9/24/16 AD.
 */
public class LastLocationORM extends SugarRecord{

    private int    idapp;
    private String A;
    private String B;
    private String C;
    private String D;
    private int    E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String K;
    private String day;
    private String date;
    private String time;

    public LastLocationORM() {
    }
    public LastLocationORM(int idapp, String A, String B, String C, String D, int E, String F, String G, String H, String I, String K, String day, String date, String time) {
        this.idapp                   = idapp;
        this.A                       = A;
        this.B                       = B;
        this.C                       = C;
        this.D                       = D;
        this.E                       = E;
        this.F                       = F;
        this.G                       = G;
        this.H                       = H;
        this.I                       = I;
        this.K                       = K;
        this.day                     = day;
        this.date                    = date;
        this.time                    = time;
    }
    //////////////////////
    public int   getIdapp() {
        return idapp;
    }
    public void  setIdapp(int idapp) {
        this.idapp = idapp;
    }
    //////////////////////
    public String getA() {
        return A;
    }
    public void   setA(String A) {
        this.A = A;
    }
    /////////////////////
    public String getB() {
        return B;
    }
    public void   setB(String B) {
        this.B = B;
    }
    /////////////////////
    public String getC() {
        return C;
    }
    public void   setC(String C) {
        this.C = C;
    }
    ////////////////////
    public String getD() {
        return D;
    }
    public void   setD(String D) {
        this.D = D;
    }
    ////////////////////
    public int getE() {
        return E;
    }
    public void setE(int E) {
        this.E = E;
    }
    /////////////////////
    public String getF() {
        return F;
    }
    public void setF(String F) {
        this.F = F;
    }
    /////////////////////
    public String getG() {
        return G;
    }
    public void setG(String G) {
        this.G = G;}
    /////////////////////
    public String getH() {
        return H;
    }
    public void setH(String H) {
        this.H = H;
    }
    /////////////////////
    public String getK() {
        return K;
    }
    public void setK(String K) {
        this.K = K;
    }
    /////////////////////
    public String getI() {
        return I;
    }
    public void setI(String I) {
        this.I = I;
    }
    /////////////////////
    public String getday() {
        return day;
    }
    public void setday(String day) {
        this.day = day;
    }
    /////////////////////
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    /////////////////////
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}


