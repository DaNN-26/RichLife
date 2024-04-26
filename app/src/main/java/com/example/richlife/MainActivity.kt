package com.example.richlife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Money
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.richlife.data.Tips
import com.example.richlife.model.Tip
import com.example.richlife.ui.theme.RichLifeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RichLifeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RichLifeApp()
                }
            }
        }
    }
}

@Composable
fun RichLifeApp() {
    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) {
        TipsList(
            tips = Tips.tips,
            contentPaddingValues = it
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    imageVector = Icons.Filled.MonetizationOn,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    colorFilter = if(!isSystemInDarkTheme())
                        ColorFilter.tint(Color.Black)
                    else
                        ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun TipsList(
    tips: List<Tip>,
    contentPaddingValues: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = contentPaddingValues,
        modifier = modifier
    ) {
        items(tips) {
            TipCard(
                tip = it,
                modifier = Modifier.padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                    )
            )
        }
    }
}

@Composable
fun TipCard(
    tip: Tip,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(true) }
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = tip.title),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                        .padding(top = 12.dp)
                )
                TipButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
            }
            Image(
                painterResource(id = tip.image),
                contentDescription = null,
                modifier = Modifier
                    .padding(
                        horizontal = 24.dp,
                        vertical = 14.dp
                    )
                    .clip(MaterialTheme.shapes.small)
                    .aspectRatio(2.5f),
                contentScale = ContentScale.FillWidth,
            )
            if(!expanded) {
                Text(
                    text = stringResource(id = R.string.tip_of_day),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(id = tip.description),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun TipButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if(!expanded)
                Icons.Filled.ExpandLess
            else
                Icons.Filled.ExpandMore,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun TipsPreview() {
    RichLifeTheme {
        RichLifeApp()
    }
}
@Preview
@Composable
fun TopAppBarPreview() {
    RichLifeTheme {
        TopAppBar()
    }
}