package cc.livvy.live.victoria.app.presentation

import android.os.Bundle
import android.view.View
import cc.livvy.live.victoria.R
import cc.livvy.live.victoria.base.BaseActivity
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import kotlinx.android.synthetic.main.activity_input_url_detail.*
import com.shuyu.gsyvideoplayer.listener.LockClickListener
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer



/**
 *
 * Created by livvy on 17-12-1.
 */
class PlayActivity : BaseActivity(){

    private val cache: Boolean = false
    private val url = "rtmp://192.168.90.55:1935/live/livestream"

    private var orientationUtils: OrientationUtils? = null
    private var gsyVideoOptionBuilder: GSYVideoOptionBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_url_detail)

        resolveNormalVideoUI()
        //外部辅助的旋转，帮助全屏
        orientationUtils = OrientationUtils(this, detailPlayer)
        //初始化不打开外部的旋转
        orientationUtils?.isEnable = false

        gsyVideoOptionBuilder = GSYVideoOptionBuilder()
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1f)
                .setUrl(url)
                .setCacheWithPlay(cache)
                .setVideoTitle("test")
        gsyVideoOptionBuilder?.build(detailPlayer)

        detailPlayer.postDelayed({ detailPlayer.startPlayLogic() }, 1000)

        detailPlayer.fullscreenButton.setOnClickListener({
            //直接横屏
            orientationUtils?.resolveByClick()

            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            detailPlayer.startWindowFullscreen(this@PlayActivity, true, true)
        })

        detailPlayer.setLockClickListener(object : LockClickListener {
            override fun onClick(view: View, lock: Boolean) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils?.setEnable(!lock)
                }
            }
        })

    }

    private fun resolveNormalVideoUI() {
        //增加title
        detailPlayer.titleTextView.visibility = View.GONE
        detailPlayer.backButton.visibility = View.GONE
    }

    private fun getCurPlay(): GSYVideoPlayer {
        return if (detailPlayer.fullWindowPlayer != null) {
            detailPlayer.fullWindowPlayer
        } else detailPlayer
    }

    override fun onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils?.backToProtVideo()
        }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }


    override fun onPause() {
        getCurPlay().onVideoPause()
        super.onPause()
    }

    override fun onResume() {
        getCurPlay().onVideoResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils?.releaseListener()
    }

}