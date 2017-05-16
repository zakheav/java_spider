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
    public List<String> getContent(Document doc) {// 获取文章的题目与主要内容
        List<String> title_content = new ArrayList<>();
        String title = doc.select("title").first().text();

        StringBuilder content = new StringBuilder();
        Elements elements = doc.select("p");
        Map<String, StringBuilder> parent_children = new HashMap<>();
        for (Element e : elements) {
            String parent = e.parent().toString();
            if (!parent_children.containsKey(parent)) {
                parent_children.put(parent, new StringBuilder());
            }
            StringBuilder sb = parent_children.get(parent);
            sb.append(e.text());
        }

        String mainDataParent = "";
        int max = 0;
        for (String parent : parent_children.keySet()) {// 找到文章正文的父节点
            if (parent_children.get(parent).length() > max) {
                max = parent_children.get(parent).length();
                mainDataParent = parent;
            }
        }

        title_content.add(title);
        title_content.add(parent_children.get(mainDataParent).toString());// 提取文章正文
        return title_content;
    }
}
