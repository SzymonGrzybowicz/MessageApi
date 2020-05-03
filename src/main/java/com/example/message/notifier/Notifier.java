package com.example.message.notifier;

import com.example.message.domain.Message;

public interface Notifier {
    void notify(Message message);
}
