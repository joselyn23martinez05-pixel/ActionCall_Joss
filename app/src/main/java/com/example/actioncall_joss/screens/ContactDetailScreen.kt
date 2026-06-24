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
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext



import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    navController: NavController,
    nombre: String,
    telefono: String,
    fotoRes: Int
) {
    val context = LocalContext.current
    val callPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->

            if (isGranted) {

                val intent = Intent(
                    Intent.ACTION_CALL,
                    Uri.parse("tel:$telefono")
                )
                context.startActivity(intent)

            } else {

                Toast.makeText(
                    context,
                    "Permiso denegado. No se puede realizar la llamada.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
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
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:$telefono")
                    context.startActivity(intent)

                }
            ) {
                Text("Abrir marcador")
            }

            // Placeholder para ACTION_CALL
            Button(
                onClick = {
                    val permissionCheck =
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CALL_PHONE
                        )

                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

                        // YA TIENE PERMISO → LLAMADA DIRECTA
                        val intent = Intent(
                            Intent.ACTION_CALL,
                            Uri.parse("tel:$telefono")
                        )
                        context.startActivity(intent)

                    } else {

                        // NO TIENE PERMISO → SOLICITARLO
                        callPermissionLauncher.launch(
                            Manifest.permission.CALL_PHONE
                        )
                    }
                }
            ) {
                Text("Llamar directo")
            }

        }

    }

}