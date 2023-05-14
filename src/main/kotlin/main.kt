import java.lang.RuntimeException

data class Message (var id: Int, val text: String, val incomming: Int = 0, var messageRead: Boolean = true) // incomming = 1 - входящее, 0 - исходящее, messageRead = false - не прочитано

data class Chat(val messages: MutableList<Message> = mutableListOf())

class UserNotFoundException(message: String) : RuntimeException(message) {
}

fun main () {
    ChatService.sendMessage(1, Message(1, "Hi", 0, true))
    ChatService.sendMessage(2, Message(2, "Hello", 1, false))
    ChatService.sendMessage(3, Message(3, "Привет", 1, false))
    ChatService.sendMessage(3, Message(2, "Привет", 0))
    ChatService.sendMessage(3, Message(4, "Как дела?", 0))
    ChatService.sendMessage(3, Message(5, "Нормально", 1, false))
    ChatService.sendMessage(1, Message(4, "Здравствуйте", 1, false))
    ChatService.getChats(1)
    ChatService.printChats()
    println(ChatService.getUnreadChatsCount())
    println(ChatService.getUnreadMessageCount(3))
    println(ChatService.lastMessages())
    println()

    ChatService.printChats()
    println(ChatService.deleteMessage(3, Message(1, "Hello", 1)))
    ChatService.printChats()
    println()
    ChatService.getChats(1)
    ChatService.deleteChat(1)
    ChatService.printChats()
    println()
    println(ChatService.listMessages(3, 2, 3))
}