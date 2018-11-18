package google.projectbuilding.fishmarket_v1.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageView extends android.support.v7.widget.AppCompatImageView {

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
        // https://stackoverflow.com/questions/16506275/imageview-be-a-square-with-dynamic-width
        // https://www.ryadel.com/en/android-proportionally-stretch-imageview-fit-whole-screen-width-maintaining-aspect-ratio/
        // https://stackoverflow.com/questions/2991110/android-how-to-stretch-an-image-to-the-screen-width-while-maintaining-aspect-ra/2997760#2997760
    }
}