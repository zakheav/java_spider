package crawler;

import db.DB;
import googleNewsParser.ArticleLinkListParser;
import googleNewsParser.BoardLinkListParser;
import parser.ContentParser;


/**
 * Created by yuan.wei on 5/16/17.
 */

class ClearOldNews implements Runnable {
    @Override
    public void run() {
        while(true) {
            System.out.println("delete the old news");
            try {
                Thread.sleep(1000 * 3600 * 24);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            DB.deleteOldNews();
        }
    }
}

public class GoogleNewsSpider extends Spider {

    public GoogleNewsSpider() {
        // add parser into the spider
        // add the content parser
        contentParsers.put("ContentParser", new ContentParser());
        // add the links parser
        linkListParsers.put("BoardLinkListParser", new BoardLinkListParser());
        linkListParsers.put("ArticleLinkListParser", new ArticleLinkListParser());
        // add the filter to reject the site with anti spider
        antiSpiderSite.add("www.nytimes.com");
    }

    public static void main(String[] args) {
        new Thread(new ClearOldNews()).start();
        while(true) {
            new GoogleNewsSpider().getData("https://news.google.com/", "", "BoardLinkListParser", "false", 0);
            try {
                Thread.sleep(1000 * 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
