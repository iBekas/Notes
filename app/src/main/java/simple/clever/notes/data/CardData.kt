package simple.clever.notes.data;

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class CardData() : Parcelable {

    var id: String = ""
    lateinit var head: String
    lateinit var timeOpen: Date
    var favorite: Boolean = false

    constructor(head: String, timeOpen: Date, favorite: Boolean) : this() {
        this.head = head
        this.timeOpen = timeOpen
        this.favorite = favorite
        this.id = ""
    }

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()?:""
        head = parcel.readString()?:""
        favorite = parcel.readByte() != 0.toByte()
        timeOpen = Date(parcel.readLong())
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(timeOpen.time)
        parcel.writeString(id)
        parcel.writeString(head)
        parcel.writeByte(if (favorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CardData> {
        override fun createFromParcel(parcel: Parcel): CardData {
            return CardData(parcel)
        }

        override fun newArray(size: Int): Array<CardData?> {
            return arrayOfNulls(size)
        }
    }


}
