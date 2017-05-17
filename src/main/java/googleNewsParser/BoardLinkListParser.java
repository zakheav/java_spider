package googleNewsParser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import parser.LinkListParserInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuan.wei on 5/16/17.
 */
public class BoardLinkListParser implements LinkListParserInterface {
    @Override
    public List<List<String>> getLinkList(Document doc) {
        String urlPrefix = "https://news.google.com";
        List<List<String>> resultList = new ArrayList<>();
        List<String> item;
        Element element;
//        element = doc.select("a.persistentblue[href]:containsOwn(World)").first();// a标签，class=persistentblue，有href属性，文本中是World
//        item = new ArrayList<>();
//        item.add(urlPrefix + element.attr("href"));
//        item.add("");
//        item.add("ArticleLinkListParser");
//        item.add("true");
//
//        resultList.add(item);
//
//        element = doc.select("a.persistentblue[href]:containsOwn(U.S.)").first();// a标签，class=persistentblue，有href属性，文本中是U.S
//        item = new ArrayList<>();
//        item.add(urlPrefix + element.attr("href"));
//        item.add("");
//        item.add("ArticleLinkListParser");
//        item.add("true");
//
//        resultList.add(item);
//
//        element = doc.select("a.persistentblue[href]:containsOwn(Business)").first();// a标签，class=persistentblue，有href属性，文本中是Business
//        item = new ArrayList<>();
//        item.add(urlPrefix + element.attr("href"));
//        item.add("");
//        item.add("ArticleLinkListParser");
//        item.add("true");
//
//        resultList.add(item);
//
//        element = doc.select("a.persistentblue[href]:containsOwn(Technology)").first();// a标签，class=persistentblue，有href属性，文本中是Technology
//        item = new ArrayList<>();
//        item.add(urlPrefix + element.attr("href"));
//        item.add("");
//        item.add("ArticleLinkListParser");
//        item.add("true");
//
//        resultList.add(item);
//
//        element = doc.select("a.persistentblue[href]:containsOwn(Entertainment)").first();// a标签，class=persistentblue，有href属性，文本中是Entertainment
//        item = new ArrayList<>();
//        item.add(urlPrefix + element.attr("href"));
//        item.add("");
//        item.add("ArticleLinkListParser");
//        item.add("true");
//
//        resultList.add(item);
//
        element = doc.select("a.persistentblue[href]:containsOwn(Sports)").first();// a标签，class=persistentblue，有href属性，文本中是Sports
        item = new ArrayList<>();
        item.add(urlPrefix + element.attr("href"));
        item.add("");
        item.add("ArticleLinkListParser");
        item.add("true");

        resultList.add(item);
//
//        element = doc.select("a.persistentblue[href]:containsOwn(Science)").first();// a标签，class=persistentblue，有href属性，文本中是Science
//        item = new ArrayList<>();
//        item.add(urlPrefix + element.attr("href"));
//        item.add("");
//        item.add("ArticleLinkListParser");
//        item.add("true");
//
//        resultList.add(item);
//
//        element = doc.select("a.persistentblue[href]:containsOwn(Health)").first();// a标签，class=persistentblue，有href属性，文本中是Health
//        item = new ArrayList<>();
//        item.add(urlPrefix + element.attr("href"));
//        item.add("");
//        item.add("ArticleLinkListParser");
//        item.add("true");
//
//        resultList.add(item);
//
//        element = doc.select("a.persistentblue[href]:containsOwn(Spotlight)").first();// a标签，class=persistentblue，有href属性，文本中是Spotlight
//        item = new ArrayList<>();
//        item.add(urlPrefix + element.attr("href"));
//        item.add("");
//        item.add("ArticleLinkListParser");
//        item.add("true");
//
//        resultList.add(item);

        return resultList;
    }
}
