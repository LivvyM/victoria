package cc.livvy.live.victoria.app.presentation;

import android.app.Activity;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.faucamp.simplertmp.RtmpHandler;
import com.seu.magicfilter.utils.MagicFilterType;

import net.ossrs.yasea.SrsCameraView;
import net.ossrs.yasea.SrsEncodeHandler;
import net.ossrs.yasea.SrsPublisher;
import net.ossrs.yasea.SrsRecordHandler;

import java.io.IOException;
import java.net.SocketException;

import cc.livvy.live.victoria.R;
import cc.livvy.widget.image.ImageViewUtils;

/**
 * Created by livvy on 2017/12/01.
 */

public class CameraActivity extends Activity implements SrsEncodeHandler.SrsEncodeListener, RtmpHandler.RtmpListener, SrsRecordHandler.SrsRecordListener, View.OnClickListener {

    private Button mCameraSwitchBtn;
    private Button mEncoderBtn;
    private SrsPublisher mPublisher;
    private String rtmpUrl = "rtmp://192.168.90.55:1935/live/livestream";

    private ImageView mImageStart;

    private boolean isStart = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_camera);

        ImageView mImageHeader = findViewById(R.id.mImageHeader);
        ImageViewUtils.bindCircleImageView(mImageHeader, "http://img3.imgtn.bdimg.com/it/u=3067862065,1178074544&fm=27&gp=0.jpg");

        mCameraSwitchBtn = findViewById(R.id.swCam);
        mEncoderBtn = findViewById(R.id.swEnc);
        mImageStart = findViewById(R.id.mImageStart);

        mImageStart.setOnClickListener(this);
        mCameraSwitchBtn.setOnClickListener(this);
        mEncoderBtn.setOnClickListener(this);

        mPublisher = new SrsPublisher((SrsCameraView) findViewById(R.id.glsurfaceview_camera));
        //编码状态回调
        mPublisher.setEncodeHandler(new SrsEncodeHandler(this));
        mPublisher.setRecordHandler(new SrsRecordHandler(this));
        //rtmp推流状态回调
        mPublisher.setRtmpHandler(new RtmpHandler(this));
        //预览分辨率
        mPublisher.setPreviewResolution(1280, 720);
        //推流分辨率
        mPublisher.setOutputResolution(720, 1280);
        //传输率
        mPublisher.setVideoHDMode();
        //开启美颜（其他滤镜效果在MagicFilterType中查看）
        mPublisher.switchCameraFilter(MagicFilterType.COOL);
        //打开摄像头，开始预览（未推流）
        mPublisher.startCamera();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //开始/停止推流
            case R.id.mImageStart:
                if (!isStart) {
                    isStart = true;
                    if (TextUtils.isEmpty(rtmpUrl)) {
                        Toast.makeText(getApplicationContext(), "地址不能为空！", Toast.LENGTH_SHORT).show();
                    }
                    mPublisher.startPublish(rtmpUrl);
                    mPublisher.startCamera();

//                    if (mEncoderBtn.getText().toString().contentEquals("软编码")) {
//                        Toast.makeText(getApplicationContext(), "当前使用硬编码", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "当前使用软编码", Toast.LENGTH_SHORT).show();
//                    }
                    mImageStart.setImageResource(R.drawable.ic_live_close);
                    mEncoderBtn.setEnabled(false);
                } else {
                    isStart = false;
                    mPublisher.stopPublish();
                    mPublisher.stopRecord();
                    mImageStart.setImageResource(R.drawable.ic_live_start);
                    mEncoderBtn.setEnabled(true);
                }
                break;
            //切换摄像头
            case R.id.swCam:
                mPublisher.switchCameraFace((mPublisher.getCamraId() + 1) % Camera.getNumberOfCameras());
                break;
            //切换编码方式
            case R.id.swEnc:
                if (mEncoderBtn.getText().toString().contentEquals("软编码")) {
                    mPublisher.switchToSoftEncoder();
                    mEncoderBtn.setText("硬编码");
                } else if (mEncoderBtn.getText().toString().contentEquals("硬编码")) {
                    mPublisher.switchToHardEncoder();
                    mEncoderBtn.setText("软编码");
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPublisher.resumeRecord();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPublisher.pauseRecord();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPublisher.stopPublish();
        mPublisher.stopRecord();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPublisher.stopEncode();
        mPublisher.stopRecord();
        mPublisher.setScreenOrientation(newConfig.orientation);
        if (!isStart) {
            mPublisher.startEncode();
        }
        mPublisher.startCamera();
    }

    @Override
    public void onNetworkWeak() {
        Toast.makeText(getApplicationContext(), "网络型号弱", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkResume() {

    }

    @Override
    public void onEncodeIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    private void handleException(Exception e) {
        try {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            mPublisher.stopPublish();
            mPublisher.stopRecord();
            isStart = false;
            mImageStart.setImageResource(R.drawable.ic_live_start);
        } catch (Exception e1) {
            //
        }
    }

    @Override
    public void onRtmpConnecting(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpConnected(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpVideoStreaming() {

    }

    @Override
    public void onRtmpAudioStreaming() {

    }

    @Override
    public void onRtmpStopped() {
        Toast.makeText(getApplicationContext(), "已停止", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpDisconnected() {
        Toast.makeText(getApplicationContext(), "未连接服务器", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpVideoFpsChanged(double fps) {

    }

    @Override
    public void onRtmpVideoBitrateChanged(double bitrate) {

    }

    @Override
    public void onRtmpAudioBitrateChanged(double bitrate) {

    }

    @Override
    public void onRtmpSocketException(SocketException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIOException(IOException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIllegalStateException(IllegalStateException e) {
        handleException(e);
    }

    @Override
    public void onRecordPause() {
        Toast.makeText(getApplicationContext(), "Record paused", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordResume() {
        Toast.makeText(getApplicationContext(), "Record resumed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordStarted(String msg) {
        Toast.makeText(getApplicationContext(), "Recording file: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordFinished(String msg) {
        Toast.makeText(getApplicationContext(), "MP4 file saved: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordIOException(IOException e) {
        handleException(e);
    }

    @Override
    public void onRecordIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

}
