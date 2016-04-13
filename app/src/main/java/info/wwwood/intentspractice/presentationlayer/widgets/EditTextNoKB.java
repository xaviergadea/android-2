package info.wwwood.intentspractice.presentationlayer.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by android-ed1 on 13/04/2016.
 */
public class EditTextNoKB extends EditText {

    public EditTextNoKB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onCheckIsTextEditor(){
        return false;
    }
}
