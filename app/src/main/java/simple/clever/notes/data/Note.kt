package simple.clever.notes.data

import android.content.Context
import android.content.SharedPreferences
import android.os.Parcel
import android.os.Parcelable

import simple.clever.notes.R

class Note : Parcelable{

    private var noteIndex: Int
    private var heading: String

    constructor(parcel: Parcel) {
        noteIndex = parcel.readInt()
        heading = parcel.readString()!!
    }

    constructor(contentIndex: Int,  heading: String){
        this.noteIndex = contentIndex
        this.heading = heading
    }

    fun getNoteName(): String {
        return heading
//        return mContext.getResources().getString(R.string.init_head)
    }

    fun getNoteBody(mContext: Context): String? {
        val sp: SharedPreferences = mContext.applicationContext.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE)
        return sp.getString(KEY_USER_NOTE+noteIndex, mContext.resources.getString(R.string.init_note))
//        return mContext.getResources().getStringArray(R.array.notes)[noteIndex]
    }

    fun setNoteBody(mContext: Context, userText: String){
        val sharedPreferences: SharedPreferences = mContext.applicationContext.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_USER_NOTE+noteIndex, userText)
        editor.apply()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(noteIndex)
        parcel.writeString(heading)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }

        private const val KEY_USER_NOTE= "save_note"
        private const val KEY_PREF= "note_pref"
    }


}
