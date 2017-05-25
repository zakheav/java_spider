package crawler;

import org.jsoup.nodes.Document;
import parser.ContentParser;
import wikiHolidayInfoParser.WikiLinkListParser;

/**
 * Created by yuan.wei on 5/25/17.
 */
public class WikiHolidayInfoSpider extends Spider {
    public WikiHolidayInfoSpider() {
        // add parser into the spider
        // add the content parser
        contentParsers.put("ContentParser", new ContentParser());
        // add the links parser
        linkListParsers.put("WikiLinkListParser", new WikiLinkListParser());
        // add the filter to reject the site with anti spider
    }

    public static void main(String[] args) {
        new Thread(new ClearOldNews()).start();
        while (true) {
            new WikiHolidayInfoSpider().getData("https://en.wikipedia.org/wiki/Public_holidays_in_the_United_States", "", "WikiLinkListParser", "true", 0);
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
