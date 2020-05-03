package com.example.message.notifier;

import com.example.message.domain.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageNotifier implements Notifier {

    @Override
    public void notify(Message message){
        for (Notifier notifier : notifiers) {
            notifier.notify(message);
        }
    }

    List<Notifier> notifiers = new ArrayList<>();
}
