package holidayInfoParser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.LinkListParserInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuan.wei on 5/22/17.
 */
public class HolidayLinkListParser implements LinkListParserInterface {
    private String prefix = "https://www.timeanddate.com";
    @Override
    public List<List<String>> getLinkList(Document doc) {
        List<List<String>> resultList = new ArrayList<>();
        Elements elements = doc.select("table.zebra tbody");
        for (Element e : elements) {
            Elements trs = e.children();
            for (Element tr : trs) {
                Element td = tr.child(2);
                List<String> item = new ArrayList<>();
                item.add(prefix + td.child(0).attr("href"));
                // System.out.println(item.get(0));
                item.add("ContentParser");
                item.add("HolidaySubLinkListParser");
                item.add("false");
                resultList.add(item);
            }
        }
        return resultList;
    }

//    public static void main(String[] args) throws IOException {
//        Document doc = Jsoup.connect("https://www.timeanddate.com/holidays/us/2016").get();
//        new HolidayLinkListParser().getLinkList(doc);
//    }
}
