package googleNewsParser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.LinkListParserInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuan.wei on 5/16/17.
 */
public class ArticleLinkListParser implements LinkListParserInterface {
    @Override
    public List<List<String>> getLinkList(Document doc) {
        List<List<String>> resultList = new ArrayList<>();
        Elements elements = doc.select("h2.esc-lead-article-title:has(a)");
        for (Element e : elements) {
            List<String> item = new ArrayList<>();
            item.add(e.children().first().attr("href"));
            item.add("ContentParser");
            item.add("");
            item.add("false");
            resultList.add(item);
        }
        return resultList;
    }
}
