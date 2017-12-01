package cc.livvy.widget.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import cc.livvy.widget.R

/**
 *
 * Created by livvy on 17-11-1.
 */
class JcDialogPlus {


    companion object {
        val YES = -1

        val CANCEL = -2
    }

    /**
     * 显示带有单个按钮且无法点击返回键取消的对话框
     */
    fun showDialogSingleBtn(context: Context, title: String, msg: String, btnConfirm: String, dialogCallBack: DialogCallBack?): Dialog {
        val builder = AlertDialog.Builder(context).setTitle(title).setMessage(msg).setNegativeButton(btnConfirm) { dialog, which ->
            if (dialogCallBack != null) {
                dialogCallBack.onClick(YES)
            }
        }
        builder.setCancelable(false)
        return builder.show()
    }

    /**
     * isCancel-是否取消
     */
    fun showDialog(context: Context, title: String, msg: String, btnConfirm: String, btnCancel: String, isCancel: Boolean, dialogCallBack: DialogCallBack): Dialog {
        return if (isCancel)
            showDialog(context, title, msg, btnConfirm, btnCancel, dialogCallBack)
        else
            showDialogNoCancel(context, title, msg, btnConfirm, btnCancel, dialogCallBack)
    }

    /**
     * 显示自定义确定和取消按钮的对话框，对话框无法点击返回键取消
     */
    fun showDialogNoCancel(context: Context, title: String, msg: String, btnConfirm: String, btnCancel: String, dialogCallBack: DialogCallBack?): Dialog {
        val builder = AlertDialog.Builder(context).setTitle(title).setMessage(msg).setNegativeButton(btnCancel) { dialog, which ->
            if (dialogCallBack != null) {
                dialogCallBack.onClick(CANCEL)
            }
        }
        if (dialogCallBack != null) {
            builder.setPositiveButton(btnConfirm) { dialog, which ->
                dialogCallBack.onClick(YES)
            }
        }
        builder.setCancelable(false)
        return builder.show()
    }

    fun showDialog(context: Context, title: String, msg: String, btnConfirm: String, btnCancel: String, dialogCallBack: DialogCallBack?): Dialog {
        val builder = AlertDialog.Builder(context).setTitle(title).setMessage(msg).setNegativeButton(btnCancel) { dialog, which ->
            if (dialogCallBack != null) {
                dialogCallBack.onClick(CANCEL)
            }
        }
        if (!TextUtils.isEmpty(btnConfirm)) {
            builder.setPositiveButton(btnConfirm) { dialog, which ->
                if (dialogCallBack != null) {
                    dialogCallBack.onClick(YES)
                }
            }
        }
        return builder.show()
    }

    fun showDialog(context: Context, title: String, msg: String, dialogCallBack: DialogCallBack?): Dialog {
        val builder = AlertDialog.Builder(context).setTitle(title).setMessage(msg).setNegativeButton(context.getString(R.string.cancel)) { dialog, which ->
            if (dialogCallBack != null) {
                dialogCallBack.onClick(CANCEL)
            }
        }
        builder.setPositiveButton(context.getString(R.string.confirm)) { dialog, which ->
            if (dialogCallBack != null) {
                dialogCallBack.onClick(YES)
            }
        }
        return builder.show()
    }
}