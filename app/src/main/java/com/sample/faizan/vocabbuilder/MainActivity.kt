package com.sample.faizan.vocabbuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sample.faizan.vocabbuilder.ui.screen.HomeScreen
import com.sample.faizan.vocabbuilder.ui.screen.VocabViewModel
import com.sample.faizan.vocabbuilder.ui.theme.VocabBuilderTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalComposeUiApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VocabBuilderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel: VocabViewModel by viewModels()
                    VocabBuilder(viewModel)
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun VocabBuilder(viewModel: VocabViewModel){

    val vocabsList = viewModel.noteList.collectAsState().value

    HomeScreen(vocabs = vocabsList,
        onRemove = {viewModel.deleteVocab(it)},
        onAdd = {viewModel.addVocab(it)})
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VocabBuilderTheme {
        Greeting("Android")
    }
}