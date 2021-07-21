package simple.clever.notes.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CardData implements Parcelable {

    private String id;
    private String head;
    private Date timeOpen;

    public CardData(String head, Date timeOpen) {
        this.head = head;
        this.timeOpen = timeOpen;
    }

//    public String getCurrentTimeStamp() {
//        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//        Date now = new Date();
//        return sdfDate.format(now);
//    }

    public String getHead() {
        return head;
    }

    public Date getTimeOpen() {
        return timeOpen;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(head);
        dest.writeLong(timeOpen.getTime());
    }

    protected CardData(Parcel in) {
        head = in.readString();
        timeOpen = new Date(in.readLong());
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
