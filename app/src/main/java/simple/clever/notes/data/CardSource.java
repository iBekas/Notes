package simple.clever.notes.data;

public interface CardSource {
    CardData getCardData(int position);
    int size();
}
