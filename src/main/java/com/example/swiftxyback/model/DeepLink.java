package com.example.swiftxyback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "deeplink")
public class DeepLink {
    @Id
    UUID id;

    @Column
    private String scheme;

    @Column
    private String content;
}
