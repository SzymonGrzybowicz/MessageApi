package com.example.message.notifier;

import com.example.message.domain.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

class FirebaseNotifier implements Notifier {

    @Override
    public void notify(com.example.message.domain.Message message) {
        for (User member: message.getConversation().getMembers()) {
            sendMessage(member.getFirebaseAuthToken());
        }
    }

    public void sendMessage(String token) {
        Message message =
                Message.builder()
                        .setNotification(new Notification("New Message", "New Message"))
                        .setToken(token)
                        .build();
        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
