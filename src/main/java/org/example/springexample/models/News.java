package org.example.springexample.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
public class News {
    private Long id;
    protected String title;
    private String text;
    private Instant date;
}