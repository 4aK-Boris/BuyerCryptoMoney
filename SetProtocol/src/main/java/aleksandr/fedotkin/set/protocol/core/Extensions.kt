package aleksandr.fedotkin.set.protocol.core

fun List<ByteArray>.contentEquals(other: List<ByteArray>): Boolean {
    return this.zip(other).fold(initial = true) { result, (first, second) ->
        result && first.contentEquals(second)
    }
}