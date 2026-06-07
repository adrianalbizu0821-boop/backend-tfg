package com.tfg.backend.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.tfg.backend.entity.DeviceToken;
import com.tfg.backend.repository.TokenRepository;
import org.springframework.stereotype.Service;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MessagingErrorCode;

import java.util.List;

@Service
public class FcmService {

    private final TokenRepository tokenRepository;

    public FcmService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String sendToToken(
            String token,
            String title,
            String body,
            String newsId) throws Exception {

        Message message = Message.builder()
                .setToken(token)
                .setNotification(
                        Notification.builder()
                                .setTitle(title)
                                .setBody(body)
                                .build()
                )
                .putData("newsId", newsId)
                .build();

        return FirebaseMessaging.getInstance()
                .send(message);
    }

    public void sendToAll(
            String title,
            String body,
            String newsId) {

        List<DeviceToken> tokens =
                tokenRepository.findAll();

        for (DeviceToken deviceToken : tokens) {

            try {

                Message message = Message.builder()
                        .setToken(deviceToken.getToken())
                        .setNotification(
                                Notification.builder()
                                        .setTitle(title)
                                        .setBody(body)
                                        .build()
                        )
                        .putData("newsId", newsId)
                        .build();

                FirebaseMessaging.getInstance()
                        .send(message);

            } catch (FirebaseMessagingException e) {

                System.out.println(
                        "Error enviando a token: "
                                + deviceToken.getToken());

                if (e.getMessagingErrorCode()
                        == MessagingErrorCode.UNREGISTERED) {

                    System.out.println(
                            "Token inválido eliminado: "
                                    + deviceToken.getToken());

                    tokenRepository.delete(deviceToken);
                }

                e.printStackTrace();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}