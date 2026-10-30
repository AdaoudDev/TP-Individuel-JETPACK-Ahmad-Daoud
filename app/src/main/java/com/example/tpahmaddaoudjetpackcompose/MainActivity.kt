package com.example.tpahmaddaoudjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpahmaddaoudjetpackcompose.ui.theme.TPAhmadDAOUDJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TPAhmadDAOUDJetpackComposeTheme {
                ProfileScreen(
                    name = "Ahmad Daoud",
                    role = "Développeur Android junior",
                    description = "Etudiant en M1 à l'ESGI, je développe des applis Android avec Kotlin et Jetpack Compose.",
                    skills = listOf("Kotlin", "Jetpack Compose", "Git"),
                    availability = "Disponible pour une alternance",
                    onAvailabilityClick = {
                        // affichera éventuellement la dispo
                    },
                    onContactClick = {
                        // affichera éventuellement le contact
                    }
                )
            }
        }
    }
}

// Ecran principal de l'appli
@Composable
fun ProfileScreen(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    availability: String,
    onAvailabilityClick: () -> Unit,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        ProfileCard(
            name = name,
            role = role,
            description = description,
            skills = skills,
            availability = availability,
            onAvailabilityClick = onAvailabilityClick,
            onContactClick = onContactClick,
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
    skills: List<String>,
    availability: String,
    onAvailabilityClick: () -> Unit,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        // padding interne : espace entre le bord de la fiche et le contenu
        Column(modifier = Modifier.padding(16.dp)) {
            ProfileHeader(name = name, role = role)
            Text(
                text = description,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Compétences",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            // un SkillItem par compétence de la liste
            skills.forEach { skill ->
                SkillItem(skill = skill, modifier = Modifier.padding(top = 4.dp))
            }
            AvailabilityInfo(
                text = availability,
                onClick = onAvailabilityClick,
                modifier = Modifier.padding(top = 16.dp)
            )
            ContactButton(
                text = "Contacter",
                onClick = onContactClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
    }
}

// Zone cliquable pour la disponibilité
@Composable
fun AvailabilityInfo(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .semantics {
                role = Role.Button
                contentDescription = "$text, appuyer pour plus de détails"
            },
        color = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(12.dp)
        )
    }
}

// Bouton contacter, le clic est géré par le parent
@Composable
fun ContactButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

// En-tete avec le nom et le role
@Composable
fun ProfileHeader(name: String, role: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = role,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

// Une compétence
@Composable
fun SkillItem(skill: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = skill,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

// Preview 1 : profil android junior dispo
@Preview(showBackground = true, name = "Junior Android dispo")
@Composable
fun ProfileJuniorPreview() {
    TPAhmadDAOUDJetpackComposeTheme {
        ProfileScreen(
            name = "Ahmad Daoud",
            role = "Développeur Android junior",
            description = "Etudiant en M1 à l'ESGI, je développe des applis Android avec Kotlin et Jetpack Compose.",
            skills = listOf("Kotlin", "Jetpack Compose", "Git"),
            availability = "Disponible pour une alternance",
            onAvailabilityClick = {},
            onContactClick = {}
        )
    }
}

// Preview 2 : profil full-stack pas dispo, avec d'autres compétences
@Preview(showBackground = true, name = "Full-stack pas dispo")
@Composable
fun ProfileFullStackPreview() {
    TPAhmadDAOUDJetpackComposeTheme {
        ProfileScreen(
            name = "Sarah Martin",
            role = "Développeuse full-stack",
            description = "5 ans d'expérience en web et mobile, surtout sur des API et du React.",
            skills = listOf("TypeScript", "React", "Node.js", "PostgreSQL", "Docker"),
            availability = "Pas disponible, en mission jusqu'en mars 2027",
            onAvailabilityClick = {},
            onContactClick = {}
        )
    }
}
