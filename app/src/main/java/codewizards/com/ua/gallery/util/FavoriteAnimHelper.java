package codewizards.com.ua.gallery.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import codewizards.com.ua.gallery.R;

/**
 * Created by User on 27.02.2017.
 */

public class FavoriteAnimHelper {

    public static void animateAddFavorite(ImageView view) {
        view.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_favorite_full));
        view.animate().scaleX(1.3f).scaleY(1.3f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationCancel(animation);
                view.animate().scaleX(1f).scaleY(1f);
            }
        });
    }

    public static void animageUnfavorite(ImageView view) {
        view.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_favorite_empty));
        view.animate().scaleX(0.8f).scaleY(0.8f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationCancel(animation);
                view.animate().scaleX(1f).scaleY(1f);
            }
        });
    }

}
