package com.assessment.sereneecommerce.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.assessment.sereneecommerce.di.ServiceLocator
import com.assessment.sereneecommerce.ui.theme.CuratedBadge
import com.assessment.sereneecommerce.ui.theme.SereneCategoryChip
import com.assessment.sereneecommerce.ui.theme.SereneCommerceTheme
import com.assessment.sereneecommerce.ui.theme.SereneSearchBar
import com.assessment.sereneecommerce.ui.theme.spacing

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(ServiceLocator.productRepository)
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(state = uiState)
}

@Composable
fun HomeContent(state: HomeUiState) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = MaterialTheme.spacing.xs)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.md)
        ) {
            Text(
                text = "MINDFUL SELECTIONS",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Discover & Shop",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                CuratedBadge()
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))
            SereneSearchBar(
                value = searchQuery,
                onValueChange = { searchQuery = it }
            )
        }
        
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

        when (state) {
            is HomeUiState.Error -> {
                TODO("Handle Error state")
            }
            HomeUiState.Loading -> {
                TODO("Handle Loading state")
            }
            is HomeUiState.Success -> {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.md),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs)
                ) {
                    items(state.categories) { category ->
                        SereneCategoryChip(
                            text = category,
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // TODO: Create product list composable and populate with data gotten from uiState

            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    SereneCommerceTheme {
        HomeContent(state = HomeUiState.Loading)
    }
}
