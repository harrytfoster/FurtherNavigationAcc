package com.example.furthernavigationacc

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.furthernavigationacc.ui.theme.FurtherNavigationAccTheme
import kotlinx.coroutines.launch
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) // Remembers burger menu open state
            val coroutineScope = rememberCoroutineScope() // More on menu open state
            ModalNavigationDrawer( // Open menu when menu pressed
                drawerState = drawerState, // Sets drawer state
                drawerContent = {
                    ModalDrawerSheet(){
                        NavigationDrawerItem( // Creates item in menu
                            selected = false, // Default selected?
                            label = { Text("Do Something") }, // Text for item in menu
                            onClick = { // Do something when clicked
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                    }
                }
            )
            {
            Scaffold( // NEED THE SCAFFOLD

                topBar = { // Creating the Top Bar
                    TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors( // Setting Colour
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ), actions = {
                        IconButton(onClick = { // Do something when burger menu clicked
                            coroutineScope.launch {
                                if(drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Menu, "Menu") // Burger menu icon
                        }
                    }, title = { Text("Top Bar Example") }) // Text on top bar
                },

                floatingActionButton = { // Create floating button
                    FloatingActionButton(
                        onClick = { /*TODO*/ }, // What the button does
                        content = {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = "Action Button") // Button icon
                        })
                },

                bottomBar = { // Create bottom bar
                    NavigationBar {// Have something to add items to
                        NavigationBarItem( // Adding item to bottom bar
                            selected = true, // Is it selected by default?
                            onClick = { /*TODO*/ }, // Do something when button clicked
                            icon = { Icon(Icons.Filled.Home, "Home")}, // Icon
                            label = {Text("Home")} // Text under icon
                        )
                        NavigationBarItem(
                            selected = false,
                            onClick = { /*TODO*/ },
                            icon = { Icon(Icons.Filled.Check, "Something Else")},
                            label = {Text("Something Else")}
                        )
                    }
                }
            )
            {innerPadding -> // Content
                Parent(modifier = Modifier.padding(innerPadding)) // Need to account for top bar in padding
            }
            }
        }
    }
}

@Composable
fun Parent(modifier: Modifier){

}

@Composable
fun Other(){
    Text("Other one")
}