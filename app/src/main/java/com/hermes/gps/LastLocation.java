package com.hermes.gps;

/**
 * Created by imanbahmani on 11/2/2016 AD.
 */

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by imanbahmani on 9/24/16 AD.
 */
public class LastLocation extends SugarRecord{

    private String title;
    private String message;
    private double latitude;
    private double longitude;
    private Date   timestamp;
    private String jalaliTime;
    private String contact_name;
    private String contact_number;
    private String status;


    public LastLocation() {
    }
    public LastLocation(String title,String message,double latitude,double longitude,Date timestamp,String jalaliTime,String contact_name,String contact_number,String status) {
        this.title                = title;
        this.message              = message;
        this.latitude             = latitude;
        this.longitude            = longitude;
        this.timestamp            = timestamp;
        this.jalaliTime           = jalaliTime;
        this.contact_name         = contact_name;
        this.contact_number       = contact_number;
        this.status               = status;
    }
    //////////////////////
    public String getTitle() {
        return title;
    }
    public void   setTitle(String title) {
        this.title = title;
    }
    /////////////////////
    public String getMessage() {
        return message;
    }
    public void   setMessage(String message) {
        this.message = message;
    }
    /////////////////////
    public double getLatitude() {
        return latitude;
    }
    public void   setLatitude(double latitude) {
        this.latitude = latitude;
    }
    ////////////////////
    public double getLongitude() {
        return longitude;
    }
    public void   setLongitude(double longitude) {
        this.longitude = longitude;
    }
    ////////////////////
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    /////////////////////
    public String getJalaliTime() {
        return jalaliTime;
    }
    public void setJalaliTime(String jalaliTime) {
        this.jalaliTime = jalaliTime;
    }
    /////////////////////
    public String getContact_name() {
        return contact_name;
    }
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;}
    /////////////////////
    public String getContact_number() {
        return contact_number;
    }
    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
    /////////////////////
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}


