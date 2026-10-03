package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppNavTab
import com.example.ui.components.ConfettiEffect
import com.example.ui.screens.GamesHubScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ParentZoneScreen
import com.example.ui.screens.StickerAlbumScreen
import com.example.ui.theme.ZooPalsTheme
import com.example.viewmodel.ZooViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ZooViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZooPalsTheme {
                ZooPalsApp(viewModel)
            }
        }
    }
}

@Composable
fun ZooPalsApp(viewModel: ZooViewModel) {
    val currentTab by viewModel.currentNavTab.collectAsState()
    val showCelebration by viewModel.showCelebration.collectAsState()
    val celebrationText by viewModel.celebrationText.collectAsState()
    val activeGame by viewModel.activeGame.collectAsState()

    // BackHandler to return to Explore tab if on another top-level tab
    BackHandler(enabled = currentTab != AppNavTab.EXPLORE && activeGame == null) {
        viewModel.setNavTab(AppNavTab.EXPLORE)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            // Only show bottom navigation when not inside a full-screen mini-game
            if (activeGame == null) {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 6.dp,
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        .testTag("main_navigation_bar")
                ) {
                    AppNavTab.values().forEach { tab ->
                        val isSelected = currentTab == tab
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                viewModel.setNavTab(tab)
                                viewModel.speakText(tab.label)
                            },
                            icon = {
                                Text(
                                    text = tab.icon,
                                    fontSize = if (isSelected) 24.sp else 20.sp
                                )
                            },
                            label = {
                                Text(
                                    text = tab.label,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    fontSize = 12.sp
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                unselectedTextColor = Color.Gray
                            ),
                            modifier = Modifier.testTag("nav_item_${tab.name.lowercase()}")
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentTab) {
                AppNavTab.EXPLORE -> HomeScreen(viewModel = viewModel)
                AppNavTab.GAMES -> GamesHubScreen(viewModel = viewModel)
                AppNavTab.STICKERS -> StickerAlbumScreen(viewModel = viewModel)
                AppNavTab.SETTINGS -> ParentZoneScreen(viewModel = viewModel)
            }

            // Confetti overlay on milestones & rewards
            if (showCelebration) {
                ConfettiEffect(message = celebrationText)
            }
        }
    }
}
