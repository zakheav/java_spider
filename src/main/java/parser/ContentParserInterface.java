package parser;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by yuan.wei on 5/16/17.
 */
public interface ContentParserInterface {
    List<String> getContent(Document doc);
}
