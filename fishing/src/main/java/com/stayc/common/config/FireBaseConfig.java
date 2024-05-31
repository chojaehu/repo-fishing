package com.stayc.common.config;

import java.io.FileInputStream;
import java.io.IOException;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import jakarta.annotation.PostConstruct;

@Configuration
public class FireBaseConfig {
	
	 private static Firestore firestore;
	

	    @PostConstruct
	    public void initialize() {
	        try {
	            if (FirebaseApp.getApps().isEmpty()) {
	                FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase/fishing-9e7dc-firebase-adminsdk-jc98j-b5455f934e.json");

	                FirebaseOptions options = FirebaseOptions.builder()
	                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
	                    .build();

	                FirebaseApp.initializeApp(options);
	                System.out.println("Firebase has been initialized successfully.");
	            } else {
	                System.out.println("FirebaseApp already initialized.");
	            }

	            if (firestore == null) {
	                firestore = FirestoreClient.getFirestore();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.err.println("Failed to initialize Firebase: " + e.getMessage());
	        }
	    }

	    @Bean
	    public Firestore getFirestore() {
	        return firestore;
	    }
	    
	    
	    
}
