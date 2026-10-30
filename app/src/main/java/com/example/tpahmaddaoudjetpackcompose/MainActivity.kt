package com.example.tpahmaddaoudjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpahmaddaoudjetpackcompose.ui.theme.TPAhmadDAOUDJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TPAhmadDAOUDJetpackComposeTheme {
                ProfileScreen()
            }
        }
    }
}

// Ecran principal de l'appli
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        ProfileCard(
            name = "Ahmad Daoud",
            role = "Développeur Android junior",
            description = "Etudiant en M1 à l'ESGI, je développe des applis Android avec Kotlin et Jetpack Compose.",
            // padding externe : c'est l'ecran qui place la fiche
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        )
    }
}

// Fiche du profil
@Composable
fun ProfileCard(
    name: String,
    role: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        // padding interne : espace entre le bord de la fiche et le contenu
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = name)
            Text(text = role)
            Text(text = description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    TPAhmadDAOUDJetpackComposeTheme {
        ProfileScreen()
    }
}
