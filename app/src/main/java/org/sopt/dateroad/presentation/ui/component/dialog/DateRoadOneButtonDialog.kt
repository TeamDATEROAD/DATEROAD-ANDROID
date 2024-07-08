package org.sopt.dateroad.presentation.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.type.OneButtonDialogType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadOneButtonDialog(
    oneButtonDialogType: OneButtonDialogType,
    onDismissRequest: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(color = DateRoadTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(id = oneButtonDialogType.titleRes),
            style = DateRoadTheme.typography.bodyBold17
        )
        Spacer(modifier = Modifier.height(36.dp))
        DateRoadBasicButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            isEnabled = true,
            textContent = stringResource(id = oneButtonDialogType.buttonTextRes),
            onClick = onDismissRequest
        )
        Spacer(modifier = Modifier.height(14.dp))
    }
}

@Composable
fun CustomDialogWrapper(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
            .clickable(onClick = onDismissRequest),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = DateRoadTheme.colors.white)
                .clickable(enabled = false) {}
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun DateRoadOneButtonDialogPreview() {
    val showDialog = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { showDialog.value = true }) {
            Text(text = "Show Dialog")
        }

        if (showDialog.value) {
            CustomDialogWrapper(onDismissRequest = { showDialog.value = false }) {
                DateRoadOneButtonDialog(
                    oneButtonDialogType = OneButtonDialogType.ENROLL_TIMELINE,
                    onDismissRequest = { showDialog.value = false }
                )
            }
        }
    }
}
