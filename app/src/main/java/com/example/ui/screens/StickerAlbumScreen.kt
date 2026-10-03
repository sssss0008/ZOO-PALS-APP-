package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AnimalData
import com.example.model.Animal
import com.example.viewmodel.ZooViewModel

enum class StickerTheme(val displayName: String, val icon: String, val brush: Brush) {
    SAVANNA(
        "Savanna",
        "🌾",
        Brush.verticalGradient(listOf(Color(0xFFFFECB3), Color(0xFFFFD54F), Color(0xFFC8E6C9)))
    ),
    OCEAN(
        "Ocean",
        "🌊",
        Brush.verticalGradient(listOf(Color(0xFFB3E5FC), Color(0xFF4FC3F7), Color(0xFF0288D1)))
    ),
    FARM(
        "Farm",
        "🚜",
        Brush.verticalGradient(listOf(Color(0xFFE1F5FE), Color(0xFFC8E6C9), Color(0xFFA5D6A7)))
    ),
    FOREST(
        "Forest",
        "🌲",
        Brush.verticalGradient(listOf(Color(0xFFD7CCC8), Color(0xFFA1887F), Color(0xFF689F38)))
    )
}

@Composable
fun StickerAlbumScreen(
    viewModel: ZooViewModel,
    modifier: Modifier = Modifier
) {
    val unlockedStickers by viewModel.unlockedStickers.collectAsState()
    val stars by viewModel.stars.collectAsState()

    var selectedTheme by remember { mutableStateOf(StickerTheme.SAVANNA) }
    val placedStickers = remember { mutableStateListOf<Animal>() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Sticker Album 🌟",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Black,
                        fontSize = 26.sp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Unlocked: ${unlockedStickers.size}/${AnimalData.animals.size} stickers",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }

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

        Spacer(modifier = Modifier.height(10.dp))

        // Background theme picker
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(StickerTheme.values()) { theme ->
                val isSelected = selectedTheme == theme
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
                    shadowElevation = 1.dp,
                    modifier = Modifier
                        .clickable { selectedTheme = theme }
                        .testTag("theme_chip_${theme.name}")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = theme.icon, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = theme.displayName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = if (isSelected) Color.White else Color(0xFF2C241E)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Interactive Play Scene (Canvas for placed stickers)
        Card(
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .testTag("sticker_scene")
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(selectedTheme.brush)
                    .padding(14.dp)
            ) {
                if (placedStickers.isEmpty()) {
                    Text(
                        text = "Tap any unlocked sticker below to add to your ${selectedTheme.displayName}! 🎨",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF3E2723)
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    // Placed Stickers Flow
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(placedStickers) { stickerAnimal ->
                            Box(
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.9f))
                                    .clickable {
                                        viewModel.playAnimalSound(stickerAnimal)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = stickerAnimal.emoji, fontSize = 44.sp)
                            }
                        }
                    }

                    OutlinedButton(
                        onClick = { placedStickers.clear() },
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .height(34.dp)
                    ) {
                        Text("Clear Scene", fontSize = 11.sp, color = Color(0xFF3E2723))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Your Animal Stickers:",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Sticker Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 72.dp),
            modifier = Modifier.fillMaxSize().testTag("sticker_grid")
        ) {
            items(AnimalData.animals, key = { it.id }) { animal ->
                val isUnlocked = unlockedStickers.contains(animal.id)

                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isUnlocked) Color(animal.bgLightHex) else Color(0xFFEEEEEE)
                    ),
                    modifier = Modifier
                        .clickable {
                            if (isUnlocked) {
                                placedStickers.add(animal)
                                viewModel.playAnimalName(animal)
                            } else {
                                viewModel.unlockStickerWithStars(animal.id, cost = 5)
                            }
                        }
                        .testTag("sticker_item_${animal.id}")
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (isUnlocked) {
                            Text(text = animal.emoji, fontSize = 34.sp)
                            Text(
                                text = animal.name,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2C241E),
                                maxLines = 1
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Locked Sticker",
                                tint = Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Color(0xFFFFB300),
                                    modifier = Modifier.size(12.dp)
                                )
                                Text(
                                    text = "5",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color(0xFF5D4037)
                                )
                            }
                            Text(
                                text = "Unlock",
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
