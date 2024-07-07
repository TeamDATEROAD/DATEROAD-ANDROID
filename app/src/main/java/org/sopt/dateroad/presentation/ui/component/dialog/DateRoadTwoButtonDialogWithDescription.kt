package org.sopt.dateroad.presentation.ui.component.dialog

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.type.TwoButtonDialogWithDescriptionType
import org.sopt.dateroad.presentation.ui.component.button.DateRoadBasicButton
import org.sopt.dateroad.ui.theme.DateRoadTheme

@Composable
fun DateRoadTwoButtonDialogWithDescription(
    twoButtonDialogWithDescriptionType: TwoButtonDialogWithDescriptionType)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = DateRoadTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally // Center align the components horizontally
    ) {
        Spacer(modifier = Modifier.height(23.dp))
        Text(text = stringResource(id = twoButtonDialogWithDescriptionType.titleRes), style = DateRoadTheme.typography.bodyBold17)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = stringResource(id = twoButtonDialogWithDescriptionType.descriptionRes), style = DateRoadTheme.typography.bodyMed13)
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp) // Space between the buttons
        ) {
            DateRoadBasicButton(
                modifier = Modifier.weight(1f),
                isEnabled = false,
                textContent = stringResource(id = twoButtonDialogWithDescriptionType.dismissButtonTextRes)
            )
            DateRoadBasicButton(
                modifier = Modifier.weight(1f),
                isEnabled = true,
                textContent = stringResource(id = twoButtonDialogWithDescriptionType.confirmButtonTextRes)
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
    }
}

@Preview
@Composable
fun DateRoadTwoButtonDialogWithDescriptionPreview() {
    DateRoadTwoButtonDialogWithDescription(TwoButtonDialogWithDescriptionType.POINT_LACK)
}