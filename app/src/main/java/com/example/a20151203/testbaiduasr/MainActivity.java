package com.example.a20151203.testbaiduasr;

import android.content.ComponentName;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.speech.VoiceRecognitionService;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements RecognitionListener {



    private SpeechRecognizer speechRecognizer;

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建识别器
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this, new ComponentName(this, VoiceRecognitionService.class));
        // 创建监听识别
        speechRecognizer.setRecognitionListener(this);

        startASR();
    }

    private void startASR() {
        Intent intent = new Intent();
        bindParams(intent);
        speechRecognizer.startListening(intent);
    }


    void bindParams(Intent intent) {
        // 设置识别参数
        intent.putExtra("sample", 16000); // 离线仅支持16000采样率
        intent.putExtra("language", "cmn-Hans-CN"); // 离线仅支持中文普通话
        intent.putExtra("prop", 20000); // 输入
//    intent.putExtra("prop", 10060); // 地图
//    intent.putExtra("prop", 10001); // 音乐
//    intent.putExtra("prop", 10003); // 应用
//    intent.putExtra("prop", 10008); // 电话
//    intent.putExtra("prop", 100014); // 联系人
//    intent.putExtra("prop", 100016); // 手机设置
    //    intent.putExtra("prop", 100018); // 电视指令
//    intent.putExtra("prop", 100019); // 播放器指令
//    intent.putExtra("prop", 100020); // 收音机指令
//    intent.putExtra("prop", 100021); // 命令词
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {
        Log.d(TAG, "onReadyForSpeech: ");
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.d(TAG, "onBeginningOfSpeech: ");

    }

    @Override
    public void onRmsChanged(float v) {
        Log.d(TAG, "onRmsChanged: ");
    }

    @Override
    public void onBufferReceived(byte[] bytes) {
        Log.d(TAG, "onBufferReceived: ");
    }

    @Override
    public void onEndOfSpeech() {
        Log.d(TAG, "onEndOfSpeech: ");
    }

    @Override
    public void onError(int i) {
        Log.d(TAG, "onError: ");
    }

    @Override
    public void onResults(Bundle bundle) {
        Log.d(TAG, "onResults: ");
        ArrayList<String> nbest = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        ;
        Log.d("YIYN", "----" + "识别成功：" + Arrays.toString(nbest.toArray(new String[nbest.size()])));
    }

    @Override
    public void onPartialResults(Bundle bundle) {
        Log.d(TAG, "onPartialResults: ");
    }

    @Override
    public void onEvent(int i, Bundle bundle) {
        Log.d(TAG, "onEvent: ");
    }

}
