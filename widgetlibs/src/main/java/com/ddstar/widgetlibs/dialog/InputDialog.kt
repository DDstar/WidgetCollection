package com.ddstar.widgetlibs.dialog

import android.content.Context
import android.view.View
import com.flyco.dialog.widget.base.BaseDialog

/**
 * Created by DDStar on 2018/11/8.
 */
class InputDialog(context: Context) : BaseDialog<InputDialog>(context) {

    override fun setUiBeforShow() {

    }

    override fun onCreateView(): View {
        //布局
        return null as View
    }

    interface InputDialogCallBack : BaseCallBack {
        fun onSure(inputContent: String)
    }
}