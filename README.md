
# Максим Роботы Ява (набор инструментов)

**Max Bots (SDK)** — это неофициальное Java-решение для быстрой интеграции функций мессенджера Max для разработки ботов.

----------

## ✨ Особенности

- **WebHook и LongPoll:** Поддержка технологий разного типа
- **Удобство:** Создание бота, принятие и отправка сообщений происходит с минимальными затратами
----------

## 📦 Установка

### Maven

 `pom.xml`:

```markup
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
```markup
	<dependency>
	    <groupId>com.github.FLOERKA</groupId>
	    <artifactId>MaxJavaSdk</artifactId>
	    <version>1.0.0</version>
	</dependency>
```

### Gradle

Gradle

```css
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
```
```css
	dependencies {
	        implementation 'com.github.FLOERKA:MaxJavaSdk:Tag'
	}
```

----------

## 🚀 Быстрый старт

Java

```
public class MessageBot extends LongPollingMaxBot {  
  
    public MessageBot(String token) {  
        super(token);  
    }  
  
    @Override  
  public void update(Update update) {  
  
        Update.Type type = update.getTypeAsEnum();  
        if(type == Update.Type.MESSAGE_CREATE) {  
            Message message = update.getMessage();  
            User sender = message.getSender();  
            sendMessage(sender.getUserId(), "Получено новое сообщение!");  
        }  
        super.update(update);  
    }  
  
  
    private void sendMessage(long userId, String text) {  
        NewMessageBody body = new NewMessageBody.Builder().format(TextFormat.MARKDOWN)  
                .text(text).build();  
        execute(new SendMessageRequest.Builder().userId(userId).body(body).build());  
    }  
      
}

```
```
public static void main(String[] args) {  
    MessageBot messageBot = new MessageBot("TOKEN");  
    //messageBot.stop(); # Принудительная остановка бота  
}
```

----------

## 🛠 Основные возможности

### Быстрый запуск бота без отдельных классов

Используйте статик метод для создания быстрого бота с возможностью переопр:

Java

```
public static void main(String[] args) {  
    MaxBots.fastBot("TOKEN", update -> {  
        // TODO:  
  });  
}

```

## 📄 Лицензия

Распространяется под лицензией MIT. Свободный доступ к изменению любого кода. Проект был создан с учебной целью.
