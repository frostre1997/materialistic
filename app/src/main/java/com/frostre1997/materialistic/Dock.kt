package com.frostre1997.materialistic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Dock(modifier: Modifier = Modifier) {
    val dockApps = listOf(
        AppItem("Browser", Icons.Filled.Language),
        AppItem("Messages", Icons.Filled.Message),
        AppItem("Photos", Icons.Filled.PhotoLibrary),
        AppItem("Music", Icons.Filled.MusicNote),
    )

    Surface(
        modifier = modifier
            .height(88.dp)
            .clip(RoundedCornerShape(24.dp)),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            dockApps.forEach { app ->
                AppIconDock(app)
            }
        }
    }
}

@Composable
fun AppIconDock(app: AppItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = app.icon,
                contentDescription = app.name,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
