package owlsdevelopers.org.passcoder.presentation.util

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class NonClickableFramaLayout: FrameLayout {
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        initView()
    }

    private fun initView(){
        this.setOnClickListener {  }
    }
}