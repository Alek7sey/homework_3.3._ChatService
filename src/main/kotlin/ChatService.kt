object ChatService {
    private val chats = mutableMapOf<Int, Chat>()

    fun sendMessage(userId: Int, message: Message) {
        chats.getOrPut(userId) { Chat() }.messages += message
    }

    fun deleteMessage(userId: Int, message: Message): Boolean? {
        return if (chats[userId] != null) {
            chats[userId]?.messages?.removeIf { it.text == message.text && it.incomming == message.incomming }
        } else {
            throw UserNotFoundException("No user with id = $userId")
        }
    }

    fun getChats(userId: Int) {
        if (chats[userId] != null) {
            println(chats.filterKeys { it == userId }.values)
        } else {
            throw UserNotFoundException("No user with id = $userId")
        }
    }

    fun deleteChat(userId: Int) {
        if (chats[userId] != null) {
            chats.remove(userId)
        } else {
            throw UserNotFoundException("No user with id = $userId")
        }
    }

    fun printChats() {
        println(chats)
    }

    fun lastMessages(): List<String> {
        return chats.values.map { it.messages.lastOrNull()?.text ?: "Нет сообщений" }
    }

    fun getUnreadChatsCount(): Int {
        return chats.values.count { it.messages.find { !it.messageRead }?.messageRead == false }
    }

    fun getUnreadMessageCount(userId: Int): Int? {
        return if (chats[userId] != null) {
            chats[userId]?.messages?.count { !it.messageRead }
        } else {
            throw UserNotFoundException("No user with id = $userId")
        }
    }

    fun listMessages(userId: Int): List<String>? {
        return if (chats[userId] != null) {
            chats[userId]?.messages?.map { it.text }
        } else throw UserNotFoundException("No user with id = $userId")
    }

    fun clear() {
        chats.clear()
    }
}




