package com.assessment.sereneecommerce.ui.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import com.assessment.sereneecommerce.ui.theme.SereneCommerceTheme

@Composable
fun DetailScreen() {
    DetailContent()
}

// TODO: Create the content of the details screen here
@Composable
fun DetailContent() {
    Text(text = "Detail Screen")
}

@Preview
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
fun DetailPreview() {
    SereneCommerceTheme {
        DetailContent()
    }
}
