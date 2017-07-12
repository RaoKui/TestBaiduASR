package com.example.a20151203.testbaiduasr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AndroidRuntimeException;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.PrivateKey;
import java.util.HashMap;

/**
 * Created by RaoKui on 2017/7/12.
 */

public class WakeUpActivity extends AppCompatActivity {

    private EventManager mEventManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventManager = EventManagerFactory.create(this, "wake_up");
        mEventManager.registerListener(new EventListener() {
            @Override
            public void onEvent(String name, String params, byte[] bytes, int i, int i1) {
                try {
                    JSONObject json = new JSONObject(params);
                    if ("wp.data".equals(name)) { // 每次唤醒成功, 将会回调name=wp.data的时间, 被激活的唤醒词在params的word字段
                        String word = json.getString("你好"); // 唤醒词
                    } else if ("wp.exit".equals(name)) {
                        // 唤醒已经停止
                    }
                } catch (JSONException e) {
                    throw new AndroidRuntimeException(e);
                }
            }
        });

        // 3) 通知唤醒管理器, 启动唤醒功能
        HashMap params = new HashMap();
        params.put("kws-file", "assets:///WakeUp.bin"); // 设置唤醒资源, 唤醒资源请到 http://yuyin.baidu.com/wake#m4 来评估和导出
        mEventManager.send("wp.start", new JSONObject(params).toString(), null, 0, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEventManager.send("wp.stop", null, null, 0, 0);
    }
}
