package simple.clever.notes.data;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import simple.clever.notes.R;

public class Note implements Parcelable {

    private int noteIndex;

    public Note(int contentIndex){
        this.noteIndex = contentIndex;
    }

    public int getNoteIndex() {
        return noteIndex;
    }

    public String getNoteName(Context mContext) {
        return mContext.getResources().getString(R.string.init_head);
    }

    public String getNoteBody(Context mContext) {
        return mContext.getResources().getStringArray(R.array.notes)[noteIndex];
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
