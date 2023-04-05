package com.zc.dictionarygenius.ui.bills

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.navigation.NavHostController
import com.zc.dictionarygenius.R
import com.zc.dictionarygenius.data.UserData.bills
import com.zc.dictionarygenius.ui.components.BillRow
import com.zc.dictionarygenius.ui.components.StatementBody

@Composable
fun BillsScreen(
    navHostController: NavHostController
) {
    StatementBody(
        modifier = Modifier.clearAndSetSemantics { contentDescription = "Bills" },
        items = bills,
        amounts = { bill -> bill.amount },
        colors = { bill -> bill.color },
        amountsTotal = bills.map { bill -> bill.amount }.sum(),
        circleLabel = stringResource(R.string.due),
        rows = { bill ->
            BillRow(bill.name, bill.due, bill.amount, bill.color)
        }
    )
}
