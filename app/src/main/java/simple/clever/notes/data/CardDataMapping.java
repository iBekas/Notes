package simple.clever.notes.data;

import com.google.firebase.Timestamp;

import java.util.HashMap;
import java.util.Map;

public class CardDataMapping {

    public static class Fields{
        public final static String DATE = "date";
        public final static String HEAD = "title";
        public final static String FAVORITE = "favorite";
    }

    public static CardData toCardData(String id, Map<String, Object> doc) {
        Timestamp timeStamp = (Timestamp)doc.get(Fields.DATE);
        CardData answer = new CardData((String) doc.get(Fields.HEAD), timeStamp.toDate(), (boolean) doc.get(Fields.FAVORITE));
        answer.setId(id);
        return answer;
    }

    public static Map<String, Object> toDocument(CardData cardData){
        Map<String, Object> answer = new HashMap<>();
        answer.put(Fields.HEAD, cardData.getHead());
        answer.put(Fields.DATE, cardData.getTimeOpen());
        answer.put(Fields.FAVORITE, cardData.getFavorite());
        return answer;
    }
}
