package holidayInfoParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.LinkListParserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuan.wei on 5/22/17.
 */
public class HolidaySubLinkListParser implements LinkListParserInterface {
    private String prefix = "https://www.timeanddate.com";

    @Override
    public List<List<String>> getLinkList(Document doc) {
        List<List<String>> resultList = new ArrayList<>();
        Elements elements = doc.select(".article.eight.columns:has(a)");
        String article_eight_columns = "";
        if(!elements.isEmpty()) {
            article_eight_columns = elements.first().toString();
            Elements aList = elements.first().getElementsByAttribute("href");
            for (Element a : aList) {
                List<String> item = new ArrayList<>();
                item.add(prefix + a.attr("href"));
                // System.out.println(item.get(0));
                item.add("ContentParser");
                item.add("HolidaySubLinkListParser");
                item.add("true");
                resultList.add(item);
            }
        }

        elements = doc.select("p:has(a)");
        for(Element p : elements) {
            if(p.parent().toString().equals(article_eight_columns)) {
                Element a = p.getElementsByAttribute("href").first();
                List<String> item = new ArrayList<>();
                item.add(prefix + a.attr("href"));
                // System.out.println(item.get(0));
                item.add("ContentParser");
                item.add("HolidaySubLinkListParser");
                item.add("true");
                resultList.add(item);
            }
        }
        return resultList;
    }

//    public static void main(String[] args) throws IOException {
//        Document doc = Jsoup.connect("https://www.timeanddate.com/holidays/common/new-year-eve").get();
//        new HolidaySubLinkListParser().getLinkList(doc);
//    }
}
