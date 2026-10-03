package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viewmodel.ZooViewModel

@Composable
fun SpellingBeeScreen(
    viewModel: ZooViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animal by viewModel.spellingAnimal.collectAsState()
    val scrambledBubbles by viewModel.scrambledBubbles.collectAsState()
    val placedBubbles by viewModel.placedBubbles.collectAsState()
    val isSuccess by viewModel.spellingSuccess.collectAsState()
    val stars by viewModel.stars.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Navigation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .testTag("spelling_back_button")
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF2C241E))
            }

            Text(
                text = "Letter Spelling 🔤",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Black
                ),
                color = MaterialTheme.colorScheme.primary
            )

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFFFFB300),
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$stars", fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        animal?.let { currentAnimal ->
            // Animal Display Card
            Card(
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Color(currentAnimal.bgLightHex)),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.speakText(currentAnimal.name) }
                    .testTag("spelling_animal_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(76.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = currentAnimal.emoji, fontSize = 46.sp)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Can you spell?",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Text(
                            text = if (isSuccess) currentAnimal.name else "???",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = Color(currentAnimal.accentColorHex)
                            )
                        )
                    }

                    IconButton(
                        onClick = { viewModel.playAnimalName(currentAnimal) },
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(Color(currentAnimal.accentColorHex))
                            .testTag("spelling_speak_button")
                    ) {
                        Icon(Icons.Default.VolumeUp, contentDescription = "Hear name", tint = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(26.dp))

            // Letter Target Slots
            val targetLength = currentAnimal.name.length
            Text(
                text = "Tap letters to spell the word:",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0 until targetLength) {
                    val placed = placedBubbles.getOrNull(i)
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(54.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                if (placed != null) Color(0xFFFFE082) else Color.White
                            )
                            .border(
                                width = 2.dp,
                                color = if (isSuccess) Color(0xFF4CAF50) else Color(0xFFFFB300),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable(enabled = placed != null && !isSuccess) {
                                viewModel.removePlacedLetter(i)
                            }
                            .testTag("letter_slot_$i"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = placed?.char?.toString() ?: "",
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 26.sp,
                                color = Color(0xFF3E2723)
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Scrambled Letter Bubbles
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                scrambledBubbles.forEach { bubble ->
                    Surface(
                        shape = CircleShape,
                        color = if (bubble.isUsed) Color(0xFFE0E0E0) else Color(0xFFFF7043),
                        shadowElevation = if (bubble.isUsed) 0.dp else 4.dp,
                        modifier = Modifier
                            .padding(6.dp)
                            .size(56.dp)
                            .clickable(enabled = !bubble.isUsed && !isSuccess) {
                                viewModel.tapLetterBubble(bubble)
                            }
                            .testTag("letter_bubble_${bubble.char}_${bubble.id}")
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = bubble.char.toString(),
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Black,
                                    fontSize = 24.sp,
                                    color = if (bubble.isUsed) Color.Gray else Color.White
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Clear Button
            if (placedBubbles.isNotEmpty() && !isSuccess) {
                OutlinedButton(
                    onClick = { viewModel.resetSpellingLetters() },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.DarkGray)
                ) {
                    Text("Clear Letters ↺", fontWeight = FontWeight.SemiBold)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Success Card & Next Animal Button
            if (isSuccess) {
                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "🎉 Awesome Spelling! +5 Stars! ⭐",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Black,
                                color = Color(0xFF2E7D32)
                            ),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = { viewModel.nextSpellingBee() },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("spelling_next_button")
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = null, tint = Color.White)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Spell Another Animal! 🔤", fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
