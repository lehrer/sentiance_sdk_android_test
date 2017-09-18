package be.gershon_lehrer.sentiance_sdk_test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sentiance.sdk.Sentiance;

public class MainActivity extends AppCompatActivity {
    private TextView mUserIDTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserIDTextView=(TextView)findViewById(R.id.user_id_textview);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // We need to ask the user to grant permission. We've offloaded that to a different activity for clarity.
            startActivity(new Intent(this, PermissionCheckActivity.class));
        }

        String sentianceUserID=Sentiance.getInstance(this).getUserId();
        final String sentianceVersion = Sentiance.getInstance(this).getVersion();

        mUserIDTextView.setText(getString(R.string.sentiance_user_id_with_param,sentianceUserID));

        mUserIDTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserIDTextView.setText(getString(R.string.sentiance_version_with_param,sentianceVersion));
            }
        });
    }
}
