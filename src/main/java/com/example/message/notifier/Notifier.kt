package com.example.message.notifier

import com.example.message.domain.Message

interface Notifier {
    fun notify(message: Message)
}