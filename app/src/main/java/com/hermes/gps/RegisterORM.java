package com.hermes.gps;

import com.orm.SugarRecord;

/**
 * Created by imanbahmani on 9/24/16 AD.
 */
public class RegisterORM extends SugarRecord{
    private int    idapp;
    private String sex;
    private String company;
    private String national_code;
    private String mobile;
    private String tel;
    private String address;
    private String reserv;
    private String access_end_time;
    private String car_serial;
    private String note;
    private String date;
    private String time;

    public RegisterORM() {
    }
    public RegisterORM(int idapp, String sex, String company, String national_code, String mobile, String tel, String address, String reserv, String access_end_time, String car_serial, String note, String date, String time) {
        this.idapp                   = idapp;
        this.sex                     = sex;
        this.company                 = company;
        this.national_code           = national_code;
        this.mobile                  = mobile;
        this.tel                     = tel;
        this.address                 = address;
        this.reserv                  = reserv;
        this.access_end_time         = access_end_time;
        this.car_serial              = car_serial;
        this.note                    = note;
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
    public String getsex() {
        return sex;
    }
    public void   setsex(String sex) {
        this.sex = sex;
    }
    /////////////////////
    public String getcompany() {
        return company;
    }
    public void   setcompany(String company) {
        this.company = company;
    }
    /////////////////////
    public String getnational_code() {
        return national_code;
    }
    public void   setnational_code(String national_code) {
        this.national_code = national_code;
    }
    ////////////////////
    public String getmobile() {
        return mobile;
    }
    public void   setmobile(String mobile) {
        this.mobile = mobile;
    }
    ////////////////////
    public String gettel() {
        return tel;
    }
    public void settel(String tel) {
        this.tel = tel;
    }
    /////////////////////
    public String getaddress() {
        return address;
    }
    public void setaddress(String address) {
        this.address = address;
    }
    /////////////////////
    public String getreserv() {
        return reserv;
    }
    public void setreserv(String reserv) {
        this.reserv = reserv;}
    /////////////////////
    public String getaccess_end_time() {
        return access_end_time;
    }
    public void setaccess_end_time(String access_end_time) {
        this.access_end_time = access_end_time;
    }
    /////////////////////
    public String getcar_serial() {
        return car_serial;
    }
    public void setcar_serial(String car_serial) {
        this.car_serial = car_serial;
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
    /////////////////////
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
}


