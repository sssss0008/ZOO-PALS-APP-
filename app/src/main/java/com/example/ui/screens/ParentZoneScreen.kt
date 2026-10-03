package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viewmodel.ZooViewModel
import kotlin.random.Random

@Composable
fun ParentZoneScreen(
    viewModel: ZooViewModel,
    modifier: Modifier = Modifier
) {
    val isSlowSpeech by viewModel.isSlowSpeech.collectAsState()
    val isSoundEnabled by viewModel.isSoundEnabled.collectAsState()
    val stars by viewModel.stars.collectAsState()
    val unlockedStickers by viewModel.unlockedStickers.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    var showGateDialog by remember { mutableStateOf(false) }
    var gateAction by remember { mutableStateOf<(() -> Unit)?>(null) }

    // Math challenge numbers
    var num1 by remember { mutableIntStateOf(4) }
    var num2 by remember { mutableIntStateOf(3) }
    var answerInput by remember { mutableStateOf("") }
    var gateError by remember { mutableStateOf(false) }

    fun requestParentAction(action: () -> Unit) {
        num1 = Random.nextInt(3, 9)
        num2 = Random.nextInt(2, 8)
        answerInput = ""
        gateError = false
        gateAction = action
        showGateDialog = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8EAF6)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🛡️", fontSize = 28.sp)
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    text = "Parents & Settings",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Black,
                        fontSize = 26.sp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Controls & Learning Progress",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Progress Overview Card
        Card(
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "📊 Learning Achievements",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2C241E)
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    ProgressStatItem(icon = "⭐", value = "$stars", label = "Stars Earned")
                    ProgressStatItem(icon = "🌟", value = "${unlockedStickers.size}", label = "Stickers")
                    ProgressStatItem(icon = "❤️", value = "${favorites.size}", label = "Favorite Pals")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Audio & Speech Settings Card
        Card(
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "🗣️ Voice & Sound Settings",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2C241E)
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Slow Speech Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Toddler Slower Voice (🐢)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "Speaks slower and clearer for early language learners",
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                        )
                    }
                    Switch(
                        checked = isSlowSpeech,
                        onCheckedChange = { viewModel.setSlowSpeech(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sound & TTS Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Audio & Animal Sounds",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "Enable animal roars, name speech, and sound effects",
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                        )
                    }
                    Switch(
                        checked = isSoundEnabled,
                        onCheckedChange = { viewModel.toggleSound() },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Educational Benefits Card
        Card(
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "🌱 Educational Curriculum",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "• Animal Recognition & Habitats (Savanna, Ocean, Farm, Forest)\n• Phonics & Spelling (Letter bubble association)\n• Biology Basics (Diet classifications & Baby animal names)\n• Positive encouragement & sticker reward incentives",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF1B5E20),
                        lineHeight = 20.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Reset Data Protected by Parental Gate
        OutlinedButton(
            onClick = {
                requestParentAction {
                    viewModel.resetProgress()
                }
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("reset_progress_button")
        ) {
            Icon(Icons.Default.Lock, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Reset App Progress (Parent Only)", color = Color.Gray, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(72.dp))
    }

    // Parental Gate Math Dialog
    if (showGateDialog) {
        val expected = num1 + num2
        AlertDialog(
            onDismissRequest = { showGateDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Parental Gate", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column {
                    Text("Please solve this math question to continue:")
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "$num1 + $num2 = ?",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = answerInput,
                        onValueChange = {
                            answerInput = it
                            gateError = false
                        },
                        placeholder = { Text("Answer") },
                        singleLine = true,
                        isError = gateError,
                        shape = RoundedCornerShape(14.dp)
                    )
                    if (gateError) {
                        Text(
                            text = "Incorrect answer, please try again.",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (answerInput.trim() == expected.toString()) {
                            showGateDialog = false
                            gateAction?.invoke()
                        } else {
                            gateError = true
                        }
                    },
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showGateDialog = false },
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun ProgressStatItem(icon: String, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = icon, fontSize = 28.sp)
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Black,
                color = Color(0xFF2C241E)
            )
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
        )
    }
}
