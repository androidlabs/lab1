package cow.boo;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Animation customAnimation = AnimationUtils.loadAnimation(this, R.anim.custom_anim);
        ImageView splashIcon = (ImageView) findViewById(R.id.splash_icon);
        splashIcon.startAnimation(customAnimation);
        
    }
}