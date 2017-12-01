package cc.livvy.widget.dialog;

/**
 * Created by lvm on 2016/12/19.
 */

public interface DialogCallBack {

    /**
     * 当是 选择是what为IDialog.YES , IDialog.CANCLE<br/>
     * 当时 item时为对应的item的位置
     */
   void onClick(int what);
}
