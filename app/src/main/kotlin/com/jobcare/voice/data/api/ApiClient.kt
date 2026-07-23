package com.jobcare.voice.data.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jobcare.voice.data.model.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:8080"
    private val gson = Gson()
    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private inline fun <reified T> parseList(json: String): List<T> =
        gson.fromJson(json, object : TypeToken<List<T>>() {}.type)

    suspend fun login(email: String, password: String): Result<LoginResponse> =
        post("/api/login", LoginRequest(email, password))

    suspend fun getStats(): Result<DashboardStats> = get("/api/stats")

    suspend fun getWorkers(): Result<List<Worker>> = getList("/api/workers")

    suspend fun getWorker(id: String): Result<Worker> = get("/api/workers/$id")

    suspend fun getJobs(): Result<List<JobPosting>> = getList("/api/jobs")

    suspend fun createJob(job: JobPosting): Result<JobPosting> = post("/api/jobs", job)

    suspend fun getCallVolumes(): Result<List<CallVolume>> = getList("/api/analytics/calls")

    private suspend inline fun <reified T> get(path: String): Result<T> {
        return try {
            val request = Request.Builder().url("$BASE_URL$path").get().build()
            val response = client.newCall(request).execute()
            val body = response.body?.string() ?: ""
            if (response.isSuccessful) {
                Result.success(gson.fromJson(body, T::class.java))
            } else {
                Result.failure(Exception("HTTP ${response.code}: $body"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend inline fun <reified T> getList(path: String): Result<List<T>> {
        return try {
            val request = Request.Builder().url("$BASE_URL$path").get().build()
            val response = client.newCall(request).execute()
            val body = response.body?.string() ?: ""
            if (response.isSuccessful) {
                Result.success(parseList(body))
            } else {
                Result.failure(Exception("HTTP ${response.code}: $body"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend inline fun <reified T, reified R> post(path: String, body: T): Result<R> {
        return try {
            val json = gson.toJson(body)
            val mediaType = "application/json".toMediaType()
            val request = Request.Builder()
                .url("$BASE_URL$path")
                .post(json.toRequestBody(mediaType))
                .build()
            val response = client.newCall(request).execute()
            val respBody = response.body?.string() ?: ""
            if (response.isSuccessful) {
                Result.success(gson.fromJson(respBody, R::class.java))
            } else {
                Result.failure(Exception("HTTP ${response.code}: $respBody"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
