package com.stayc.common.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FireBaseConfig {
	
	@PostConstruct
    public void initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase/fishing-9e7dc-firebase-adminsdk-jc98j-b5455f934e.json");

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            FirebaseApp.initializeApp(options);

            
            
            System.out.println("Firebase has been initialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize Firebase: " + e.getMessage());
        }
    }

}
