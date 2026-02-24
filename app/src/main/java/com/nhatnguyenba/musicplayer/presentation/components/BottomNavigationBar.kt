package com.nhatnguyenba.musicplayer.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavigationBar(
    selectedItem: Int = 0,
    onItemSelected: (Int) -> Unit = {}
) {
    val items = listOf(
        NavigationItem("Home", Icons.Default.Home),
        NavigationItem("Search", Icons.Default.Search),
        NavigationItem("Library", Icons.Default.LibraryMusic),
        NavigationItem("Profile", Icons.Default.Person)
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { onItemSelected(index) },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp
                    )
                },
                alwaysShowLabel = true // Hiển thị nhãn khi được chọn
            )
        }
    }
}

data class NavigationItem(val title: String, val icon: ImageVector)