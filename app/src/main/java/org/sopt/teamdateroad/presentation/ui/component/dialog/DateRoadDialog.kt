package org.sopt.teamdateroad.presentation.ui.component.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.sopt.teamdateroad.presentation.type.OneButtonDialogType
import org.sopt.teamdateroad.presentation.type.OneButtonDialogWithDescriptionType
import org.sopt.teamdateroad.presentation.type.TwoButtonDialogType
import org.sopt.teamdateroad.presentation.type.TwoButtonDialogWithDescriptionType

@Composable
fun DateRoadDialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        content()
    }
}

@Preview
@Composable
fun DateRoadDialogPreview(modifier: Modifier = Modifier) {
    val showOneButtonDialog = remember { mutableStateOf(false) }
    val showOneButtonDialogWithDescription = remember { mutableStateOf(false) }
    val showTwoButtonDialog = remember { mutableStateOf(false) }
    val showTwoButtonDialogWithDescription = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showOneButtonDialog.value = true }) {
            Text(text = "Show One Button Dialog")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { showOneButtonDialogWithDescription.value = true }) {
            Text(text = "Show One Button Dialog With Description")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { showTwoButtonDialog.value = true }) {
            Text(text = "Show Two Button Dialog")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { showTwoButtonDialogWithDescription.value = true }) {
            Text(text = "Show Two Button Dialog With Description")
        }
    }

    if (showOneButtonDialog.value) {
        DateRoadOneButtonDialog(
            oneButtonDialogType = OneButtonDialogType.ENROLL_TIMELINE,
            onDismissRequest = { showOneButtonDialog.value = false },
            onClickConfirm = { showOneButtonDialog.value = false }
        )
    }

    if (showOneButtonDialogWithDescription.value) {
        DateRoadOneButtonDialogWithDescription(
            oneButtonDialogWithDescriptionType = OneButtonDialogWithDescriptionType.ENROLL_COURSE,
            onDismissRequest = { showOneButtonDialogWithDescription.value = false },
            onClickConfirm = { showOneButtonDialogWithDescription.value = false }
        )
    }

    if (showTwoButtonDialog.value) {
        DateRoadTwoButtonDialog(
            twoButtonDialogType = TwoButtonDialogType.LOGOUT,
            onDismissRequest = { showTwoButtonDialog.value = false },
            onClickConfirm = { showTwoButtonDialog.value = false },
            onClickDismiss = { showTwoButtonDialog.value = false }
        )
    }

    if (showTwoButtonDialogWithDescription.value) {
        DateRoadTwoButtonDialogWithDescription(
            twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.POINT_LACK,
            onDismissRequest = { showTwoButtonDialogWithDescription.value = false },
            onClickConfirm = { showTwoButtonDialogWithDescription.value = false },
            onClickDismiss = { showTwoButtonDialogWithDescription.value = false }
        )
    }
}
