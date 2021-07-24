package simple.clever.notes.data

import com.google.firebase.Timestamp

import java.util.HashMap


class CardDataMapping {

    object Fields{
        const val DATE: String = "date"
        const val HEAD: String = "title"
        const val FAVORITE: String = "favorite"
    }


    companion object{
        @JvmStatic
         fun toCardData(id: String, doc: Map<String, Any>): CardData {
            val timeStamp: Timestamp = doc.get(Fields.DATE) as Timestamp
            val answer= CardData(doc.get(Fields.HEAD) as String, timeStamp.toDate(), doc.get(Fields.FAVORITE) as Boolean)
            answer.id = id
            return answer
        }
        @JvmStatic
        fun toDocument(cardData: CardData): MutableMap<String, Any>{
            val answer: MutableMap<String, Any> = HashMap()
            answer[Fields.HEAD] = cardData.head
            answer.put(Fields.DATE, cardData.timeOpen)
            answer.put(Fields.FAVORITE, cardData.favorite)
            return answer
        }
    }
}

