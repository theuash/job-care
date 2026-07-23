package com.jobcare.voice.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jobcare.voice.data.api.ApiClient
import com.jobcare.voice.data.model.CallVolume

@Composable
fun AnalyticsScreen(nav: NavController) {
    var callVolumes by remember { mutableStateOf<List<CallVolume>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        ApiClient.getCallVolumes().onSuccess { callVolumes = it }
        loading = false
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            Text("Analytics", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Worker call volume & performance", fontSize = 13.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
        }

        if (loading) {
            item {
                Box(modifier = Modifier.fillMaxWidth().height(300.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        } else {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Weekly Call Volume", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
                        Spacer(modifier = Modifier.height(16.dp))

                        val maxCalls = callVolumes.maxOfOrNull { it.calls } ?: 1
                        callVolumes.forEach { cv ->
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Wk ${cv.week}", fontSize = 11.sp, modifier = Modifier.width(32.dp), color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(24.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(MaterialTheme.colorScheme.surfaceVariant)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(fraction = cv.calls.toFloat() / maxCalls)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(
                                                if (cv.week <= 4) Color(0xFF6366F1).copy(alpha = 0.5f)
                                                else Color(0xFF06B6D4)
                                            )
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("${cv.calls}", fontSize = 12.sp, fontWeight = FontWeight.Medium, modifier = Modifier.width(40.dp), textAlign = TextAlign.End,
                                    color = MaterialTheme.colorScheme.onSurface)
                            }
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.06f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Key Metrics", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onBackground)
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("2,200", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6366F1))
                                Text("Total Calls", fontSize = 11.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("140", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF10B981))
                                Text("Placements", fontSize = 11.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("78%", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF06B6D4))
                                Text("Retention", fontSize = 11.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                            }
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}
