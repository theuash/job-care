package com.example.backend.model;

public record Worker(
    String id,
    String name,
    String skill,
    String location,
    String wageExpected,
    String available,
    boolean verified,
    int matchScore
) {}
