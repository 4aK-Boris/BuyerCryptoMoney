package aleksandr.fedotkin.buyercryptomoney.presentation.ui.visual.transformation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class BankCardNumberVisualTransformation: VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length >= LENGTH) {
            text.text.substring(0 until LENGTH)
        } else {
            text.text
        }

        val annotatedString = AnnotatedString.Builder().run {
            for (i in trimmed.indices) {
                append(trimmed[i])
                if (i % 4 == 3 && i != 15) {
                    append("-")
                }
            }
            pushStyle(SpanStyle(color = Color.LightGray))
            append(MASK.takeLast(MASK.length - length))
            toAnnotatedString()
        }

        val creditCardOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when (val length = text.text.length) {
                    in 0..3 -> length
                    in 4..7 -> length + 1
                    in 8..11 -> length + 2
                    in 12..15 -> length + 3
                    else -> 19
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when (val length = text.text.length) {
                    in 0..4 -> length
                    in 5..8 -> length - 1
                    in 9..12 -> length - 2
                    in 13..18 -> length - 3
                    else -> 16
                }
            }
        }

        return TransformedText(annotatedString, creditCardOffsetTranslator)
    }

    companion object {
        private const val MASK = "XXXX-XXXX-XXXX-XXXX"
        const val LENGTH = 16
    }
}