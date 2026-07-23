package com.jobcare.voice.data.model

data class DashboardStats(
    val activeEmployers: Int,
    val totalCalls: Int,
    val confirmedPlacements: Int,
    val employerRetention: Int
)

data class Worker(
    val id: String,
    val name: String,
    val skill: String,
    val location: String,
    val wageExpected: String,
    val available: String,
    val verified: Boolean,
    val matchScore: Int
)

data class JobPosting(
    val id: String,
    val title: String,
    val company: String,
    val wage: String,
    val location: String,
    val requiredSkills: String,
    val status: String,
    val date: String
)

data class CallVolume(
    val week: Int,
    val calls: Int
)

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String, val employerName: String)
