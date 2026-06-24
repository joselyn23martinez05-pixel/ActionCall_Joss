package com.example.actioncall_joss.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    navController: NavController,
    nombre: String,
    telefono: String,
    fotoRes: Int
) {

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("Detalle del Contacto")
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            // REQUISITO:
                            // Regresar usando popBackStack()
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )

                    }
                }
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = nombre.firstOrNull()?.toString() ?: ""
                )

            }

            Text(
                text = nombre,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = telefono
            )

            // Placeholder para ACTION_DIAL
            Button(
                onClick = { }
            ) {
                Text("Abrir marcador")
            }

            // Placeholder para ACTION_CALL
            Button(
                onClick = { }
            ) {
                Text("Llamar directo")
            }

        }

    }

}