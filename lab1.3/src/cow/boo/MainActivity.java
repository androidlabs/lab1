package cow.boo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private MediaPlayer mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        ImageView cowImage = (ImageView) findViewById(R.id.cow);
        cowImage.setOnClickListener(this);
        
	}

	@Override
	public void onClick(View v) {
		if (mp != null) {
			mp.release();
		}
		mp = MediaPlayer.create(this, R.raw.cow);
		mp.start();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (mp != null) {
			mp.release();
		}
	}

}