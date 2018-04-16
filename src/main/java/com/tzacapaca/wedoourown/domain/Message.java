package com.tzacapaca.wedoourown.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "messages")
@Data
public class Message {
    @Id
    private String  id;

    private String body;

    private Date date;

}
