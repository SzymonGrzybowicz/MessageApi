package com.example.message.notifier

import com.example.message.domain.Message
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification

internal class FirebaseNotifier : Notifier {

    override fun notify(message: Message) {
        message.conversation?.with?.forEach {
            sendMessage(
                    it.firebaseAuthTokens.map { t -> t.firebaseAuthToken }
            )
        }
    }

    private fun sendMessage(tokens: List<String>) {
        if (tokens.isEmpty()) {
            return
        }
        val message = MulticastMessage.builder()
                .setNotification(Notification("New Message", "New Message"))
                .addAllTokens(tokens)
                .build()
        FirebaseMessaging.getInstance().sendMulticastAsync(message)
    }
}