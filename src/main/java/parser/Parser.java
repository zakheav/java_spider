package parser;

import org.jsoup.nodes.Document;

/**
 * Created by yuan.wei on 5/15/17.
 */
public interface Parser {
    public String getTitle(Document doc);// 获取文章的题目

    public String getMainData(Document doc);// 获取主要内容

    public String getDate(Document doc);// 获取内容日期
}
