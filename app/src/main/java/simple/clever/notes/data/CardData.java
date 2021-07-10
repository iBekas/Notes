package simple.clever.notes.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CardData {
    private String head;
    private String timeOpen;

    public CardData(String head) {
        this.head = head;
        this.timeOpen = getCurrentTimeStamp();
    }

    public String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public String getHead() {
        return head;
    }

    public String getTimeOpen() {
        return timeOpen;
    }
}
