@file:JvmName("ParcelableUtils")
@file:Suppress("NOTHING_TO_INLINE")

package biz.growapp.base.extensions

import android.os.Parcel


inline fun Parcel.readBoolean() = readInt() != 0

inline fun Parcel.writeBoolean(value: Boolean?) = writeInt(if (value ?: false) 1 else 0)


inline fun Parcel.writeInt(value: Int?) = if (value == null) {
    writeBoolean(false)
} else {
    writeBoolean(true)
    writeInt(value)
}

inline fun Parcel.readIntUtil() = if (readBoolean()) readInt() else null


inline fun Parcel.writeDouble(value: Double?) = if (value == null) {
    writeBoolean(false)
} else {
    writeBoolean(true)
    writeDouble(value)
}

inline fun Parcel.readDoubleUtil() = if (readBoolean()) readDouble() else null


inline fun Parcel.writeLong(value: Long?) = if (value == null) {
    writeBoolean(false)
} else {
    writeBoolean(true)
    writeLong(value)
}

inline fun Parcel.readLongUtil() = if (readBoolean()) readLong() else null