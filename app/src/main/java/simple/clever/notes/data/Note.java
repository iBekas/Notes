package simple.clever.notes.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import simple.clever.notes.R;

public class Note implements Parcelable {

    private int noteIndex;
    private String heading;
    private static final String KEY_USER_NOTE= "save_note";
    private static final String KEY_PREF= "note_pref";

    public Note(int contentIndex, String heading){
        this.noteIndex = contentIndex;
        this.heading = heading;
    }

    public String getNoteName(Context mContext) {
        return heading;
//        return mContext.getResources().getString(R.string.init_head);
    }

    public String getNoteBody(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sp.getString(KEY_USER_NOTE+noteIndex, mContext.getResources().getString(R.string.init_note));
//        return mContext.getResources().getStringArray(R.array.notes)[noteIndex];
    }

    public void setNoteBody(Context mContext, String userText){
        SharedPreferences sharedPreferences = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_NOTE+noteIndex, userText);
        editor.apply();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(noteIndex);
    }

    protected Note(Parcel in) {
        noteIndex = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
