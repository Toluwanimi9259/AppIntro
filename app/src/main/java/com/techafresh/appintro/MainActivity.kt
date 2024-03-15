package com.techafresh.appintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canopas.lib.showcase.IntroShowcase
import com.canopas.lib.showcase.IntroShowcaseScope
import com.canopas.lib.showcase.component.IntroShowcaseState
import com.canopas.lib.showcase.component.ShowcaseStyle
import com.canopas.lib.showcase.component.rememberIntroShowcaseState
import com.techafresh.appintro.ui.theme.AppIntroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppIntroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(modifier: Modifier = Modifier) {

    var showAppIntro by remember {
        mutableStateOf(true)
    }

    val introShowcaseState = rememberIntroShowcaseState()

    IntroShowcase(
        showIntroShowCase = showAppIntro,
        dismissOnClickOutside = false,
        onShowCaseCompleted = {
            showAppIntro = false
        },
        state = introShowcaseState,
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "AppIntroExample") },
                    navigationIcon = {
                         BackButton(introShowcaseState = introShowcaseState)
                    },
                    actions = {
                        Search()
                    }
                )
            },
            floatingActionButton = {
                Fab()
            }
        ) {
            Surface(
                Modifier
                    .padding(it)
                    .fillMaxSize())
            {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "App Content")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppIntroTheme {
        AppContent()
    }
}

@Preview
@Composable
fun IntroShowcaseScope.Fab(){
    FloatingActionButton(
        onClick = {},
        modifier = Modifier.introShowCaseTarget(
            index = 0, // specify index to show feature in order
            style = ShowcaseStyle.Default.copy(
                backgroundColor = Color(0xFF1C0A00), // specify color of background
                backgroundAlpha = 0.98f, // specify transparency of background
                targetCircleColor = Color.White // specify color of target circle
            ),
            // specify the content to show to introduce app feature
            content = {
                Column {
                    Text(
                        text = "Create a note",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Click here to create notes",
                        color = Color.White,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Icon(
                        painterResource(id = R.drawable.right_arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.End),
                        tint = Color.White
                    )
                }
            }
        ),
        contentColor = Color.White,
        containerColor = Color.Magenta,
        elevation = FloatingActionButtonDefaults.elevation(6.dp)
    ) {
        Icon(
            Icons.Filled.Create,
            contentDescription = null
        )
    }
}

@Composable
fun IntroShowcaseScope.BackButton(introShowcaseState: IntroShowcaseState){
    IconButton(
        modifier = Modifier.introShowCaseTarget(
            index = 2,
            style = ShowcaseStyle.Default.copy(
                backgroundColor = Color(0xFF7C99AC), // specify color of background
                backgroundAlpha = 0.98f, // specify transparency of background
                targetCircleColor = Color.White // specify color of target circle
            ),
            content = {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painterResource(id = R.drawable.go_back),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(top = 10.dp)
                    )

                    Column {

                        Text(
                            text = "Go back!!",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "You can go back by clicking here.",
                            color = Color.White,
                            fontSize = 16.sp
                        )

                        Button(
                            onClick = {
                                // Used to restart the intro showcase
                                introShowcaseState.reset()
                            },
                        ) {
                            Text(text = "Restart Intro")
                        }
                    }
                }
            }
        ),
        onClick = { /*TODO*/ }
    ) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
    }}

@Composable
fun IntroShowcaseScope.Search(){
    IconButton(
        modifier = Modifier.introShowCaseTarget(
            index = 1,
            style = ShowcaseStyle.Default.copy(
                backgroundColor = Color(0xFF9AD0EC), // specify color of background
                backgroundAlpha = 0.98f, // specify transparency of background
                targetCircleColor = Color.White // specify color of target circle
            ),
            content = {
                Column {
                    Text(
                        text = "Search anything!!",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "You can search anything by clicking here.",
                        color = Color.White,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Icon(
                        painterResource(id = R.drawable.search_example),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.End),
                        tint = Color.White
                    )
                }
            }
        ),
        onClick = { /*TODO*/ }
    ) {
        Icon(imageVector = Icons.Filled.Search, contentDescription = null)
    }
}

