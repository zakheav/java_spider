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
        // 构建爬虫所用到的解析器
        contentParsers.put("ContentParser", new ContentParser());
        linkListParsers.put("BoardLinkListParser", new BoardLinkListParser());
        linkListParsers.put("ArticleLinkListParser", new ArticleLinkListParser());
        // 添加有反爬虫机制的网站
        antiSpiderSite.add("www.nytimes.com");
    }

    public static void main(String[] args) {
        new Thread(new ClearOldNews()).start();
        while(true) {
            new GoogleNewsSpider().getData("https://news.google.com/", "", "BoardLinkListParser", "false");
            try {
                Thread.sleep(1000 * 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
