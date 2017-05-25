package wikiHolidayInfoParser;

import org.jsoup.nodes.Document;
import parser.LinkListParserInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuan.wei on 5/25/17.
 */
public class WikiLinkListParser implements LinkListParserInterface {
    @Override
    public List<List<String>> getLinkList(Document doc) {
        List<List<String>> resultList = new ArrayList<>();
        String[] urls = {
                "https://en.wikipedia.org/wiki/Thanksgiving_(United_States)",
                "https://en.wikipedia.org/wiki/Christmas",
                "https://en.wikipedia.org/wiki/New_Years_Day",
                "https://en.wikipedia.org/wiki/Easter",
                "https://en.wikipedia.org/wiki/Halloween",
                "https://en.wikipedia.org/wiki/Valentines_Day"
        };
        for (String url : urls) {
            List<String> item = new ArrayList<>();
            item.add(url);
            item.add("ContentParser");
            item.add("");
            item.add("true");
            resultList.add(item);
        }
        return resultList;
    }
}
