package org.sopt.dateroad.presentation.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.type.OneButtonDialogType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadOneButtonDialog(
    oneButtonDialogType: OneButtonDialogType
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = DateRoadTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally // Center align the components horizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = stringResource(id = oneButtonDialogType.titleRes), style = DateRoadTheme.typography.bodyBold17)
        Spacer(modifier = Modifier.height(36.dp))
        DateRoadBasicButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            isEnabled = true,
            textContent = stringResource(id = oneButtonDialogType.buttonTextRes)
        )

        Spacer(modifier = Modifier.height(14.dp))
    }
}

@Preview
@Composable
fun DateRoadOneButtonDialogPreview() {
    DateRoadOneButtonDialog(OneButtonDialogType.ENROLL_TIMELINE)
}
