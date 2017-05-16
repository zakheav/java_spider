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
    protected Set<String> antiSpiderSite = new HashSet<>();
    protected Map<String, ContentParserInterface> contentParsers = new HashMap<>();// 存放内容抽取的解析器
    protected Map<String, LinkListParserInterface> linkListParsers = new HashMap<>();// 存放链接发现的解析器
    protected ThreadPool threadPool = ThreadPool.getInstance();

    private String getDomainName(String url) {
        int ptr1 = 8, ptr2 = 0;
        for (ptr2 = ptr1; ptr2 < url.length() && url.charAt(ptr2) != '/'; ++ptr2) ;
        return url.substring(ptr1, ptr2);
    }

    // 传入参数分别是内容解析器名称和链接解析器名称，multiThread表示是否需要开启多线程
    public void getData(String url, String contentParserName, String linkListParserName, String multiThread) {
        String domainName = getDomainName(url);
        if (!antiSpiderSite.contains(domainName)) {
            try {
                Document doc = Jsoup.connect(url).get();
                if (!contentParserName.equals("")) {// 需要解析内容
                    ContentParserInterface contentParser = contentParsers.get(contentParserName);
                    List<String> title_content = contentParser.getContent(doc);
                    long timeStamp = System.currentTimeMillis() / (1000 * 3600);
                    System.out.println(url);
                    System.out.println(title_content.get(0));
                    DB.persistent(title_content.get(0), title_content.get(1), timeStamp);
                }
                if (!linkListParserName.equals("")) {// 需要解析链接
                    LinkListParserInterface linkListParser = linkListParsers.get(linkListParserName);
                    List<List<String>> resultList = linkListParser.getLinkList(doc);
                    if (multiThread.equals("true")) {// 当前网站可以并行爬取
                        for (List<String> item : resultList) {
                            Runnable childSpider = new ChildSpider(item.get(0), item.get(1), item.get(2), item.get(3), this);
                            threadPool.addTasks(childSpider);
                        }
                    } else {// 当前网站只能单线程爬取
                        for (List<String> item : resultList) {
                            getData(item.get(0), item.get(1), item.get(2), item.get(3));
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
