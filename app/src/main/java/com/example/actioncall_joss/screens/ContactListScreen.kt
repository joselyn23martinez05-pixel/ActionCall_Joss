package com.example.actioncall_joss.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

import com.example.actioncall_joss.data.listaContactos
import com.example.actioncall_joss.model.Contacto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    onContactClick: (Contacto) -> Unit
) {

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text("Mis Contactos")
                }
            )
        }

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {

            items(listaContactos) { contacto ->

                ListItem(

                    modifier = Modifier.clickable {
                        onContactClick(contacto)
                    },

                    headlineContent = {
                        Text(contacto.nombre)
                    },

                    supportingContent = {
                        Text(contacto.telefono)
                    },

                    leadingContent = {

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(
                                    MaterialTheme.colorScheme.primaryContainer
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = contacto.nombre.first().toString()
                            )

                        }
                    },

                    trailingContent = {

                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = "Ver detalle"
                        )

                    }

                )

                HorizontalDivider()

            }

        }

    }

}