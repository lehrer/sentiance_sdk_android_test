package be.gershon_lehrer.sentiance_sdk_test;

import android.app.Application;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.sentiance.sdk.OnInitCallback;
import com.sentiance.sdk.OnStartFinishedHandler;
import com.sentiance.sdk.SdkConfig;
import com.sentiance.sdk.SdkStatus;
import com.sentiance.sdk.Sentiance;

/**
 * Created by gershonlehrer on 18/09/2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // PendingIntent that will start your application's MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        // Create new notification
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.app_name) + " is running")
                .setContentText("Touch to open.")
                .setShowWhen(false)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .build();


        final SdkConfig sdkConfig = new SdkConfig.Builder("59c02717d0eae70500000086", "96dfc55396478c6d0d8ea20acb1f5478e110c842c188699c132c15479c9de60faac33d9d194bf2597468b83c3ffbb4c1ec48147a9f22f4cf66642fbf6f293350")
                .enableForeground(notification)
                .build();
        OnInitCallback initCallback = new OnInitCallback() {
            @Override
            public void onInitSuccess() {
                Sentiance.getInstance(getApplicationContext()).start(new OnStartFinishedHandler() {
                    @Override
                    public void onStartFinished(SdkStatus sdkStatus) {
                        Toast.makeText(getApplicationContext(),sdkStatus.toString(),Toast.LENGTH_LONG).show();
                        }
                    });
                }

            @Override
            public void onInitFailure(InitIssue issue) {
            }
        };

        Sentiance.getInstance(this).init(sdkConfig, initCallback);


    }


}
