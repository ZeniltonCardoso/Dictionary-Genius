package com.zc.dictionarygenius.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.material.R

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit = {},
    hint: String = "",
    onIconCloseClick: (() -> Unit)? = null,
    onComponentClick: (() -> Unit)? = null,
    mask: VisualTransformation = VisualTransformation.None,
    showCloseIcon: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = if (onComponentClick != null) modifier
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {
                    onComponentClick.invoke()
                }
            )
        else modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { input ->
                onValueChanged.invoke(input)
            },
            maxLines = 1,
            singleLine = true,
            shape = RoundedCornerShape(size = 40.dp),
            textStyle = TextStyle(fontWeight = FontWeight.Bold,
                fontSize = 24.sp),
            enabled = onComponentClick == null,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text
            ),
            visualTransformation = mask,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.button.copy(
                        color = colors.primary
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 40.dp)
                .border(
                    width = 1.dp,
                    color = colors.primary,
                    shape = RoundedCornerShape(size = 24.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledTextColor = colors.primary,
                cursorColor = colors.primary
            ),
            leadingIcon = inputTextIcon(
                color = colors.primary,
                resourceId = R.drawable.ic_search_black_24,
                onClick = null,
            ),
            trailingIcon = searchBarIconRight(
                isEmpty = value.isEmpty() && !showCloseIcon,
                onClick = onIconCloseClick
            )
        )
    }
}

@Composable
fun inputTextIcon(
    resourceId: Int?,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    color: Color? = null
): @Composable () -> Unit =
    @Composable {
        resourceId?.let { painterResource(id = it) }
            ?.let { painter ->
                if (onClick != null) {
                    IconButton(onClick = onClick) {
                        InputIcon(painter, modifier, color)
                    }
                } else InputIcon(painter, modifier, color)
            }
    }

@Composable
private fun InputIcon(painter: Painter, modifier: Modifier, color: Color? = null) {
    Icon(
        painter = painter,
        contentDescription = "",
        tint = color ?: Color.Unspecified,
        modifier = modifier
    )
}

@Composable
fun searchBarIconRight(
    isEmpty: Boolean,
    onClick: (() -> Unit)? = null
): @Composable (() -> Unit)? {
    return when {
        isEmpty -> null
        else -> inputTextIcon(
            resourceId = R.drawable.ic_m3_chip_close,
            onClick = onClick,
            color = colors.primary
        )
    }
}

@Composable
@Preview
fun InputTextPreview() {
    Column {
        SearchBar(value = "Preview", hint = "Preview hint")
    }
}