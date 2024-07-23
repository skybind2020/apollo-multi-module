package com.example.accounts

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rocketreserver.LaunchListQuery

@Composable
fun LaunchList2(onLaunchClick: (launchId: String) -> Unit) {
    var launchList by remember { mutableStateOf(emptyList<LaunchListQuery.Launch>()) }
    LaunchedEffect(Unit) {
        val response = apolloClient.query(LaunchListQuery()).execute()
        launchList = response.data?.launches?.launches?.filterNotNull() ?: emptyList()
    }

    LazyColumn {
        items(launchList){ launch ->
            LaunchItem2(launch = launch, onClick = onLaunchClick)
        }

    }

}

@Composable
private fun LaunchItem2(launch: LaunchListQuery.Launch, onClick: (launchId: String) -> Unit) {


    ListItem(
        modifier = Modifier.clickable { onClick(launch.id) },
        headlineContent = {
            // Mission name
            Text(text = "Launch ${launch.id}")
        },
        supportingContent = {
            // Site
            Text(text = "Site...")
        },
        leadingContent = {
            // Mission patch
            Image(
                modifier = Modifier.size(68.dp, 68.dp),
                painter = painterResource(R.drawable.ic_placeholder_2),
                contentDescription = "Mission patch"
            )
        }
    )
}

@Composable
private fun LoadingItem() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        CircularProgressIndicator()
    }
}