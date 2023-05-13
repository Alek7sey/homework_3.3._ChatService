import java.lang.RuntimeException

data class Message (val text: String, val incomming: Int = 0, var messageRead: Boolean = true) // incomming = 1 - входящее, 0 - исходящее, messageRead = false - не прочитано

data class Chat(val messages: MutableList<Message> = mutableListOf())

class UserNotFoundException(message: String) : RuntimeException(message) {
}

fun main () {
    ChatService.sendMessage(1, Message("Hi", 0, true))
    ChatService.sendMessage(2, Message("Hello", 1, false))
    ChatService.sendMessage(3, Message("Привет", 1, false))
    ChatService.sendMessage(1, Message("Здравствуйте", 1, false))
    ChatService.getChats(1)
    ChatService.printChats()
    println(ChatService.getUnreadChatsCount())
    println(ChatService.getUnreadMessageCount(3))
    println(ChatService.lastMessages())
    println()

    ChatService.printChats()
    println(ChatService.deleteMessage(3, Message("Hello", 1)))
    ChatService.printChats()
    println()
    ChatService.getChats(1)
    ChatService.deleteChat(1)
    ChatService.printChats()
    println(ChatService.listMessages(3))
}