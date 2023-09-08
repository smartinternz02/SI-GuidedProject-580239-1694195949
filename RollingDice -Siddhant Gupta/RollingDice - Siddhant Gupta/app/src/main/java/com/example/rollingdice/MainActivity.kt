package com.example.rollingdice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rollingdice.ui.theme.RollingDiceTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.reflect.KProperty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollApp()
        }
    }
}

@Composable
fun DiceRollApp() {
    DiceRollScreen()
}

@Composable
fun DiceRollScreen() {
    var diceImage by remember { mutableStateOf(R.drawable.dice1) }
    var rolling by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = diceImage),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (!rolling) {
            Button(
                onClick = {
                    rolling = true
                    coroutineScope.launch {
                        val result = rollDice()
                        diceImage = when (result) {
                            1 -> R.drawable.dice1
                            2 -> R.drawable.dice2
                            3 -> R.drawable.dice3
                            4 -> R.drawable.dice4
                            5 -> R.drawable.dice5
                            else -> R.drawable.dice6
                        }
                        rolling = false
                    }
                }
            ) {
                Text("Roll Dice")
            }
        } else {
            Text("Rolling...")
        }
    }
}

private suspend fun rollDice(): Int {
    // Simulate dice rolling by delaying for a while (you can adjust the delay time)
    delay(1000L)
    return Random.nextInt(1, 7) // Generate a random number between 1 and 6
}



