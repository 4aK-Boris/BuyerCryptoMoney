package aleksandr.fedotkin.buyercryptomoney.presentation.ui.visual.transformation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CodeVisualTransformation: VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length >= LENGTH) {
            text.text.substring(0 until LENGTH)
        } else {
            text.text
        }

        val annotatedString = AnnotatedString.Builder().run {
            append(trimmed)
            pushStyle(SpanStyle(color = Color.LightGray))
            append(MASK.takeLast(MASK.length - length))
            toAnnotatedString()
        }

        val creditCardOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return text.text.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                return text.text.length
            }
        }

        return TransformedText(annotatedString, creditCardOffsetTranslator)
    }

    companion object {
        private const val MASK = "******"
        const val LENGTH = 6
    }
}