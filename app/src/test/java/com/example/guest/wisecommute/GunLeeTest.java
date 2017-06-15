package com.example.guest.wisecommute;

import com.example.guest.wisecommute.services.TwitterService;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Guest on 6/14/17.
 */

public class GunLeeTest {

    @Test
    public void timeFilter() {
        String output;
        String response = "Tue Jun 13 15:01:13 +0000 2017";

        String hour = response.substring(11, 13);
        String min = response.substring(14, 16);
        assertEquals("15", hour);
        assertEquals("01", min);
        assertEquals("Jun 13", response.substring(4,10));

        final String time = "14:01";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            Date dateObj = sdf.parse(time);
            System.out.println(new SimpleDateFormat("h:mm a").format(dateObj));
        } catch (final ParseException e) {
            e.printStackTrace();
        }




    }

    @Test
    public void testTimeFilter(){
        TwitterService t = new TwitterService();
        assertEquals("11", t.timeFilter("Tue Jun 13 15:01:13 +0000 2017"));
    }
}
