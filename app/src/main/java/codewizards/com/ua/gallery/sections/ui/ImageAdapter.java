package codewizards.com.ua.gallery.sections.ui;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.model.ui.GalleryImage;
import codewizards.com.ua.gallery.sections.gallery.OnImageActionListener;
import codewizards.com.ua.gallery.util.FavoriteAnimHelper;

/**
 * Created by Интернет on 19.01.2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{
    private List<GalleryImage> imageList;
    private OnImageActionListener listener;

    public ImageAdapter(OnImageActionListener listener) {
        this.listener = listener;
    }

    public void setImageList(List<GalleryImage> imageList) {
        this.imageList = imageList;
    }
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.init();
     }

    @Override
    public int getItemCount() {
        return imageList != null ? imageList.size() : 0;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPic;
        TextView tvDate;
        TextView tvTitle;
        ImageView ivFavorite;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.ivPic);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ivFavorite = (ImageView) itemView.findViewById(R.id.ivFavorite);
        }

        public void init() {
            final GalleryImage image = imageList.get(getAdapterPosition());
            Glide.with(ivPic.getContext()).load(image.getUrl()).into(ivPic);
            tvTitle.setText(image.getName());
            Date date = new Date(image.getDate());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            tvDate.setText(simpleDateFormat.format(date));

            if(image.isFavorite()) {
                ivFavorite.setImageDrawable(ContextCompat.getDrawable(ivFavorite.getContext(), R.drawable.ic_favorite_full));
            } else {
                ivFavorite.setImageDrawable(ContextCompat.getDrawable(ivFavorite.getContext(), R.drawable.ic_favorite_empty));
            }

            ivFavorite.setOnClickListener(v -> {
                GalleryImage img = imageList.get(getAdapterPosition());
                if(img.isFavorite()) {
                    img.setFavorite(false);
                    listener.onImageUnfavorite(img);
                    FavoriteAnimHelper.animageUnfavorite(ivFavorite);
                } else {
                    img.setFavorite(true);
                    listener.onImageFavorite(img);
                    FavoriteAnimHelper.animateAddFavorite(ivFavorite);
                }
            });

            itemView.setOnClickListener(v -> {
                GalleryImage img = imageList.get(getAdapterPosition());
                listener.onImageSelected(img);
            });
        }

    }
}
