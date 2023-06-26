package com.zc.dictionarygenius.ui.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue

class Mask(
    private val mask: String,
) : VisualTransformation {

    private val specialSymbolsIndices = mask.indices.filter { mask[it] != '#' }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0

        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }

        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == '#' }
        }
    }

    companion object {
        fun buildCpf() = Mask(MaskTypes.CPF.value)
        fun buildBirthday() = Mask(MaskTypes.DATE.value)
        fun buildPhone() = Mask(MaskTypes.PHONE.value)
        fun buildCep() = Mask(MaskTypes.CEP.value)
    }
}

enum class MaskTypes(val value: String) {
    PHONE("(##) # ####-####"),
    CPF("###.###.###-##"),
    DATE("##/##/####"),
    CEP("#####-###");

    fun length() = value.length
}