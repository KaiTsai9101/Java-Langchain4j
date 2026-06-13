package chapter1.kai.aicodehelper.ai;

import chapter1.kai.aicodehelper.ai.tools.QuoteSearchTool;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiCodeHelperServiceFactory {

    @Resource
    private ChatModel qwenChatModel;

    @Resource
    private ChatModel myQwenChatModel;

    @Resource
    private ContentRetriever contentRetriever;

    @Resource
    private McpToolProvider mcpToolProvider;

    @Resource
    private StreamingChatModel qwenStreamingChatModel;
    @Bean
    public AiCodeHelperService aiCodeHelperService() {
        // 创建一个会话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        // 构造 AI Service
        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                .chatModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();
        return aiCodeHelperService;
    }

    @Bean
    public AiCodeHelperService aiCodeHelperServiceWithMemory() {
        // 创建一个会话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        // 构造 AI Service
        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                .chatModel(qwenChatModel)
                .chatMemory(chatMemory)     // 会话记忆
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))    // 多会话隔离会话记忆
                .build();
        return aiCodeHelperService;
    }

    @Bean
    public AiCodeHelperService aiCodeHelperServiceWithRag() {
        // 创建一个会话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        // 构造 AI Service
        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                .chatModel(qwenChatModel)
                .chatMemory(chatMemory)     // 会话记忆
                .contentRetriever(contentRetriever)    // RAG 检索增强生成
                .build();
        return aiCodeHelperService;
    }

    @Bean
    public AiCodeHelperService aiCodeHelperServiceWithTools() {
        // 创建一个会话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        // 构造 AI Service
        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                .chatModel(qwenChatModel)
                .chatMemory(chatMemory)     // 会话记忆
                .contentRetriever(contentRetriever)    // RAG 检索增强生成
                .tools(new QuoteSearchTool())       // 工具调用
                .build();
        return aiCodeHelperService;
    }

    @Bean
    public AiCodeHelperService aiCodeHelperServiceWithMcpTools() {
        // 创建一个会话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        // 构造 AI Service
        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                .chatModel(qwenChatModel)
                .chatMemory(chatMemory)     // 会话记忆
                .contentRetriever(contentRetriever)    // RAG 检索增强生成
                .tools(new QuoteSearchTool())       // 工具调用
                .toolProvider(mcpToolProvider)      // MCP 工具调用
                .build();
        return aiCodeHelperService;
    }

    @Bean
    public AiCodeHelperService aiCodeHelperServiceWithLogs() {
        // 创建一个会话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        // 构造 AI Service
        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                .chatModel(myQwenChatModel)
                .chatMemory(chatMemory)     // 会话记忆
                .contentRetriever(contentRetriever)    // RAG 检索增强生成
                .tools(new QuoteSearchTool())       // 工具调用
                .toolProvider(mcpToolProvider)      // MCP 工具调用
                .build();
        return aiCodeHelperService;
    }

    @Bean
    public AiCodeHelperService streamingAiCodeHelperService() {
        // 创建一个会话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        // 构造 AI Service
        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                .chatModel(myQwenChatModel)
                .streamingChatModel(qwenStreamingChatModel)     // 流式输出
                .chatMemory(chatMemory)     // 会话记忆
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))    // 多会话隔离会话记忆
                .contentRetriever(contentRetriever)    // RAG 检索增强生成
                .tools(new QuoteSearchTool())       // 工具调用
                .toolProvider(mcpToolProvider)      // MCP 工具调用
                .build();
        return aiCodeHelperService;
    }
}
