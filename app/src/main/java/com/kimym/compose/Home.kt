package com.kimym.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun Home() {
    val color = remember { SampleData.getColor() }
    val colors = remember { SampleData.getColors() }
    MaterialTheme {
        Scaffold(
            topBar = { AppBar() }
        ) { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                item {
                    Header(text = "Is this your favorite rainbow color?")
                }
                item {
                    RainbowRandomEntity(
                        color = color,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                item {
                    Header(text = stringResource(id = R.string.rainbow))
                }
                items(colors) { color ->
                    RainbowItem(color = color)
                    Divider(startIndent = 72.dp)
                }
            }
        }
    }
}

@Composable
private fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.Palette,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        },
        title = {
            Text(text = stringResource(id = R.string.rainbow))
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Composable
private fun Header(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .semantics { heading() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
private fun RainbowRandomEntity(
    color: RainbowEntity,
    modifier: Modifier = Modifier,
) {
    Card(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* onClick */ },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = color.drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            val padding = Modifier.padding(horizontal = 16.dp)
            Text(
                text = stringResource(id = color.color),
                modifier = padding
            )
            RainbowColorCodeData(color, padding)
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun RainbowColorCodeData(
    color: RainbowEntity,
    modifier: Modifier = Modifier,
) {
    val text = buildAnnotatedString {
        append("#")
        append(stringResource(id = color.code).uppercase(Locale.getDefault()))
    }
    Text(
        text = text,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun RainbowItem(
    color: RainbowEntity,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .clickable { /* TODO */ }
            .padding(vertical = 8.dp),
        icon = {
            Image(
                painter = painterResource(color.drawable),
                contentDescription = null,
                modifier = Modifier
                    .height(56.dp)
                    .width(56.dp)
            )
        },
        text = {
            Text(text = stringResource(color.color))
        },
        secondaryText = {
            RainbowColorCodeData(color = color)
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun RainbowRandomPreview() {
    val color = remember { SampleData.getColor() }
    Surface {
        RainbowRandomEntity(color = color)
    }
}

@Preview(showBackground = true)
@Composable
private fun RainbowItemPreview() {
    val color = remember { SampleData.getColor() }
    Surface {
        RainbowItem(color = color)
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    Home()
}
