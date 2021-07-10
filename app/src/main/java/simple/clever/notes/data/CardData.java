package simple.clever.notes.data;

public class CardData {
    private String head;
    private String timeOpen;

    public CardData(String head, String timeOpen) {
        this.head = head;
        this.timeOpen = timeOpen;
    }

    public String getHead() {
        return head;
    }

    public String getTimeOpen() {
        return timeOpen;
    }
}
