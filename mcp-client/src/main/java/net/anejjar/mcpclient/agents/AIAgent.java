package net.anejjar.mcpclient.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class AIAgent {
    private ChatClient chatClient;

    public AIAgent(ChatClient.Builder chatClient, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chatClient
                .defaultToolCallbacks(toolCallbackProvider)
                .defaultSystem("Answer the user question using provided tools")
                .build();
    }

    public String askLLLM(String query){
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }

    }

