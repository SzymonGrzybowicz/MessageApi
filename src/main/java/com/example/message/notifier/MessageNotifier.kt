package com.example.message.notifier

import com.example.message.domain.Message
import org.springframework.stereotype.Component

@Component
class MessageNotifier : Notifier {

    override fun notify(message: Message) {
        notifiers.forEach {
            it.notify(message)
        }
    }

    private val notifiers = listOf<Notifier>(
            FirebaseNotifier()
    )
}