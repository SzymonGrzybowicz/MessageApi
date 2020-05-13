package com.example.message.notifier;

import com.example.message.domain.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageNotifier implements Notifier {

    public MessageNotifier() {
        notifiers = new ArrayList<>();
        notifiers.add(new FirebaseNotifier());
    }

    @Override
    public void notify(Message message){
        for (Notifier notifier : notifiers) {
            notifier.notify(message);
        }
    }

    private final List<Notifier> notifiers;
}
