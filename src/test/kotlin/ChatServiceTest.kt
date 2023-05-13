import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ChatServiceTest {

    private val message1 = Message("Hello", 1, false)
    private val message2 = Message("Hello", 1)
    private val message3 = Message("Привет", 1, false)
    private val message4 = Message("Здравствуйте", 1, false)

    @Before
    fun clearBeforeTest() {
        ChatService.clear()
    }

    @Test
    fun deleteMessageTrue() {
        ChatService.sendMessage(2, message1)
        val result = ChatService.deleteMessage(2, message2)
        kotlin.test.assertTrue(result == true)
    }

    @Test(expected = UserNotFoundException::class)
    fun deleteMessageNotFoundUser() {
        ChatService.sendMessage(2, message1)
        ChatService.deleteMessage(4, message1)
    }

    @Test(expected = UserNotFoundException::class)
    fun getChatsNotFoundUser() {
        ChatService.sendMessage(1, message1)
        ChatService.getChats(4)
    }

    @Test(expected = UserNotFoundException::class)
    fun deleteChatNotFoundUser() {
        ChatService.sendMessage(1, message1)
        ChatService.deleteChat(4)
    }

    @Test
    fun getUnreadChatsCountTrue() {
        ChatService.sendMessage(1, message2)
        ChatService.sendMessage(2, message1)
        ChatService.sendMessage(3, message3)
        ChatService.sendMessage(1, message4)
        val result = ChatService.getUnreadChatsCount()
        kotlin.test.assertEquals(3, result)

    }

    @Test
    fun getUnreadMessageCount() {
        ChatService.sendMessage(1, message2)
        ChatService.sendMessage(2, message1)
        ChatService.sendMessage(3, message3)
        ChatService.sendMessage(1, message4)
        val result = ChatService.getUnreadMessageCount(1)
        kotlin.test.assertEquals(1, result)
    }

    @Test(expected = UserNotFoundException::class)
    fun getUnreadMessageCountUserNotFound() {
        ChatService.sendMessage(1, message2)
        ChatService.sendMessage(1, message4)
        ChatService.getUnreadMessageCount(2)
    }


    @Test(expected = UserNotFoundException::class)
    fun listMessagesUserNotFound() {
        ChatService.sendMessage(1, message2)
        ChatService.sendMessage(1, message4)
        ChatService.listMessages(2)
    }
}