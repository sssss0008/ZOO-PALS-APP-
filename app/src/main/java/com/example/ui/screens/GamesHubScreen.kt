package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.GameType
import com.example.viewmodel.ZooViewModel

@Composable
fun GamesHubScreen(
    viewModel: ZooViewModel,
    modifier: Modifier = Modifier
) {
    val activeGame by viewModel.activeGame.collectAsState()
    val stars by viewModel.stars.collectAsState()

    // Handle Android system back button when inside a mini-game
    BackHandler(enabled = activeGame != null) {
        viewModel.exitGame()
    }

    when (activeGame) {
        GameType.SOUND_DETECTIVE -> {
            SoundDetectiveScreen(
                viewModel = viewModel,
                onBack = { viewModel.exitGame() }
            )
        }
        GameType.SPELLING_BEE -> {
            SpellingBeeScreen(
                viewModel = viewModel,
                onBack = { viewModel.exitGame() }
            )
        }
        GameType.HABITAT_MATCH -> {
            HabitatMatchScreen(
                viewModel = viewModel,
                onBack = { viewModel.exitGame() }
            )
        }
        null -> {
            // Main Games Hub Menu
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header with stars
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Play & Learn 🎮",
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 28.sp
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Play games and win Stars!",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFFFFB300),
                        shadowElevation = 2.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "$stars", fontWeight = FontWeight.Black, color = Color.White, fontSize = 16.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Game 1: Sound Detective
                GameSelectorCard(
                    title = "👂 Sound Detective",
                    subtitle = "Listen to animal sounds & guess who made them!",
                    reward = "+3 Stars per win ⭐",
                    bgHex = 0xFFFFF8E1,
                    accentHex = 0xFFFFB300,
                    icon = "🦁",
                    onClick = { viewModel.openGame(GameType.SOUND_DETECTIVE) },
                    testTag = "game_card_sound_detective"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Game 2: Letter Spelling
                GameSelectorCard(
                    title = "🔤 Letter Spelling Bee",
                    subtitle = "Tap bubbly letters to spell animal names!",
                    reward = "+5 Stars per win ⭐",
                    bgHex = 0xFFFCE4EC,
                    accentHex = 0xFFEC407A,
                    icon = "🦆",
                    onClick = { viewModel.openGame(GameType.SPELLING_BEE) },
                    testTag = "game_card_spelling_bee"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Game 3: Habitat Match
                GameSelectorCard(
                    title = "🏡 Home Matcher",
                    subtitle = "Find where safari, ocean, and farm pals live!",
                    reward = "+3 Stars per win ⭐",
                    bgHex = 0xFFE0F2F1,
                    accentHex = 0xFF00897B,
                    icon = "🐬",
                    onClick = { viewModel.openGame(GameType.HABITAT_MATCH) },
                    testTag = "game_card_habitat_match"
                )

                Spacer(modifier = Modifier.height(72.dp))
            }
        }
    }
}

@Composable
private fun GameSelectorCard(
    title: String,
    subtitle: String,
    reward: String,
    bgHex: Long,
    accentHex: Long,
    icon: String,
    onClick: () -> Unit,
    testTag: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag(testTag),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color(bgHex)),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(text = icon, fontSize = 42.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    color = Color(accentHex),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = reward,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Black,
                        fontSize = 19.sp
                    ),
                    color = Color(0xFF2C241E)
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}
