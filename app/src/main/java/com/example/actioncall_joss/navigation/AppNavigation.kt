package com.example.actioncall_joss.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.actioncall_joss.screens.ContactDetailScreen
import com.example.actioncall_joss.screens.ContactListScreen

// RUTAS DE NAVEGACIÓN
object Routes {

    const val LISTA = "lista"
    const val DETALLE = "detalle/{nombre}/{telefono}/{fotoRes}"
}


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LISTA
    ) {

        composable(Routes.LISTA) {
            ContactListScreen(onContactClick = { contacto ->

                    val nombreCodificado = Uri.encode(contacto.nombre)
                    val telefonoCodificado = Uri.encode(contacto.telefono)

                    navController.navigate(
                        "detalle/$nombreCodificado/$telefonoCodificado/${contacto.fotoRes}"
                    )
                }
            )
        }
        composable(
            route = Routes.DETALLE,
            arguments = listOf(
                navArgument("nombre") {
                    type = NavType.StringType
                },
                navArgument("telefono") {
                    type = NavType.StringType
                },
                navArgument("fotoRes") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val nombre = Uri.decode(
                backStackEntry.arguments?.getString("nombre") ?: ""
            )

            val telefono = Uri.decode(
                backStackEntry.arguments?.getString("telefono") ?: ""
            )

            val fotoRes =
                backStackEntry.arguments?.getInt("fotoRes") ?: 0

            ContactDetailScreen(
                navController = navController,
                nombre = nombre,
                telefono = telefono,
                fotoRes = fotoRes
            )
        }
    }
}