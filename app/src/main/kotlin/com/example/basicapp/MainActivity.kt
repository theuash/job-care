package com.example.basicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basicapp.ui.theme.BasicAppTheme
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

data class HelloResponse(val message: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { BasicAppScreen() }
    }
}

@Composable
fun BasicAppScreen() {
    var responseText by remember { mutableStateOf("Press button to call backend") }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val client = remember { OkHttpClient() }
    val gson = remember { Gson() }

    BasicAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Basic App + Spring Boot",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Card(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = responseText, fontSize = 18.sp, textAlign = TextAlign.Center)

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                loading = true
                                scope.launch {
                                    try {
                                        val request = Request.Builder()
                                            .url("http://10.0.2.2:8080/api/hello")
                                            .build()
                                        val httpResponse = client.newCall(request).execute()
                                        val body = httpResponse.body?.string() ?: ""
                                        val hello = gson.fromJson(body, HelloResponse::class.java)
                                        responseText = hello.message
                                    } catch (e: Exception) {
                                        responseText = "Error: ${e.message}"
                                    } finally {
                                        loading = false
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !loading
                        ) {
                            Text(text = if (loading) "Calling..." else "Call Backend", fontSize = 18.sp)
                        }
                    }
                }

                Text(
                    text = "Note: Backend must run on http://10.0.2.2:8080 (Android emulator localhost)",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}