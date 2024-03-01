package com.credentialsservice.credentialsservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Document
public class User {
    private String _id;
    private String username;
    private String password;
}
