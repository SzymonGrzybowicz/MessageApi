package com.example.message.notifier;

import com.example.message.domain.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;

import java.util.List;
import java.util.stream.Collectors;

class FirebaseNotifier implements Notifier {

    @Override
    public void notify(com.example.message.domain.Message message) {
        for (User member: message.getConversation().getWith()) {
            sendMessage(member.getFirebaseAuthTokens().stream().map(t -> t.getFirebaseAuthToken()).collect(Collectors.toList()));
        }
    }

    public void sendMessage(List<String> tokens) {
        if (tokens.isEmpty()) {
            return;
        }
        MulticastMessage message =
                MulticastMessage.builder()
                        .setNotification(new Notification("New Message", "New Message"))
                        .addAllTokens(tokens)
                        .build();
        FirebaseMessaging.getInstance().sendMulticastAsync(message);
    }
}
