package com.bmnmrls.domain.mappers

abstract class Transform<T1, T2> {

    abstract fun transform(value: T1): T2

    abstract fun reverseTransform(value: T2): T1

    fun transformCollection(values: List<T1>): List<T2> = values.map { t1 -> transform(t1) }

    fun reverseTransformCollection(values: List<T2>) = values.map { t2 -> reverseTransform(t2) }

}