package chapter1.kai.aicodehelper.ai.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class QuoteSearchTool {
    /**
     * 获取名言
     *
     * @param keyword 搜索关键词（如：love, life, success等）
     * @return 相关的名言列表
     */
    @Tool(name = "quoteSearch", value = """
            Retrieves relevant quotes from quotes.toscrape.com based on a keyword.
            Use this tool when the user asks for quotes about specific topics like love, life, success, friendship, etc.
            The input should be a clear search term, e.g., 'love', 'life', 'success'.
            """
    )
    public String searchQuote(@P(value = "the keyword to search for quotes") String keyword) {
        List<String> questions = new ArrayList<>();
        // 构建搜索URL（编码关键词以支持中文）
        String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        // 使用专门为练习设计的 quotes.toscrape.com 网站
        String url = "http://quotes.toscrape.com/tag/" + encodedKeyword;
        // 发送请求并解析页面
        Document doc;
        try {
            doc = Jsoup.connect(url)
                    .userAgent("QuoteLearningBot/1.0 (Educational; Contact: kaitsai9101@gmail.com)")
                    .timeout(5000)
                    .get();
        } catch (IOException e) {
            log.error("Failed to fetch quotes from web", e);
            return e.getMessage();
        }
        // 提取练习数据
        // quotes.toscrape.com 的页面结构中，名言在 .quote 类下的 .text 元素中
        Elements quotes = doc.select(".quote .text");
        quotes.forEach(el -> questions.add(el.text().trim()));

        return questions.isEmpty() ? "未找到相关内容" : String.join("\n", questions);
    }
}
