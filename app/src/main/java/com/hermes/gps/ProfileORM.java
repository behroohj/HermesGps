package com.hermes.gps;

import com.orm.SugarRecord;
import java.util.Date;

/**
 * Created by imanbahmani on 9/24/16 AD.
 */
public class ProfileORM extends SugarRecord{

    private int    idapp;
    private String car_name;
    private String plate;
    private String owner;
    private String device_phone_number;
    private String driver_name;
    private String driver_mobile_phone;
    private String device_type;
    private String security;
    private String device_version;
    private String setup_time;
    private String g_end_time;
    private String note;
    private String serial;
    private String date;
    private String time;

    public ProfileORM() {
    }
    public ProfileORM(int idapp,String car_name, String plate, String owner, String device_phone_number, String driver_name, String driver_mobile_phone, String device_type, String security, String device_version, String setup_time,String g_end_time,String note,String serial,String date,String time) {
        this.idapp                   = idapp;
        this.car_name                = car_name;
        this.plate                   = plate;
        this.owner                   = owner;
        this.device_phone_number     = device_phone_number;
        this.driver_name             = driver_name;
        this.driver_mobile_phone     = driver_mobile_phone;
        this.device_type             = device_type;
        this.security                = security;
        this.device_version          = device_version;
        this.setup_time              = setup_time;
        this.g_end_time              = g_end_time;
        this.note                    = note;
        this.serial                  = serial;
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
    public String getCar_name() {
        return car_name;
    }
    public void   setCar_name(String car_name) {
        this.car_name = car_name;
    }
    /////////////////////
    public String getPlate() {
        return plate;
    }
    public void   setPlate(String plate) {
        this.plate = plate;
    }
    /////////////////////
    public String getOwner() {
        return owner;
    }
    public void   setOwner(String owner) {
        this.owner = owner;
    }
    ////////////////////
    public String getDevice_phone_number() {
        return device_phone_number;
    }
    public void   setDevice_phone_number(String device_phone_number) {
        this.device_phone_number = device_phone_number;
    }
    ////////////////////
    public String getDriver_name() {
        return driver_name;
    }
    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }
    /////////////////////
    public String getDriver_mobile_phone() {
        return driver_mobile_phone;
    }
    public void setDriver_mobile_phone(String driver_mobile_phone) {
        this.driver_mobile_phone = driver_mobile_phone;
    }
    /////////////////////
    public String getDevice_type() {
        return device_type;
    }
    public void setDevice_type(String device_type) {
        this.device_type = device_type;}
    /////////////////////
    public String getSecurity() {
        return security;
    }
    public void setSecurity(String security) {
        this.security = security;
    }
    /////////////////////
    public String getSetup_time() {
        return setup_time;
    }
    public void setSetup_time(String setup_time) {
        this.setup_time = setup_time;
    }
    /////////////////////
    public String getDevice_version() {
        return device_version;
    }
    public void setDevice_version(String device_version) {
        this.device_version = device_version;
    }
    /////////////////////
    public String getG_end_time() {
        return g_end_time;
    }
    public void setG_end_time(String g_end_time) {
        this.g_end_time = g_end_time;
    }
    /////////////////////
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    /////////////////////
    public String getSerial() {
        return serial;
    }
    public void setSerial(String serial) {
        this.serial = serial;
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


