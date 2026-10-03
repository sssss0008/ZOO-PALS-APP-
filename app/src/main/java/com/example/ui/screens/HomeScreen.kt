package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.data.AnimalData
import com.example.model.AnimalCategory
import com.example.ui.components.AnimalCard
import com.example.ui.components.AnimalDetailSheet
import com.example.viewmodel.ZooViewModel

@Composable
fun HomeScreen(
    viewModel: ZooViewModel,
    modifier: Modifier = Modifier
) {
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedLetter by viewModel.selectedLetterFilter.collectAsState()
    val selectedAnimal by viewModel.selectedAnimal.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val stars by viewModel.stars.collectAsState()
    val isSlowSpeech by viewModel.isSlowSpeech.collectAsState()
    val isSoundEnabled by viewModel.isSoundEnabled.collectAsState()

    // Filtered animal list
    val animals = AnimalData.animals.filter { animal ->
        val matchesCategory = selectedCategory == AnimalCategory.ALL || animal.category == selectedCategory
        val matchesSearch = searchQuery.isBlank() || animal.name.contains(searchQuery, ignoreCase = true)
        val matchesLetter = selectedLetter == null || animal.name.startsWith(selectedLetter!!, ignoreCase = true)
        matchesCategory && matchesSearch && matchesLetter
    }

    Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize().testTag("home_animal_grid")
        ) {
            // Header: App Title, Stars & Controls
            item(span = { GridItemSpan(2) }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "ZooPals 🐾",
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 28.sp
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Learn & Play with Animal Friends!",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Star Counter Pill
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Color(0xFFFFB300),
                            shadowElevation = 2.dp,
                            modifier = Modifier.testTag("stars_indicator")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Stars earned",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "$stars",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Black
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        // Voice Speed Toggle (Turtle vs Bunny)
                        IconButton(
                            onClick = { viewModel.setSlowSpeech(!isSlowSpeech) },
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                                .testTag("voice_speed_toggle")
                        ) {
                            Text(
                                text = if (isSlowSpeech) "🐢" else "🐰",
                                fontSize = 20.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        // Sound Mute Toggle
                        IconButton(
                            onClick = { viewModel.toggleSound() },
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                                .testTag("sound_mute_toggle")
                        ) {
                            Icon(
                                imageVector = if (isSoundEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                                contentDescription = "Toggle sound",
                                tint = if (isSoundEnabled) MaterialTheme.colorScheme.primary else Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }

            // Featured "Animal of the Day" Card
            item(span = { GridItemSpan(2) }) {
                val daily = viewModel.animalOfTheDay
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.openAnimalDetail(daily) }
                        .testTag("animal_of_the_day_card"),
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(daily.bgLightHex)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = daily.emoji, fontSize = 42.sp)
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Surface(
                                color = Color(daily.accentColorHex).copy(alpha = 0.2f),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = "🌟 PAL OF THE DAY",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Black,
                                        color = Color(daily.accentColorHex)
                                    ),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = daily.name,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            )
                            Text(
                                text = "Says \"${daily.soundEffect}\" • Tap to explore!",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }

                        IconButton(
                            onClick = { viewModel.playAnimalSound(daily) },
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Color(daily.accentColorHex))
                                .testTag("daily_sound_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = "Play sound",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            // Search Bar
            item(span = { GridItemSpan(2) }) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.setSearchQuery(it) },
                    placeholder = { Text("Find animal (e.g. Lion, Cow)...", fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear search", tint = Color.Gray)
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(22.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color(0xFFE0E0E0)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("animal_search_input")
                )
            }

            // Category Chips Row
            item(span = { GridItemSpan(2) }) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 4.dp),
                    modifier = Modifier.fillMaxWidth().testTag("category_row")
                ) {
                    items(AnimalCategory.values()) { category ->
                        val isSelected = selectedCategory == category
                        Surface(
                            shape = RoundedCornerShape(18.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
                            shadowElevation = if (isSelected) 3.dp else 1.dp,
                            modifier = Modifier
                                .clickable {
                                    viewModel.selectCategory(category)
                                    viewModel.speakText(category.displayName)
                                }
                                .testTag("category_chip_${category.name}")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = category.icon, fontSize = 16.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = category.displayName,
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.White else Color(0xFF2C241E)
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // A-Z Alphabet Quick Bar
            item(span = { GridItemSpan(2) }) {
                val letters = listOf("ALL") + ('A'..'Z').map { it.toString() }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    contentPadding = PaddingValues(vertical = 2.dp),
                    modifier = Modifier.fillMaxWidth().testTag("alphabet_row")
                ) {
                    items(letters) { item ->
                        val isAll = item == "ALL"
                        val isSelected = (isAll && selectedLetter == null) ||
                                (!isAll && selectedLetter?.toString() == item)

                        Surface(
                            shape = CircleShape,
                            color = if (isSelected) Color(0xFFFF7043) else Color.White,
                            shadowElevation = 1.dp,
                            modifier = Modifier
                                .size(36.dp)
                                .clickable {
                                    if (isAll) {
                                        viewModel.setLetterFilter(null)
                                    } else {
                                        val char = item.first()
                                        viewModel.setLetterFilter(char)
                                        viewModel.speakText(item)
                                    }
                                }
                                .testTag("alphabet_chip_$item")
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = item,
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Black,
                                        fontSize = if (isAll) 10.sp else 13.sp,
                                        color = if (isSelected) Color.White else Color(0xFF2C241E)
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // Animal Count Header
            item(span = { GridItemSpan(2) }) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${animals.size} Animals Found",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF5B4F47)
                        )
                    )
                    if (searchQuery.isNotEmpty() || selectedLetter != null) {
                        Text(
                            text = "Reset Filter",
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.clickable {
                                viewModel.setSearchQuery("")
                                viewModel.setLetterFilter(null)
                            }
                        )
                    }
                }
            }

            // Animal Grid Cards
            items(animals, key = { it.id }) { animal ->
                AnimalCard(
                    animal = animal,
                    isFavorite = favorites.contains(animal.id),
                    onCardClick = { viewModel.openAnimalDetail(animal) },
                    onSoundClick = { viewModel.playAnimalSound(animal) },
                    onFavoriteClick = { viewModel.toggleFavorite(animal.id) }
                )
            }

            // Bottom Spacer for BottomNavigation
            item(span = { GridItemSpan(2) }) {
                Spacer(modifier = Modifier.height(72.dp))
            }
        }

        // Animal Detail Bottom Sheet
        selectedAnimal?.let { animal ->
            AnimalDetailSheet(
                animal = animal,
                isFavorite = favorites.contains(animal.id),
                onDismiss = { viewModel.closeAnimalDetail() },
                onPlayName = { viewModel.playAnimalName(animal) },
                onPlaySound = { viewModel.playAnimalSound(animal) },
                onPlayPhonics = { viewModel.playPhonics(animal) },
                onPlayFact = { fact -> viewModel.speakText(fact) },
                onToggleFavorite = { viewModel.toggleFavorite(animal.id) }
            )
        }
    }
}
