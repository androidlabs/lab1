package cow.boo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class SplashActivity extends Activity implements AnimationListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        Animation customAnimation = AnimationUtils.loadAnimation(this, R.anim.custom_anim);
        ImageView splashIcon = (ImageView) findViewById(R.id.splash_icon);
        splashIcon.startAnimation(customAnimation);
        customAnimation.setAnimationListener(this);
    }

	@Override
    public void onAnimationEnd(Animation arg0) {
        this.startActivity(new Intent(this, MainActivity.class));
        this.finish();
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
	}
}