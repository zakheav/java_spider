package crawler;

import db.DB;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import parser.ContentParserInterface;
import parser.LinkListParserInterface;
import threadpool.ChildSpider;
import threadpool.ThreadPool;

import java.io.IOException;
import java.util.*;

/**
 * Created by yuan.wei on 5/16/17.
 */
public class Spider {
    protected int deepLimit = 2;
    protected Set<String> antiSpiderSite = new HashSet<>();
    protected Map<String, ContentParserInterface> contentParsers = new HashMap<>();// 存放内容抽取的解析器
    protected Map<String, LinkListParserInterface> linkListParsers = new HashMap<>();// 存放链接发现的解析器
    protected ThreadPool threadPool = ThreadPool.getInstance();

    private String getDomainName(String url) {
        int ptr1 = 8, ptr2 = 0;
        for (ptr2 = ptr1; ptr2 < url.length() && url.charAt(ptr2) != '/'; ++ptr2) ;
        return url.substring(ptr1, ptr2);
    }

    // the parameter
    // url: the site's url
    // contentParserName: the page's content parser name
    // linkListParserName: the page's links parser
    // multiThread: whether the site could be crawled in parallel
    public void getData(String url, String contentParserName, String linkListParserName, String multiThread, int deep) {
        String domainName = getDomainName(url);
        if (!antiSpiderSite.contains(domainName)) {
            try {
                Document doc = Jsoup.connect(url).get();
                if (!contentParserName.equals("")) {// need to get the page's content
                    ContentParserInterface contentParser = contentParsers.get(contentParserName);
                    List<String> title_content = contentParser.getContent(doc);
                    //System.out.println(title_content.get(1));
                    long timeStamp = System.currentTimeMillis() / (1000 * 3600);
                    DB.persistent(title_content.get(0), title_content.get(1), timeStamp);
                }
                if (!linkListParserName.equals("") && deep <= deepLimit) {// need to get the page's links
                    LinkListParserInterface linkListParser = linkListParsers.get(linkListParserName);
                    List<List<String>> resultList = linkListParser.getLinkList(doc);
                    if (multiThread.equals("true")) {// this site could be crawled in parallel
                        for (List<String> item : resultList) {
                            Runnable childSpider = new ChildSpider(item.get(0), item.get(1), item.get(2), item.get(3), this, deep + 1);
                            threadPool.addTasks(childSpider);
                        }
                    } else {
                        for (List<String> item : resultList) {
                            getData(item.get(0), item.get(1), item.get(2), item.get(3), deep + 1);
                        }
                    }
                }

                delay();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    protected void delay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
