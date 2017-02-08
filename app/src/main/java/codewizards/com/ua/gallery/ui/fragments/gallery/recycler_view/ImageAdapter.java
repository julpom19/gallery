package codewizards.com.ua.gallery.ui.fragments.gallery.recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import codewizards.com.ua.gallery.model.Image;

/**
 * Created by Интернет on 19.01.2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{
    List<Image> imageList;
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        if(holder == null) {

        }
     }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageViewHolder(View itemView) {
            super(itemView);
        }


    }
}
