package org.sopt.dateroad.presentation.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import org.sopt.dateroad.presentation.type.TwoButtonDialogWithDescriptionType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadTwoButtonDialogWithDescription(
    twoButtonDialogWithDescriptionType: TwoButtonDialogWithDescriptionType,
    onDismissRequest: () -> Unit,
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
            text = stringResource(id = twoButtonDialogWithDescriptionType.titleRes),
            style = DateRoadTheme.typography.bodyBold17
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            color = DateRoadTheme.colors.black,
            text = stringResource(id = twoButtonDialogWithDescriptionType.descriptionRes),
            style = DateRoadTheme.typography.bodyMed13
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DateRoadBasicButton(
                modifier = Modifier.weight(1f),
                isEnabled = false,
                textContent = stringResource(id = twoButtonDialogWithDescriptionType.dismissButtonTextRes),
                onClick = onDismissRequest
            )
            DateRoadBasicButton(
                modifier = Modifier.weight(1f),
                isEnabled = true,
                textContent = stringResource(id = twoButtonDialogWithDescriptionType.confirmButtonTextRes),
                onClick = onConfirmRequest
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
    }
}

@Composable
fun DialogWithDescriptionTest(
    onDismissRequest: () -> Unit,
    twoButtonDialogWithDescriptionType: TwoButtonDialogWithDescriptionType,
    onConfirmRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        DateRoadTwoButtonDialogWithDescription(
            twoButtonDialogWithDescriptionType = twoButtonDialogWithDescriptionType,
            onDismissRequest = onDismissRequest,
            onConfirmRequest = onConfirmRequest
        )
    }
}

@Preview
@Composable
fun DateRoadTwoButtonDialogWithDescriptionPreview() {
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
                twoButtonDialogWithDescriptionType = TwoButtonDialogWithDescriptionType.POINT_LACK,
                onConfirmRequest = { showDialog.value = false }
            )
        }
    }
}
