package aleksandr.fedotkin.set.protocol.core.mapper

interface BaseMapper <T, R> {

    fun map(value: T): R

    fun reverseMap(value: R): T
}