package threadpool;

import crawler.Spider;

/**
 * Created by yuan.wei on 5/16/17.
 */
public class ChildSpider implements Runnable {
    private Spider spider;
    private String url;
    private String contentParserName;
    private String linkListParserName;
    private String multiThread;
    private int deep;

    public ChildSpider(String url, String contentParserName, String linkListParserName, String multiThread, Spider spider, int deep) {
        this.spider = spider;
        this.url = url;
        this.contentParserName = contentParserName;
        this.linkListParserName = linkListParserName;
        this.multiThread = multiThread;
        this.deep = deep;
    }

    @Override
    public void run() {
        spider.getData(url, contentParserName, linkListParserName, multiThread, deep);
    }
}
