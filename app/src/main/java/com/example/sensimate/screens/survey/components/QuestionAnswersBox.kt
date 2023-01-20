package com.example.sensimate.screens.survey.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sensimate.R
import com.example.sensimate.firebase_model.data.Question
import com.example.sensimate.screens.survey.QuestionState

//change List<question>, List<useranswers> to question, UserAnswers when changed to the real code
@Composable
fun QuestionAnswersBox(
    questionState: QuestionState,
//    onAnswer: (Answer<*>) -> Unit
    editMode: Boolean,
    onNewAnswerOptionValue: (List<String>) -> Unit,
    onAnswer: (String) -> Unit
) {
    val question = questionState.question.value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
//            .heightIn(0.dp, 300.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(15.dp)
        ) {

            if (question.questionType == "Single choice") {
                SingleChoiceQuestion(
                    answerOptions = question.answerOptions,
                    answeredString = questionState.answeredString,
                    editMode = editMode,
                    onNewValue = onNewAnswerOptionValue,
                    onAnswer = onAnswer
                )
            } else if (question.questionType == "Multiple choice") {
                MultipleChoiceQuestion(
                    answerOptions = question.answerOptions,
                    editMode = editMode,
                    onNewValue = onNewAnswerOptionValue,
                    onAnswer = onAnswer,

                )
            }









            if (editMode){
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {


                    //add answer option
                    IconButton(
                        onClick = {
                            if(question.answerOptions.isNotEmpty()){
                                val answerOptionsTemp: MutableList<String> = question.answerOptions.toMutableList()
                                answerOptionsTemp.add("")
                                onNewAnswerOptionValue(answerOptionsTemp)
                            } else {
                                val answerOptionsTemp: List<String> = mutableListOf("Answer")
                                onNewAnswerOptionValue(answerOptionsTemp)
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add answerOption")
                    }
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleChoiceQuestion(
    answerOptions: List<String>,
    answeredString: String,
//onAnswerSelected: (Int) -> Unit,
    editMode: Boolean,
    onNewValue: (List<String>) -> Unit,
    onAnswer: (String) -> Unit,
) {

    var padding by remember { mutableStateOf(16.dp) }

    padding = if (editMode) {
        0.dp
    } else {
        12.dp
    }


    val (selectedOption, onOptionSelected) = mutableStateOf(answeredString)
    val answerOptionsTemp: MutableList<String> = answerOptions.toMutableList()

    Column(Modifier.selectableGroup()) {
        answerOptions.forEachIndexed { index, text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            onAnswer(text)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = padding),
                verticalAlignment = Alignment.CenterVertically
            ) {


                if (!editMode) {

                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null // null recommended for accessibility with screenreaders
                    )

                    Text(
                        text = text,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
                    )
                } else {

                    //delete answer option button
                    IconButton(
                        onClick = {
                            answerOptionsTemp.removeAt(index)
                            onNewValue(answerOptionsTemp)
                        }
                    ) {
                        //TODO: Color should be red?
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
//                            tint = MaterialTheme.colorScheme.error,
                            contentDescription = null,
                        )
                    }

                    //edit answer option text
                    OutlinedTextField(
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        singleLine = false,
                        value = text,
                        onValueChange = {
                            answerOptionsTemp[index] = it
                            onNewValue(answerOptionsTemp)
                        },
                        placeholder = { Text("Answer Text") }
                    )



                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultipleChoiceQuestion(
    answerOptions: List<String>,
//onAnswerSelected: (Int) -> Unit,
    editMode: Boolean,
    onNewValue: (List<String>) -> Unit,
    onAnswer: (String) -> Unit,
) {

    val answerOptionsTemp: MutableList<String> = answerOptions.toMutableList()

    Column(Modifier.selectableGroup()) {
        answerOptions.forEachIndexed {index, text ->
            val (checkedState, onStateChange) = remember { mutableStateOf(false) }

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .toggleable(
                        value = checkedState,
                        onValueChange = { onStateChange(!checkedState) },
                        role = Role.Checkbox
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedState,
                    onCheckedChange = null // null recommended for accessibility with screenreaders
                )
                if (!editMode) {
                    Text(
                        text = text,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(start = 16.dp, bottom = 0.dp)
                    )
                } else {
                    OutlinedTextField(
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        singleLine = false,
                        value = text,
                        onValueChange = {
                            answerOptionsTemp[index] = it
                            onNewValue(answerOptionsTemp)
                        },
                        placeholder = { Text("Answer Text") }
                    )
                    IconButton(
                        onClick = {
                            answerOptionsTemp.removeAt(index)
                            onNewValue(answerOptionsTemp)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = null,
                        )
                    }

                }
            }
        }
    }
}
