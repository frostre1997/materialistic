package com.frostre1997.materialistic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frostre1997.materialistic.ui.MaterialisticTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialisticTheme {
                MaterialSurface {
                    DesktopScreen()
                }
            }
        }
    }
}

@Composable
fun MaterialSurface(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        content()
    }
}

@Composable
fun DesktopScreen() {
    val currentTime = rememberCurrentTime()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top bar: big clock
        TopBar(
            timeText = currentTime,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Main area: app grid
        AppGrid(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom dock
        Dock(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TopBar(timeText: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .height(64.dp)
            .clip(RoundedCornerShape(16.dp)),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = timeText,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

data class AppItem(
    val name: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun AppGrid(modifier: Modifier = Modifier) {
    val apps = listOf(
        AppItem("Browser", Icons.Filled.Language),
        AppItem("Messages", Icons.Filled.Message),
        AppItem("Photos", Icons.Filled.PhotoLibrary),
        AppItem("Music", Icons.Filled.MusicNote),
        AppItem("Maps", Icons.Filled.Map),
        AppItem("Settings", Icons.Filled.Settings),
    )

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp)),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 80.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(apps) { app ->
                AppIcon(app)
            }
        }
    }
}

@Composable
fun AppIcon(app: AppItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = app.icon,
                contentDescription = app.name,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = app.name,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1
        )
    }
}

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

@Composable
private fun rememberCurrentTime(): String {
    var timeText by androidx.compose.runtime.remember {
        mutableStateOf(formatTime())
    }

    val ticker = androidx.compose.runtime.remember {
        androidx.compose.runtime.snapshotFlow { System.currentTimeMillis() }
    }

    androidx.compose.runtime.LaunchedEffect(ticker) {
        while (true) {
            kotlinx.coroutines.delay(1000L)
            timeText = formatTime()
        }
    }

    return timeText
}

private fun formatTime(): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(Date())
}
