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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sensimate.model2.Question

//change List<question>, List<useranswers> to question, UserAnswers when changed to the real code
@Composable
fun QuestionAnswersBox(
    question: Question,
//    onAnswer: (Answer<*>) -> Unit
    editMode: Boolean,
    onNewAnswerOptionValue: (List<String>) -> Unit
) {

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
                    editMode = editMode,
                    onNewValue = onNewAnswerOptionValue

                )
            } else if (question.questionType == "Multiple choice") {
                MultipleChoiceQuestion(question.answerOptions,
                    editMode = editMode,
                    onNewValue = onNewAnswerOptionValue)
            }

            if (editMode){
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {


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

@Composable
fun SingleChoiceQuestion(
    answerOptions: List<String>,
//onAnswerSelected: (Int) -> Unit,
    editMode: Boolean,
    onNewValue: (List<String>) -> Unit
) {

    val (selectedOption, onOptionSelected) = remember { mutableStateOf("-") }
    val answerOptionsTemp: MutableList<String> = answerOptions.toMutableList()

    Column(Modifier.selectableGroup()) {
        answerOptions.forEachIndexed { index, text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // null recommended for accessibility with screenreaders
                )

                if (!editMode) {
                    Text(
                        text = text,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
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
                        //TODO: Size
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            modifier = Modifier.size(20.dp),
                            contentDescription = null,
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun MultipleChoiceQuestion(
    answerOptions: List<String>,
//onAnswerSelected: (Int) -> Unit,
    editMode: Boolean,
    onNewValue: (List<String>) -> Unit
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
