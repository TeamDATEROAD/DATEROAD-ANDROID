package org.sopt.dateroad.presentation.ui.navigator.component

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.dateroad.R
import org.sopt.dateroad.presentation.type.MainNavigationBarItemType
import org.sopt.dateroad.ui.theme.DATEROADTheme
import org.sopt.dateroad.ui.theme.capReg11
import org.sopt.dateroad.ui.theme.Gray200
import org.sopt.dateroad.ui.theme.Gray300
import org.sopt.dateroad.ui.theme.Black

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
        NavigationBar {
            navigationBarItems.forEachIndexed { _, mainNavigationBarItemType ->
                val isSelected = currentNavigationBarItem == mainNavigationBarItemType

                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onNavigationBarItemSelected(mainNavigationBarItemType) },
                    icon = {
                        Icon(
                            painter = painterResource(id = mainNavigationBarItemType.iconRes),
                            tint = if (isSelected) Black else Gray200,
                            contentDescription = context.getString(mainNavigationBarItemType.label)
                        )
                    },
                    label = {
                        Text(
                            text = context.getString(mainNavigationBarItemType.label),
                            style = capReg11,
                            color = if (isSelected) Black else Gray300
                        )
                    }
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
