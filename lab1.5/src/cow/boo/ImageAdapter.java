package cow.boo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    int mGalleryItemBackground;
    private Context mContext;

    private static int[] pictureIds = { R.drawable.bear, R.drawable.cat, R.drawable.chicken, R.drawable.chimpansee,
            R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.elephant, R.drawable.frog, R.drawable.goat,
            R.drawable.goose, R.drawable.horse, R.drawable.kitten, R.drawable.lion, R.drawable.monkey, R.drawable.pig,
            R.drawable.rooster, R.drawable.seal, R.drawable.sheep, R.drawable.tiger, R.drawable.turkey,
            R.drawable.whale, R.drawable.wolf };

    public ImageAdapter(Context context) {
        mContext = context;
    }

    public int getCount() {
        return pictureIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView value = new ImageView(mContext);
        value.setImageResource(pictureIds[position]);
        value.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT,
                Gallery.LayoutParams.MATCH_PARENT));
        value.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return value;
    }
}