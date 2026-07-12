package com.frostre1997.materialistic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.frostre1997.materialistic.ui.MaterialisticTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialisticTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    DesktopScreen()
                }
            }
        }
    }
}

@Composable
fun DesktopScreen() {
    // TODO: We will build the desktop/launcher UI here in later steps
    // For now, just a simple text to confirm it works
    androidx.compose.material3.Text(
        text = "Materialistic Launcher (Work in Progress)"
    )
}
