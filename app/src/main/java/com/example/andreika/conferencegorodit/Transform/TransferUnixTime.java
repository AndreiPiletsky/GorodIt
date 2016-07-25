package com.example.andreika.conferencegorodit.Transform;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by andreika on 22.07.2016.
 */
public class TransferUnixTime {
    public static String convertTime(long time) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM / HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        String formattedDate = sdf.format(date);
        return formattedDate;

    }
}
