package com.example.backend.model;

public record JobPosting(
    String id,
    String title,
    String company,
    String wage,
    String location,
    String requiredSkills,
    String status,
    String date
) {}
