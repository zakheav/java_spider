package parser;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by yuan.wei on 5/16/17.
 */
public interface LinkListParserInterface {
    // return a data list, each data contains 4 parts
    // 1 link's url
    // 2 the content parser name for the site correspond to the url
    // 3 the links parser name for the site correspond to the
    // 4 "true"/"false", it's means whether the site allows to be crawled in parallel
    List<List<String>> getLinkList(Document doc);
}
