package com.eemf.sirgoingfar.imagefromxml;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView resultImage = findViewById(R.id.iv_generated_image);
        resultImage.setImageDrawable(convertBitmapToDrawable(createBitmapFromLayoutWithText(this)));
    }

    public Bitmap createBitmapFromLayoutWithText(Context context) {

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (mInflater == null)
            return null;

        //Inflate the layout into a view and configure it the way you like
        RelativeLayout view = new RelativeLayout(context);
        mInflater.inflate(R.layout.layout_image_view, view, true);

        //set child views at this point
        ImageView icon = view.findViewById(R.id.iv_icon);
        icon.setImageResource(R.drawable.img_silhouettes);

        TextView txtview_1 = view.findViewById(R.id.tv_text_1);
        txtview_1.setText("Reset");


        //Provide it with a layout params. It should necessarily be wrapping the
        //content as we not really going to have a parent for it.
        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));

        //Pre-measure the view so that height and width don't remain null.
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        //Assign a size and position to the view and all of its descendants
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        //Create the bitmap
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        //Create a canvas with the specified bitmap to draw into
        Canvas c = new Canvas(bitmap);

        //Render this view (and all of its children) to the given Canvas
        view.draw(c);
        return bitmap;
    }

    Drawable convertBitmapToDrawable(@NonNull Bitmap bitmap){
        return new BitmapDrawable(getResources(), bitmap);
    }
}
