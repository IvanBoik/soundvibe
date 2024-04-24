package com.boiko.soundvibe.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.boiko.soundvibe.ui.theme.Montserrat
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.LocalDate

@Composable
fun DateInput(
    value: MutableState<LocalDate>,
    formattedValue: String,
    placeholder: String,
    dialogState: MaterialDialogState
) {
    Spacer(modifier = Modifier.height(16.dp))
    BasicTextField(
        value = formattedValue,
        onValueChange = {},
        enabled = false,
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 16.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium
        ),
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(46.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(16.dp)
            )
            .background(Color.Transparent, RoundedCornerShape(16.dp))
            .clickable { dialogState.show() },
        decorationBox = {innerTextField ->
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent, RoundedCornerShape(16.dp))
            ){
                Row {
                    Spacer(modifier = Modifier.width(24.dp))
                    if (formattedValue.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 16.sp,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.W100
                            )
                        )
                    }
                    else {
                        innerTextField()
                    }
                }
                if (formattedValue.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(end = 24.dp)
                    )
                }
            }
        }
    )
    MaterialDialog(
        dialogState = dialogState,
        properties = DialogProperties(
            dismissOnClickOutside = true
        ),
        buttons = {
            positiveButton(
                text = "Ok",
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
            negativeButton(
                text = "Cancel",
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        datepicker(
            initialDate = value.value,
            title = "Pick a date",
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = MaterialTheme.colorScheme.primary,
                dateActiveBackgroundColor = MaterialTheme.colorScheme.onPrimary,
                dateActiveTextColor = MaterialTheme.colorScheme.primary,
                dateInactiveTextColor = MaterialTheme.colorScheme.onPrimary,
                calendarHeaderTextColor = MaterialTheme.colorScheme.onPrimary,
                headerTextColor = MaterialTheme.colorScheme.onPrimary
            ),
            yearRange = IntRange(start = 1900, endInclusive = 2023)
        ) {
            value.value = it
        }
    }
}

@Preview
@Composable
private fun DateInputPreview() {

}