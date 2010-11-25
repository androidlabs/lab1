package com.vxcompany.radrace10;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity implements OnTouchListener {

    private static final int PREVIOUS = -1;

    private static final int NEXT = +1;

    private MediaPlayer mp;

    private SharedPreferences preferences;

    private static final String CURRENT_POSITION = "currentPosition";

    private static final int STARTING_POSITION = 0;

    private static final int MIN_SWIPE_WIDTH = 50;

    private static final String TAG = "TCGB";

    private int[] xCoordinates = { 0, 0 };

    private static int[] pictureIds = { R.drawable.bear, R.drawable.cat, R.drawable.chicken, R.drawable.chimpansee,
            R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.elephant, R.drawable.frog, R.drawable.goat,
            R.drawable.goose, R.drawable.horse, R.drawable.kitten, R.drawable.lion, R.drawable.monkey, R.drawable.pig,
            R.drawable.rooster, R.drawable.seal, R.drawable.sheep, R.drawable.tiger, R.drawable.turkey,
            R.drawable.whale, R.drawable.wolf };

    private static int[] soundIds = { R.raw.bear, R.raw.cat, R.raw.chicken, R.raw.chimpansee, R.raw.cow, R.raw.dog,
            R.raw.donkey, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.kitten, R.raw.lion,
            R.raw.monkey, R.raw.pig, R.raw.rooster, R.raw.seal, R.raw.sheep, R.raw.tiger, R.raw.turkey, R.raw.whale,
            R.raw.wolf };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        preferences = getPreferences(MODE_PRIVATE);

        setStartingPosition();

        ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.image_switcher);
        imageSwitcher.setFactory(new MyImageSwitcherFactory());
        imageSwitcher.setOnTouchListener(this);

        displayCurrentImage();
        playCurrentSound();

    }

    private void setStartingPosition() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CURRENT_POSITION, STARTING_POSITION);
        editor.commit();
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.xCoordinates[0] = (int) event.getX();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            this.xCoordinates[1] = (int) event.getX();
            if (xCoordinates[1] - xCoordinates[0] > MIN_SWIPE_WIDTH) {
                Log.d(TAG, "User swiped right");
                updateCurrentPosition(PREVIOUS);
                updateAnimation(PREVIOUS);
                stopPlayingSounds();
                displayCurrentImage();
            } else if (xCoordinates[0] - xCoordinates[1] > MIN_SWIPE_WIDTH) {
                Log.d(TAG, "User swiped left");
                updateCurrentPosition(NEXT);
                updateAnimation(NEXT);
                stopPlayingSounds();
                displayCurrentImage();
            } else {
                // We handle this as a normal 'click'
                playCurrentSound();
            }
            // Indicate we have handled the onTouch event
            return true;
        }
        return false;
    }

    private void updateAnimation(int delta) {
        ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.image_switcher);
        switch (delta) {
        case PREVIOUS:
            imageSwitcher.setInAnimation(this, R.anim.slide_in_left);
            imageSwitcher.setOutAnimation(this, R.anim.slide_out_right);
            break;
        case NEXT:
            imageSwitcher.setInAnimation(this, R.anim.slide_in_right);
            imageSwitcher.setOutAnimation(this, R.anim.slide_out_left);
            break;
        default:
            imageSwitcher.setInAnimation(this, android.R.anim.fade_in);
            imageSwitcher.setOutAnimation(this, android.R.anim.fade_out);
            break;
        }
    }

    private void updateCurrentPosition(int delta) {
        int currentPosition = getCurrentPosition();
        int updatedPosition = (pictureIds.length + currentPosition + delta) % pictureIds.length;
        Log.d(TAG, "Moving position from " + currentPosition + " to " + updatedPosition);
        Editor editor = preferences.edit();
        editor.putInt(CURRENT_POSITION, updatedPosition);
        editor.commit();
    }

    private void playCurrentSound() {
        stopPlayingSounds();
        int currentPosition = getCurrentPosition();
        int currentSound = soundIds[currentPosition];
        mp = MediaPlayer.create(this, currentSound);
        mp.start();
    }

    private void stopPlayingSounds() {
        if (mp != null) {
            mp.release();
        }
    }

    private int getCurrentPosition() {
        return preferences.getInt(CURRENT_POSITION, 0);
    }

    private void displayCurrentImage() {
        int currentPosition = getCurrentPosition();
        ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.image_switcher);
        imageSwitcher.setImageResource(pictureIds[currentPosition]);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPlayingSounds();
    }

    private class MyImageSwitcherFactory implements ViewFactory {
        public View makeView() {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView
                    .setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            return imageView;
        }
    }

}