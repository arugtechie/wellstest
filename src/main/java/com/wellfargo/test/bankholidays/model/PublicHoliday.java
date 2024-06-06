package com.wellfargo.test.bankholidays.model;

import lombok.Data;

@Data
public class PublicHoliday {
    public String date;
    public String localName;
    public String name;
    public String countryCode ;
    public Boolean fixed;
    public Boolean global;
    public String[] counties;
    public String[] types;
}