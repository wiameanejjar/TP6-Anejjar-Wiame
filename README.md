# Projet : Chatbot Agentique avec Spring AI et MCP
## 📌 Objectif du TP
Ce projet vise à développer une application de chatbot de nouvelle génération, capable d'interagir
de manière intelligente et contextuelle avec les utilisateurs. Au cœur de cette solution se trouve l'intégration 
du protocole MCP (Multi-Agent Communication Protocol), permettant une communication fluide entre différents services et agents,
qu'ils soient développés en Java (Spring), Python ou NodeJS.   
Plus spécifiquement, les objectifs incluent de savoir :
  - Architecture et Spécifications du Protocole MCP
  - Comment créer un serveur MCP SSE avec Java Spring
  - Comment Tester le serveur MCP avec Postman
  - Comment Créer un Client MCP avec Spring AI, Llama3.1, Claude et OpenAI
  - Comment déployer un serveur MCP NodeJS en mode STDIO dans le client MCP Spring AI
  - Comment Créer un serveur MCP avec Python
  - Comment déployer un serveur MCP Python en mode STDIO dans le client MCP Spring AI
  - Comment rendre l'application Agentique

---
## 🧱 Structure du Projet

Le projet mcp-demo-spring-python est organisée pour séparer les responsabilités et faciliter le développement. Il se compose des modules principaux suivants :

  ![img](screens/mcp-server/structuregeneral.JPG)

  - mcp-client :  
Ce module est côté client. Il est développé en Java avec Spring Boot et utilise Spring AI.  Son rôle il reçoit les requêtes des utilisateurs, les traite en utilisant des modèles d'IA externes (Llama3.1, Claude, OpenAI) via Spring AI, et interagit avec les serveurs MCP pour exécuter des outils spécifiques.  
     - Fonctionnalités clés :  
Interface avec les modèles de langage via Spring AI.  
Gestion des conversations et du contexte utilisateur.  
Découverte et appel des outils exposés par les serveurs MCP.  
Peut inclure des agents (AIAgent) et des contrôleurs REST (AIRestController) pour exposer des API.

![img](screens/mcp-server/mcp-client.JPG)

  - mcp-server:  
Ce module représente le serveur MCP développé en Java avec Spring Boot. Son rôle c'est qu'il expose des fonctionnalités (outils) spécifiques via le protocole MCP. Ces outils peuvent être appelés par le mcp-client ou d'autres clients MCP.  
    - Fonctionnalités clés :  
Implémentation de services métier (StockTolls.java pour des outils liés aux actions).  
Exposition de ces services en tant qu'outils MCP.  
Peut fonctionner comme un serveur SSE (Server-Sent Events) pour une communication en temps réel.

![img](screens/mcp-server/mcp-server.JPG)

   - python-mcp-server :  
Ce module est un exemple de serveur MCP développé en Python. Son rôle est similaire au mcp-server Java, il expose des fonctionnalités (outils) spécifiques, mais implémentées en Python. Cela démontre la capacité du protocole MCP à intégrer des services développés dans différents langages.  
     - Fonctionnalités clés :  
Implémentation de logiques métier en Python (server.py pour des informations sur les employés).  
Exposition de ces logiques en tant qu'outils MCP.  
Peut être déployé en mode STDIO (Standard Input/Output) pour une communication directe avec le client MCP Spring AI.

![img](screens/mcp-server/puthon-mcp.JPG)

# Technologies Utilisées
Backend : Java (Spring Boot, Spring AI), Python, NodeJS  
Protocoles : MCP (Multi-Agent Communication Protocol), SSE  
Modèles d'IA : Llama3.1  

## 📄 Explication détaillée d'implémentation de projet

## Module mcp-server:
### 1. Classe StockTools.java :  
  Le fichier StockTolls.java, situé dans le package net.anejjar.mcpserver.tools, définit un outil de gestion fictive d'informations sur des entreprises, utilisé dans le contexte d'une application basée sur Spring AI. Il contient une liste prédéfinie de sociétés marocaines représentées par la classe Company, incluant des données comme l’activité, le chiffre d'affaires, le nombre d’employés et le pays. À travers l’utilisation de l’annotation @Tool, plusieurs méthodes sont exposées comme des outils accessibles à des agents intelligents : getAllCompanies() permet de récupérer la liste complète des entreprises, getCompanyByName(String name) retourne une entreprise en fonction de son nom, et getStockByCompanyName(String name) génère un objet Stock avec une valeur aléatoire simulant un prix de marché. 
  
![img](screens/mcp-server/stocktools1.JPG)
![img](screens/mcp-server/stocktools2.JPG)
  
### 2. Classe MCPServerApplication :

Le fichier McpServerApplication.java constitue la classe principale de l'application Spring Boot située dans le module mcpserver. Grâce à l’annotation @SpringBootApplication, il sert de point d’entrée pour le lancement de l'application via la méthode main(). Une configuration essentielle y est déclarée avec la méthode annotée @Bean, qui expose un objet MethodToolCallbackProvider. Ce composant permet de rendre disponible la classe StockTolls comme source d'outils exploitables par Spring AI. En effet, sans cette déclaration, les méthodes annotées avec @Tool dans StockTolls ne seraient jamais reconnues ni exécutables dans le contexte de l'application. Ce code montre donc comment intégrer et activer dynamiquement des outils personnalisés.

![img](screens/mcp-server/mcpserverApp.JPG)

### 3. Application.properties :  

Ce fichier permet de spécifier la configuration principale du serveur Spring Boot, notamment les paramètres liés au serveur MCP (Model Context Protocol), aux endpoints, au port d’exécution et au niveau de journalisation.    

  - spring.application.name=mcp-server : définit le nom de l'application Spring Boot.  
  - Configuration du serveur MCP (Model Context Protocol) :  
       - spring.ai.mcp.server.name=spring-mcp-server : le nom interne du serveur MCP.  
       - spring.ai.mcp.server.type=sync : mode de traitement (synchrone).  
       - spring.ai.mcp.server.version=1.0.0 : version déclarée du serveur.  
       - spring.ai.mcp.server.sse-endpoint=/sse : endpoint pour les connexions SSE (Server-Sent Events).  
       - spring.ai.mcp.server.sse-message-endpoint=/mcp/message : endpoint pour l’envoi des messages SSE.  
       - spring.ai.mcp.server.prompt-change-notification=true , spring.ai.mcp.server.tool-change-notification=true , spring.ai.mcp.server.resource-change-notification=true : ces trois lignes activent les notifications automatiques en cas de changement de prompt, d’outil ou de ressource.  
  - Configuration du serveur HTTP :  
        - server.port=8899 : le serveur tourne sur le port 8899.  
  - Configuration des logs:  
       - logging.level.io.modelcontextprotocol=TRACE , logging.level.org.springframework.ai.mcp=TRACE : permet d’avoir un niveau de log très détaillé (TRACE) sur le protocole MCP et sur Spring AI.
   
    ![img](screens/mcp-server/propertiesformcpserver.JPG)












