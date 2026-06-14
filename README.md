# AI 名言名句查询

基于 Spring Boot + Vue3 的智能名言名句问答系统

## 📚 项目说明

这是一个**学习用途**的 AI 名言名句查询练习项目，用于理解大语言模型集成、RAG（检索增强生成）以及前后端分离架构的基本原理。

> **注意**: 虽然项目文件名为AICoderHelper，但为了便于测试和学习，本项目最终功能定位为"AI名言名句查询"以测试AI的网络搜索，用以回答关于名人名言、经典语录的相关问题。

## ⚠️ 免责声明

1. 本项目仅用于**学习和研究目的**
2. 使用本项目需要遵守阿里云 DashScope API 的服务条款
3. 请勿将本项目用于商业用途
4. 使用者需自行承担法律责任和 API 调用费用
5. **如使用网络爬虫功能**：
   - 请遵守目标网站的 `robots.txt` 协议和服务条款
   - 请勿用于大规模数据采集或商业爬取
   - 使用者需自行承担因爬虫行为产生的法律责任

## 🛠️ 技术栈

### 后端
- JDK 21
- Spring Boot 3.5.14
- LangChain4j 1.1.0 (LLM 框架)
- 阿里云通义千问 (DashScope)
- Maven 3.6+

### 前端
- Vue 3.3.4
- Vite 4.4.9
- Marked (Markdown 渲染)

## 📖 学习内容
- Spring Boot RESTful API 开发
- Server-Sent Events (SSE) 流式响应
- LangChain4j 框架集成
- RAG（检索增强生成）实现
- Vue3 组件化开发
- Markdown 实时渲染

## 🚀 运行方式

### 前置要求
- JDK 21+
- Node.js 16+
- Maven 3.6+
- 阿里云 DashScope API Key

### 后端启动

1. **配置 API Key**
   
   编辑 `src/main/resources/application-local.yml`：
```yaml langchain4j: 
community: 
   dashscope: 
      chat-model: 
          api-key: <Your API Key here>
```

2. **编译并运行**
```bash 
mvn clean install 
mvn spring-boot:run
```
   后端服务将在 `http://localhost:8081/api` 启动

### 前端启动

```bash 
cd ai-code-helper-frontend 
npm install 
npm run dev
```
  前端应用将在 `http://localhost:3000` 启动

### 快速测试

打开浏览器访问 `http://localhost:3000`，即可开始查询名言名句，例如：
- "请帮我搜索关于 love 的名言"
- "请帮我搜索关于 success 的名言"
- "请帮我搜索关于 kill 的名言"  （出现违禁词理应报错）

## 📁 项目结构

```
ai-code-helper/ 
├── src/main/java/.../ai/ # AI 核心模块 
│ ├── guardrail/ # 安全护栏 
│ ├── mcp/ # MCP 配置 
│ ├── rag/ # RAG 配置 
│ └── tools/ # 工具类 
├── src/main/resources/ # 配置文件和资源 
├── ai-code-helper-frontend/ # 前端项目 
└── pom.xml # Maven 配置
```

## ⚖️ 法律提示

在使用本项目前，请：
- 阅读并遵守阿里云 DashScope API 的服务条款
- 合理控制 API 调用频率
- 妥善保管 API Key，不要提交到公开代码仓库
- 尊重知识产权
- **如使用网络爬虫功能**：
  - 遵守目标网站的 `robots.txt` 协议
  - 尊重网站的服务条款和使用限制
  - 控制爬取频率，避免对服务器造成压力
  - 不要爬取受版权保护的内容用于商业用途
  - 使用者需自行承担因爬虫行为产生的法律责任


## 🌐 API 接口

### 聊天接口

- **URL**: `GET /api/ai/chat`
- **参数**:
  - `memoryId`: 聊天室 ID
  - `message`: 用户消息
- **返回**: SSE 流式响应

## 🤝 贡献指南

1. Fork 本项目
2. 创建功能分支
3. 提交更改
4. 创建 Pull Request

## 📄 许可证

本项目采用 MIT 许可证。
