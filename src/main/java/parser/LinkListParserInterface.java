package parser;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by yuan.wei on 5/16/17.
 */
public interface LinkListParserInterface {
    // 返回多组数据，每组数据四个部分：
    // 1 链接的url
    // 2 url对应contentParser的名称
    // 3 url对应linkListParser的名称
    // 4 "true"/"false"，表示url对饮网站爬取是否开启多线程
    List<List<String>> getLinkList(Document doc);
}
