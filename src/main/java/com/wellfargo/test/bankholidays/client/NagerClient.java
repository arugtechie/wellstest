package com.wellfargo.test.bankholidays.client;

import com.wellfargo.test.bankholidays.exception.InvalidDateFormatException;
import com.wellfargo.test.bankholidays.model.PublicHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class NagerClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${nager.client.baseUrl}")
    String baseUrl;

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public boolean IsTodayPublicHoliday(String dateTimeString, String countryCode) throws InvalidDateFormatException
    {
        LocalDate localDate = LocalDate.from(DATE_FORMAT.parse(dateTimeString));
        String IsTodayPublicHolidayUrl = baseUrl + "/IsTodayPublicHoliday/" + countryCode;
        String response = restTemplate.getForObject(IsTodayPublicHolidayUrl, String.class);
        return false;

    }

    public boolean IsTodayInPublicHolidays(String dateTimeString, String countryCode) throws InvalidDateFormatException
    {

        String year = String.valueOf(LocalDate.parse(dateTimeString, DATE_FORMAT).getYear());
        Optional<PublicHoliday> optionalPublicHoliday = getListofPublicHolidays(year,countryCode).stream().filter(publicHoliday ->
        {
            return publicHoliday.date.equals(dateTimeString);
        }).findAny();

        return optionalPublicHoliday.isPresent();
    }

    public List<PublicHoliday> getListofPublicHolidays(String year, String countryCode)
    {
        List<PublicHoliday> publicHolidayList = new ArrayList<>();
        String getPublicHolidaysUrl = baseUrl + "/PublicHolidays/" + year + "/" + countryCode;
        ResponseEntity<PublicHoliday[]> response = restTemplate.getForEntity(getPublicHolidaysUrl, PublicHoliday[].class);

        if(Objects.nonNull(response.getBody())) {
            publicHolidayList = Arrays.asList(response.getBody());
        }
        return publicHolidayList;
    }


}


