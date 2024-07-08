package org.sopt.dateroad.presentation.ui.navigator.component

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.dateroad.presentation.type.MainNavigationBarItemType
import org.sopt.dateroad.ui.theme.Black
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.DateRoadTheme
import org.sopt.dateroad.ui.theme.Gray200
import org.sopt.dateroad.ui.theme.Gray300
import org.sopt.dateroad.ui.theme.White

@Composable
fun CustomNavigationBarItem(
    context: Context,
    mainNavigationBarItemType: MainNavigationBarItemType,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .background(if (isSelected) White else Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(11.dp))
        Icon(
            painter = painterResource(id = mainNavigationBarItemType.iconRes),
            tint = if (isSelected) Black else Gray200,
            contentDescription = context.getString(mainNavigationBarItemType.label)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = context.getString(mainNavigationBarItemType.label),
            style = DateRoadTheme.typography.capReg11,
            color = if (isSelected) Black else Gray300
        )
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    isVisible: Boolean,
    navigationBarItems: List<MainNavigationBarItemType>,
    currentNavigationBarItem: MainNavigationBarItemType?,
    onNavigationBarItemSelected: (MainNavigationBarItemType) -> Unit
) {
    AnimatedVisibility(visible = isVisible) {
        Row(
            modifier = modifier
                .background(White)
                .border(1.dp, Color(0xFFF1F1F5))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationBarItems.forEach { mainNavigationBarItemType ->
                val isSelected = currentNavigationBarItem == mainNavigationBarItemType

                CustomNavigationBarItem(
                    context = context,
                    mainNavigationBarItemType = mainNavigationBarItemType,
                    isSelected = isSelected,
                    onClick = { onNavigationBarItemSelected(mainNavigationBarItemType) },
                    modifier = Modifier.weight(1f) // Ensure equal width for each item
                )
            }
        }
    }
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    DATEROADTheme {
        MainBottomBar(
            isVisible = true,
            navigationBarItems = MainNavigationBarItemType.entries.toList(),
            currentNavigationBarItem = MainNavigationBarItemType.HOME,
            onNavigationBarItemSelected = {}
        )
    }
}
