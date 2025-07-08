package net.anejjar.mcpserver;

import net.anejjar.mcpserver.tools.StockTolls;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpServerApplication.class, args);
	}

	/* si on oublie @Bean le programme va jamais s'executer */
	@Bean
	public MethodToolCallbackProvider getMethodToolCallbackProvider() {
		return MethodToolCallbackProvider.builder()
				.toolObjects(new StockTolls())
				.build();
	}

}

