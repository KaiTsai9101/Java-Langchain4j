package chapter1.kai.aicodehelper.ai;

import chapter1.kai.aicodehelper.ai.guardrail.SafeInputGuardrail;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;
import dev.langchain4j.service.spring.AiService;

import java.util.List;

//@AiService
@InputGuardrails({ SafeInputGuardrail.class })
public interface AiCodeHelperService {

    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(String userMessage);

    // 在 LangChain4j 中，当方法有多个参数时，除了 @MemoryId 参数外，其他参数也需要正确的注解。
    @SystemMessage(fromResource = "system-prompt.txt")
    String chatWithMemory(@MemoryId int memoryId,@UserMessage String userMessage);

    @SystemMessage(fromResource = "system-prompt.txt")
    Report chatForReport(String userMessage);

    @SystemMessage(fromResource = "system-prompt.txt")
    Result<String> chatWithRag(String userMessage);

    @SystemMessage(fromResource = "system-prompt.txt")
    String chatWithTools(String userMessage);

    @SystemMessage(fromResource = "system-prompt.txt")
    String chatWithMcpTools(String userMessage);

    // 学习报告
    record Report(String name, List<String> suggestionList){};
}
