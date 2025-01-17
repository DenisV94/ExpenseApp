package org.denis.expenseapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import network.chaintech.kmp_date_time_picker.ui.datepicker.WheelDatePickerComponent.WheelDatePicker
import network.chaintech.kmp_date_time_picker.utils.WheelPickerDefaults
import network.chaintech.kmp_date_time_picker.utils.now

@Composable
fun VisibleDatePicker(
    modifier: Modifier = Modifier,
    startDate: LocalDate = LocalDate.now(),
    minDate: LocalDate = LocalDate(2000, 1, 1),
    maxDate: LocalDate = LocalDate(2100, 12, 31),
    yearsRange: IntRange = 2000..2100,
    onDateSelected: (LocalDate) -> Unit
) {
    var selectedDate by remember { mutableStateOf(startDate) }

    WheelDatePicker(
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
        startDate = selectedDate,
        minDate = minDate,
        maxDate = maxDate,
        yearsRange = yearsRange,
        selectorProperties = WheelPickerDefaults.selectorProperties(),
        dateTextStyle = MaterialTheme.typography.bodyMedium,
        onDateChangeListener = { snappedDate ->
            selectedDate = snappedDate
            onDateSelected(snappedDate)
        },
        hideHeader = true
    )
}