package cow.boo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;

public class MainActivity extends Activity {

    private MediaPlayer mp;
    
    private static int[] soundIds = { R.raw.bear, R.raw.cat, R.raw.chicken, R.raw.chimpansee, R.raw.cow, R.raw.dog,
        R.raw.donkey, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.kitten, R.raw.lion,
        R.raw.monkey, R.raw.pig, R.raw.rooster, R.raw.seal, R.raw.sheep, R.raw.tiger, R.raw.turkey, R.raw.whale,
        R.raw.wolf };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                playSound(position);
            }
        });
    }

    private void playSound(int position) {
        if (mp != null) {
            mp.release();
        }
        mp = MediaPlayer.create(this, soundIds[position]);
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