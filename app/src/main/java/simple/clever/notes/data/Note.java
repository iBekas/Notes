package simple.clever.notes.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String userHead;
    private String userNote;
    private int index;



    public Note(String userHead, String userNote, int index) {
        this.userHead = userHead;
        this.userNote = userNote;
        this.index = index;
    }

    protected Note(Parcel in) {
        userHead = in.readString();
        userNote = in.readString();
        index = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userHead);
        dest.writeString(userNote);
        dest.writeInt(index);
    }

    public String getUserNote() {
        return userNote;
    }

    public int getIndex() {
        return index;
    }
}
