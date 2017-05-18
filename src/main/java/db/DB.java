package db;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuan.wei on 5/15/17.
 */
public class DB {
    private static MongoClient client = new MongoClient();
    private static MongoDatabase database = client.getDatabase("news");// 打开一个数据库（如果不存在就新建一个)

    private static List<String> clean(String article) {
        article = article.toLowerCase();
        List<String> result = new ArrayList<>();
        int ptr1 = 0, ptr2 = 0;
        while (ptr1 < article.length()) {
            while (ptr1 < article.length() && !(article.charAt(ptr1) >= 'a' && article.charAt(ptr1) <= 'z')) {
                ++ptr1;
            }
            for (ptr2 = ptr1; ptr2 < article.length() && (article.charAt(ptr2) >= 'a' && article.charAt(ptr2) <= 'z'); ++ptr2)
                ;
            if (ptr1 < article.length()) {
                result.add(article.substring(ptr1, ptr2));
            }
            ptr1 = ptr2;
        }
        return result;
    }

    private static Document findNews(String title) {
        MongoCollection<Document> collection = database.getCollection("articles");// 打开一个数据库表（如果不存在就打开一个）
        BasicDBObject queryObject = new BasicDBObject("title", title);
        FindIterable<Document> it = collection.find(queryObject);
        return it.first();
    }

    public static void persistent(String title, String article, long timeStamp) {
        MongoCollection<Document> collection = database.getCollection("articles");// 打开一个数据库表（如果不存在就打开一个）
        List<String> result = clean(article);
        // 查询这个新闻是否已经爬取过
        if (DB.findNews(title) == null && result.size() > 100) {// 简单判断是否是有效的文章
            Document doc = new Document("title", title).append("article", result).append("timestamp", timeStamp);
            collection.insertOne(doc);
        }
    }

    public static void deleteOldNews() {
        int timeStamp = (int) (System.currentTimeMillis() / (1000 * 3600)) - 24 * 7;// 用来过滤掉一周前的新闻
        MongoCollection<Document> collection = database.getCollection("articles");
        collection.deleteMany(Filters.lt("timestamp", timeStamp));
    }
}
