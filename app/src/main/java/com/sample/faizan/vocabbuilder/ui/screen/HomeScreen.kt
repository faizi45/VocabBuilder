package com.sample.faizan.vocabbuilder.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.faizan.vocabbuilder.R
import com.sample.faizan.vocabbuilder.components.VocabButton
import com.sample.faizan.vocabbuilder.components.VocabInputText
import com.sample.faizan.vocabbuilder.components.VocabOutlinedTextFieldWithIcon
import com.sample.faizan.vocabbuilder.models.VocabData

//This is home screen composable...
@ExperimentalComposeUiApi
@Composable
fun HomeScreen(
    vocabs: List<VocabData>,
    onAdd: (VocabData) -> Unit,
    onRemove: (VocabData) -> Unit
) {
    var word by remember {
        mutableStateOf("")
    }
    var meaning by remember {
        mutableStateOf("")
    }
    var sentence by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name))
                          }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "")
        }, backgroundColor = Color(0xFFDADFE3))
        
        Column(modifier = Modifier.fillMaxWidth(),

        horizontalAlignment = Alignment.CenterHorizontally) {
            VocabOutlinedTextFieldWithIcon(
                modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp),
                text = word,
                icon = Icons.Default.Email,
                label = "Words",
                placeholder = "Enter the word you learnt",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) word = it
                } )

            VocabInputText(modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp),
                text = meaning,
                label = "Meaning",
                placeholder = "Enter the meaning of your word",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) meaning = it
                } )
            
            VocabButton(text = "Save", onClick = {
                if (word.isNotEmpty() && meaning.isNotEmpty()){
                    onAdd(VocabData(word= word, meaning = meaning, sentence = ""))
                    word = ""
                    meaning = ""
                }
            })

        }

        Divider(modifier = Modifier.padding(10.dp))

        LazyColumn{
            items(vocabs){ vocabData ->
                VocabRowItem(vocabData = vocabData, onRowItemClicked = {
                    onRemove(it)
                })
            }
        }

        Scaffold(floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Word Icon ")
                }
            }, content = {

            })
    }
}

@Composable
fun VocabRowItem(modifier: Modifier = Modifier,
vocabData: VocabData, onRowItemClicked: (VocabData) -> Unit){
    
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
//        .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(), color = Color(0xFF2D6F9E),
        elevation = 6.dp) {
        Column(modifier
            .clickable { onRowItemClicked(vocabData) }
            .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = vocabData.word,
                style = MaterialTheme.typography.subtitle2,
                color = Color.White,
            fontSize = 20.sp)
            Text(text = vocabData.meaning,
                style = MaterialTheme.typography.caption,
                color = Color.White,
            fontSize = 12.sp)
//            Text(text = formatDate(vocabData.entryDate.time),
//                style = MaterialTheme.typography.caption)


        }
    }
}