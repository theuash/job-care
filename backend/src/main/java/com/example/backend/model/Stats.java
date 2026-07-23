package com.example.backend.model;

public record Stats(
    int activeEmployers,
    int totalCalls,
    int confirmedPlacements,
    int employerRetention
) {}
