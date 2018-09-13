package com.stone.stoneprogressbar.activity;

import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.stone.stoneprogressbar.R;
import com.stone.stoneprogressbar.utils.NfcUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class NFCActivity extends AppCompatActivity {
    private String mPackageName = "com.android.mms";//短信
    private static final String TAG = NFCActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设定intentfilter和tech-list。如果两个都为null就代表优先接收任何形式的TAG action。也就是说系统会主动发TAG intent。
        if (NfcUtils.mNfcAdapter != null) {
            NfcUtils.mNfcAdapter.enableForegroundDispatch(this, NfcUtils.mPendingIntent, NfcUtils.mIntentFilter, NfcUtils.mTechList);
        }
    }

    //在onNewIntent中处理由NFC设备传递过来的intent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "--------------NFC-------------");
        getNfcTag(intent);

    }

    private void getNfcTag(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        writeNFCTag(tag);
    }

    /**
     * 往标签写数据的方法
     *
     * @param tag
     */
    public void writeNFCTag(Tag tag) {
        if (tag == null) {
            return;
        }
        NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{NdefRecord
                .createApplicationRecord(mPackageName)});
        //转换成字节获得大小
        int size = ndefMessage.toByteArray().length;
        try {
            //2.判断NFC标签的数据类型（通过Ndef.get方法）
            Ndef ndef = Ndef.get(tag);
            //判断是否为NDEF标签
            if (ndef != null) {
                ndef.connect();
                //判断是否支持可写
                if (!ndef.isWritable()) {
                    return;
                }
                //判断标签的容量是否够用
                if (ndef.getMaxSize() < size) {
                    return;
                }
                //3.写入数据
                ndef.writeNdefMessage(ndefMessage);
                Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
            } else { //当我们买回来的NFC标签是没有格式化的，或者没有分区的执行此步
                //Ndef格式类
                NdefFormatable format = NdefFormatable.get(tag);
                //判断是否获得了NdefFormatable对象，有一些标签是只读的或者不允许格式化的
                if (format != null) {
                    //连接
                    format.connect();
                    //格式化并将信息写入标签
                    format.format(ndefMessage);
                    Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "写入失败", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
        }
    }

    //  这块的processIntent() 就是处理卡中数据的方法
    public void processIntent(Intent intent) {
        Parcelable[] rawmsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage msg = (NdefMessage) rawmsgs[0];
        NdefRecord[] records = msg.getRecords();
        String resultStr = new String(records[0].getPayload());
        // 返回的是NFC检查到卡中的数据
        Log.e(TAG, "processIntent: " + resultStr);
        try {
            // 检测卡的id
            String id = NfcUtils.readNFCId(intent);
            Log.e(TAG, "processIntent--id: " + id);
            // NfcUtils中获取卡中数据的方法
            String result = NfcUtils.readNFCFromTag(intent);
            Log.e(TAG, "processIntent--result: " + result);
            // 往卡中写数据
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            String data = "this.is.write";
            NfcUtils.writeNFCToTag(data, intent);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (NfcUtils.mNfcAdapter != null) {
            NfcUtils.mNfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NfcUtils.mNfcAdapter = null;
    }
}
