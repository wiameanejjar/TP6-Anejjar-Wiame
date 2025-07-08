package net.anejjar.mcpclient.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class AIAgent {
    private ChatClient chatClient;

    public AIAgent(ChatClient.Builder chatClient, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chatClient
                .defaultToolCallbacks(toolCallbackProvider)
                .defaultSystem("Answer the user question using provided tools")
                // cette ligne c'est pour ajouter la mémoire à notre agent pour stocker la conversation
                // 20 c'est pour dire de me rendre juste les 20 derniers messages
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(
                        MessageWindowChatMemory.builder().maxMessages(20).build()
                ).build())
                .build();
    }

    public String askLLLM(String query){
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }

    }

