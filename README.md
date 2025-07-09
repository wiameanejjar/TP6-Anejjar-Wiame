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
















