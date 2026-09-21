package com.assessment.sereneecommerce.ui.detail


import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.assessment.sereneecommerce.data.model.Product
import com.assessment.sereneecommerce.data.model.Rating
import com.assessment.sereneecommerce.ui.theme.RatingAccent
import com.assessment.sereneecommerce.ui.theme.SereneButton
import com.assessment.sereneecommerce.ui.theme.SereneCommerceTheme
import com.assessment.sereneecommerce.ui.theme.SereneQuantityPicker
import com.assessment.sereneecommerce.ui.theme.spacing

@Composable
fun DetailScreen(product: Product) {
    DetailContent(product = product)
}

@Composable
fun DetailContent(product: Product) {
    var isFavorite by remember { mutableStateOf(false) }
    var quantity by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Product image with favourite icon
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(MaterialTheme.spacing.xs)
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                ) {
                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                            tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.md),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs)
        ) {
            // Breadcrumb: category • subCategory
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = product.category,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "  •  ",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = product.subCategory,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Title
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            // Rating
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = RatingAccent,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.xxs))
                Text(
                    text = product.rating.stars.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.xxs))
                Text(
                    text = "(${product.rating.count} customer ratings)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Price
            Text(
                text = "$${"%.2f".format(product.priceCents / 100.0)}",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

            // Overview
            Text(
                text = "OVERVIEW",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = product.description, style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

            // Related themes / keywords
            Text(
                text = "Related Themes",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                product.keywords.forEach { keyword ->
                    Text(
                        text = "#$keyword",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.surfaceDim,
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(
                                vertical = MaterialTheme.spacing.xxs,
                                horizontal = MaterialTheme.spacing.xs
                            )
                    )
                    Spacer(Modifier.width(MaterialTheme.spacing.xs))
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))

            // Quantity + Add to Cart
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SereneQuantityPicker(
                    quantity = quantity, onQuantityChange = { quantity = it })
                Spacer(Modifier.width(MaterialTheme.spacing.md))
                SereneButton(
                    text = "Add to Cart", modifier = Modifier.weight(1f), onClick = { })
            }
        }
    }
}

@Preview
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
fun DetailPreview() {
    SereneCommerceTheme {
        DetailContent(
            product = Product(
                id = "1",
                image = "https://kolzsticks.github.io/Free-Ecommerce-Products-Api/main/images/products/moisturizer.jpg",
                name = "Organic Herbal Facial Serum",
                rating = Rating(stars = 4.5f, count = 120),
                priceCents = 2000,
                category = "Beauty & Personal Care",
                subCategory = "Skincare",
                keywords = listOf("moisturizer", "hydration", "skincare", "beauty"),
                description = "This Hydrating Facial Moisturizer is expertly formulated to deeply nourish and hydrate your skin."
            )
        )
    }
}
