package com.veroanggra.pennymate.feature.detail

import android.icu.text.DecimalFormat
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.veroanggra.pennymate.data.BillDetails
import com.veroanggra.pennymate.data.BillItem
import kotlinx.serialization.json.Json

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SplitBillResultScreen(
    modifier: Modifier = Modifier,
    billJson: String,
    navController: NavController
) {
    val billDetails: BillDetails? = remember(billJson) {
        billJson.let {
            try {
                Json.decodeFromString<BillDetails>(Uri.decode(it))
            } catch (e: Exception) {
                null
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        ResultScreenTopBar(navController)
        if (billDetails != null) {
            BillDetailsContent(
                bill = billDetails,
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            BottomActionButtons()
        } else {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text("Could not load bill details")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        navController.popBackStack()
                    }) {
                        Text("Go Back")
                    }
                }
            }
        }

    }
}

@Composable
fun BottomActionButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0XFFBCE0F1)
            )
        ) {
            Text("Edit Bill", color = Color(0xFF1F75A8))
        }
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F75A8))
        ) {
            Text("Confirm", color = Color.White)
        }
    }
}

@Composable
fun ResultScreenTopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 4.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        Text(
            text = "Split Bill",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
fun BillDetailsContent(bill: BillDetails, modifier: Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Split Bill",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Recognize Items",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Make sure to check that all item read correctly.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = bill.dateTime,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = bill.restaurantName,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = Color.LightGray)
        Spacer(modifier = Modifier.height(16.dp))
        bill.items.forEachIndexed { index, item ->
            BillItemRow(item = item)
            if (index < bill.items.size - 1) {
                DottedDivider(modifier = Modifier.padding(vertical = 12.dp))
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        SummaryRow("Subtotal", bill.subTotal.toDouble())
        SummaryRow("Tax", bill.tax.toDouble())
        SummaryRow("Discount", bill.discount.toDouble())
        SummaryRow("Other", bill.others.toDouble())
        HorizontalDivider(color = Color.LightGray, modifier = Modifier.padding(vertical = 8.dp))
        SummaryRow("Total", bill.total.toDouble(), isTotal = true)
        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
fun SummaryRow(label: String, value: Double, isTotal: Boolean = false) {
    val textStyle =
        if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge
    val fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            style = textStyle,
            fontWeight = fontWeight,
            color = if (isTotal) MaterialTheme.colorScheme.onSurface else Color.DarkGray
        )
        Text(
            formatCurrencyValue(value),
            style = textStyle,
            fontWeight = fontWeight,
            color = if (isTotal) MaterialTheme.colorScheme.onSurface else Color.DarkGray
        )

    }
}

@Composable
fun DottedDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.LightGray,
    strokeWidth: Float = 2f,
    dashWidth: Float = 10f,
    gapWidth: Float = 8f
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, gapWidth), 0f)
        drawLine(
            color = color,
            start = Offset(0f, center.y),
            end = Offset(size.width, center.y),
            strokeWidth = strokeWidth,
            pathEffect = pathEffect
        )
    }
}

@Composable
fun BillItemRow(item: BillItem) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            item.name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = formatCurrencyValue(item.unitPrice.toDouble()),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                "${item.quantity}x",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )
            Text(
                formatCurrencyValue(item.totalPrice.toDouble()),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

fun formatCurrencyValue(value: Double): String {
    val formatter = DecimalFormat("#,###")
    formatter.maximumFractionDigits = 0
    return formatter.format(value)

}

