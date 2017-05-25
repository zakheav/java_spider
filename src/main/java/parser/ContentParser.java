package parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuan.wei on 5/16/17.
 */
public class ContentParser implements ContentParserInterface {
    @Override
    // get the title and main body of the news
    public List<String> getContent(Document doc) {
        List<String> title_content = new ArrayList<>();
        String title = doc.select("title").first().text();

        Elements elements = doc.select("p");
        Map<String, StringBuilder> parent_children = new HashMap<>();
        for (Element e : elements) {
            String parent = e.parent().toString();
            if (!parent_children.containsKey(parent)) {
                parent_children.put(parent, new StringBuilder());
            }
            StringBuilder sb = parent_children.get(parent);
            sb.append(e.text());
            Elements elist = e.children();
            for (Element ee : elist) {
                sb.append(" " + ee.text() + " ");
            }
        }

        String mainDataParent = "";
        int max = 0;
        for (String parent : parent_children.keySet()) {
            if (parent_children.get(parent).length() > max) {
                max = parent_children.get(parent).length();
                mainDataParent = parent;
            }
        }

        title_content.add(title);
        title_content.add(parent_children.get(mainDataParent).toString());// get the main body of the news
        return title_content;
    }
}
