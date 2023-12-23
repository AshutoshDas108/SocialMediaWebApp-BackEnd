package com.socials.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;

    private String image;


    /* One user can create multiple message
    but one message only belongs to a
    single user
     */
    @ManyToOne
    private User user;


    /*

     */
    @ManyToOne
    @JsonIgnore //to avoid falling into recursive loop
    private Chat chat;

    private LocalDateTime timeStamp;


}
