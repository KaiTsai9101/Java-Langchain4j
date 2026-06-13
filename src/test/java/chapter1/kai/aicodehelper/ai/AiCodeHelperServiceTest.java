package chapter1.kai.aicodehelper.ai;

import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeHelperServiceTest {

    @Resource
    private AiCodeHelperService aiCodeHelperService;
    @Resource
    private AiCodeHelperService aiCodeHelperServiceWithMemory;
    @Resource
    private AiCodeHelperService aiCodeHelperServiceWithRag;
    @Resource
    private AiCodeHelperService aiCodeHelperServiceWithTools;
    @Resource
    private AiCodeHelperService aiCodeHelperServiceWithMcpTools;
    @Resource
    private AiCodeHelperService aiCodeHelperServiceWithLogs;

    @Test
    void chat() {
        String result = aiCodeHelperService.chat("你好，我是Kai");
        System.out.println(result);
    }

    @Test
    void chatWithMemory() {
        int userId = 1;
        String result = aiCodeHelperServiceWithMemory.chatWithMemory(userId, "你好，我是Kai");
        System.out.println(result);
        result = aiCodeHelperServiceWithMemory.chatWithMemory(userId, "你好，我是谁来着？");
        System.out.println(result);
    }

    @Test
    void chatForReport() {
        String userMessage = "你好，我是程序员Kai，学编程两年半，请帮我指定学习报告";
        AiCodeHelperService.Report report = aiCodeHelperService.chatForReport(userMessage);
        System.out.println(report);
    }

    @Test
    void chatWithRag() {
        Result<String> result = aiCodeHelperServiceWithRag.chatWithRag("学习 Java 爬虫需要注意什么");
        System.out.println(result.sources());       // Rag 检索到的文档
        System.out.println(result.content());       // 实际 AI 输出的内容
    }

    @Test
    void chatWithTools() {
        String result = aiCodeHelperServiceWithTools.chatWithTools("请帮我搜索关于 love 的名言");
        System.out.println(result);
    }

    @Test
    void chatWithMcp() {
        String result = aiCodeHelperServiceWithMcpTools.chatWithMcpTools("什么是 Java 爬虫");
        System.out.println(result);
    }

    @Test   // 会报错，因为有违禁词 "kill"
    void chatWithGuardrail() {
        String result = aiCodeHelperService.chat("kill the game");
        System.out.println(result);
    }

    @Test
    void chatWithLogs() {
        String result = aiCodeHelperServiceWithLogs.chatWithLogs("success");
        System.out.println(result);
    }
}