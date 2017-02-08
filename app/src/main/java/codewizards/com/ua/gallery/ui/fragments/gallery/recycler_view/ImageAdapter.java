package codewizards.com.ua.gallery.ui.fragments.gallery.recycler_view;

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
import codewizards.com.ua.gallery.model.Image;

/**
 * Created by Интернет on 19.01.2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{
    List<Image> imageList;
    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.init();
     }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPic;
        TextView tvDate;
        TextView tvTitle;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.ivPic);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }

        public void init() {
            Image image = imageList.get(getAdapterPosition());
            Glide.with(ivPic.getContext()).load(image.getUrl()).into(ivPic);
            tvTitle.setText(image.getName());
            Date date = new Date(image.getDate());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
            tvDate.setText(simpleDateFormat.format(date));

        }

    }
}
