package alarish.hicss.hicss;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {

    private static final String TAG = AboutActivity.class.getSimpleName();

    @BindString(R.string.facebook_page_url)
    String mFacebookPageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.contactFacebookPageButton)
    public void onFaceBookButtonClick() {
        startActivity(getFacebookIntent());
    }

    private Intent getFacebookIntent() {
        try {
            if (getFacebookApplicationInfo().enabled) {
                Uri facebookUri = Uri.parse("fb://facewebmodal/f?href=" + mFacebookPageUrl);
                return new Intent(Intent.ACTION_VIEW, facebookUri);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, e.getMessage());
        }
        return new Intent(Intent.ACTION_VIEW, Uri.parse(mFacebookPageUrl));
    }

    private ApplicationInfo getFacebookApplicationInfo() throws PackageManager.NameNotFoundException {
        return getPackageManager().getApplicationInfo("com.facebook.katana", 0);
    }
}
