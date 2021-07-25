package simple.clever.notes.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText

import androidx.fragment.app.DialogFragment

import java.util.Date

import simple.clever.notes.MainActivity
import simple.clever.notes.R
import simple.clever.notes.data.CardData

class ChangeHeadingDialogBuilderFragment: DialogFragment() {

    private var cardData: CardData? = null
    private var userHeadText: EditText? = null

    companion object{
        const val SAVE_CARD_DATA = "SaveCardData"

        @JvmStatic
        fun newInstance(cardData: CardData): ChangeHeadingDialogBuilderFragment  {
            val fragment = ChangeHeadingDialogBuilderFragment()
            val args = Bundle()
            args.putParcelable(SAVE_CARD_DATA, cardData)
            fragment.arguments = args
            return fragment
        }

        @JvmStatic
        fun newInstance(): ChangeHeadingDialogBuilderFragment {
            return ChangeHeadingDialogBuilderFragment()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            cardData = requireArguments().getParcelable(SAVE_CARD_DATA)!!
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val contentView: View = requireActivity().layoutInflater
                .inflate(R.layout.dialog_change_heading, null)

        init(contentView)
        if (cardData != null) {
            populateView()
        }

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(R.string.change_head)
                .setView(contentView)
                .setPositiveButton(R.string.button_save) { dialog, which ->
                    cardData = collectCardData()
                    (requireActivity() as MainActivity).updateHeadingFragment(cardData)
                    dismiss()
                }
        return builder.create()
    }

    private fun collectCardData(): CardData  {
        val head: String = this.userHeadText?.text.toString().trim()
        val date: Date = getCurrentTimeStamp()
        return if (cardData != null) {
            val answer = CardData(head, date, cardData!!.favorite)
            answer.id = cardData!!.id
            return answer
        } else CardData(head, date, false)
    }

    private fun getCurrentTimeStamp(): Date {
//        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy HH:mm")
        return Date()
    }

    private fun populateView() {
        userHeadText?.hint = cardData?.head ?: ""
    }

    private fun init(view: View) {
        userHeadText = view.findViewById(R.id.new_note_name)
    }



}
