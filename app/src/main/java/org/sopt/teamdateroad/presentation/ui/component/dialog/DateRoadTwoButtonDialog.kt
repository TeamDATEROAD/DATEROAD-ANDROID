package org.sopt.teamdateroad.presentation.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.sopt.teamdateroad.presentation.type.TwoButtonDialogType
import org.sopt.teamdateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.teamdateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadTwoButtonDialog(
    twoButtonDialogType: TwoButtonDialogType,
    onDismissRequest: () -> Unit,
    onClickConfirm: () -> Unit,
    onClickDismiss: () -> Unit
) {
    DateRoadDialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(color = DateRoadTheme.colors.white),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(27.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                color = DateRoadTheme.colors.black,
                textAlign = TextAlign.Center,
                text = stringResource(id = twoButtonDialogType.titleRes),
                style = DateRoadTheme.typography.bodyMed15
            )
            Spacer(modifier = Modifier.height(29.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DateRoadBasicButton(
                    modifier = Modifier.weight(1f),
                    enabledBackgroundColor = DateRoadTheme.colors.gray200,
                    enabledTextColor = DateRoadTheme.colors.gray400,
                    textContent = stringResource(id = twoButtonDialogType.dismissButtonTextRes),
                    onClick = onClickDismiss
                )
                DateRoadBasicButton(
                    modifier = Modifier.weight(1f),
                    textContent = stringResource(id = twoButtonDialogType.confirmButtonTextRes),
                    onClick = onClickConfirm
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}
