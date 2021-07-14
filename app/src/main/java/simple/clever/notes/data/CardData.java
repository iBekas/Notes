package simple.clever.notes.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CardData implements Parcelable {

    private String head;
    private String timeOpen;

    public CardData(String head) {
        this.head = head;
        this.timeOpen = getCurrentTimeStamp();
    }

    public String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date now = new Date();
        return sdfDate.format(now);
    }

    public String getHead() {
        return head;
    }

    public String getTimeOpen() {
        return timeOpen;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(head);
        dest.writeString(timeOpen);
    }

    protected CardData(Parcel in) {
        head = in.readString();
        timeOpen = in.readString();
    }

    public static final Creator<CardData> CREATOR = new Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };
}
