package crawler;

import holidayInfoParser.HolidayLinkListParser;
import holidayInfoParser.HolidaySubLinkListParser;
import parser.ContentParser;

/**
 * Created by yuan.wei on 5/22/17.
 */
public class HolidayInfoSpider extends Spider{
    public HolidayInfoSpider() {
        // add parser into the spider
        // add the content parser
        contentParsers.put("ContentParser", new ContentParser());
        // add the links parser
        linkListParsers.put("HolidayLinkListParser", new HolidayLinkListParser());
        linkListParsers.put("HolidaySubLinkListParser", new HolidaySubLinkListParser());
        // add the filter to reject the site with anti spider
    }

    public static void main(String[] args) {
        new Thread(new ClearOldNews()).start();
        while(true) {
            new HolidayInfoSpider().getData("https://www.timeanddate.com/holidays/us/2016", "", "HolidayLinkListParser", "false", 0);
            try {
                Thread.sleep(1000 * 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void delay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
