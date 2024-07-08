package org.sopt.dateroad.presentation.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.sopt.dateroad.presentation.type.OneButtonDialogWithDescriptionType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadOneButtonDialogWithDescription(
    oneButtonDialogWithDescriptionType: OneButtonDialogWithDescriptionType,
    onConfirmRequest: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(color = DateRoadTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(23.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            color = DateRoadTheme.colors.black,
            text = stringResource(id = oneButtonDialogWithDescriptionType.titleRes),
            style = DateRoadTheme.typography.bodyBold17
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            color = DateRoadTheme.colors.black,
            text = stringResource(id = oneButtonDialogWithDescriptionType.descriptionRes),
            style = DateRoadTheme.typography.bodyMed13
        )
        Spacer(modifier = Modifier.height(30.dp))
        DateRoadBasicButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            isEnabled = true,
            textContent = stringResource(id = oneButtonDialogWithDescriptionType.buttonTextRes),
            onClick = onConfirmRequest
        )
        Spacer(modifier = Modifier.height(14.dp))
    }
}

@Composable
fun DialogWithDescriptionTest(
    onDismissRequest: () -> Unit,
    oneButtonDialogWithDescriptionType: OneButtonDialogWithDescriptionType,
    onConfirmRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        DateRoadOneButtonDialogWithDescription(
            oneButtonDialogWithDescriptionType = oneButtonDialogWithDescriptionType,
            onConfirmRequest = onConfirmRequest
        )
    }
}

@Preview
@Composable
fun DateRoadOneButtonDialogWithDescriptionPreview() {
    val showDialog = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { showDialog.value = true }) {
            Text(text = "Show Dialog")
        }

        if (showDialog.value) {
            DialogWithDescriptionTest(
                onDismissRequest = { showDialog.value = false },
                oneButtonDialogWithDescriptionType = OneButtonDialogWithDescriptionType.SOON,
                onConfirmRequest = { showDialog.value = false }
            )
        }
    }
}
