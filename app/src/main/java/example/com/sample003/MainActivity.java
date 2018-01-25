package example.com.sample003;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ----- 大事なのは↓ここから↓ ----- //
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        telephonyManager.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String number) {
                // stateに状態を示す数値が、numberには着信時にかかってきた番号が入る
                switch (state) {
                    // 着信時のイベント
                    case TelephonyManager.CALL_STATE_RINGING:
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(
                                number,
                                null,
                                "Hey!!",
                                null,
                                null
                        );
                        break;
                    // 通話開始時のイベント
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        break;
                    // 待機状態への変化時(通話を切った時)のイベント
                    case TelephonyManager.CALL_STATE_IDLE:
                        break;
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
        // ----- ↑ここまで↑ ----- //
    }
}