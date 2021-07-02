package simple.clever.notes;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    private String head;
    private String userNote;
    private String userHead;


    public Note(String head, String userNote, String userHead) {
        this.head = head;
        this.userNote = userNote;
        this.userHead = userHead;
    }

    protected Note(Parcel in) {
        head = in.readString();
        userNote = in.readString();
        userHead = in.readString();
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

    public void setHead(String head) {
        this.head = head;
    }

    public String getHead() {
        return head;
    }

    public String getUserNote() {
        return userNote;
    }

    public String getUserHead() {
        return userHead;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(head);
        dest.writeString(userNote);
        dest.writeString(userHead);
    }
}
