package codewizards.com.ua.gallery.sections.internet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.model.net.Item;

/**
 * Created by User on 28.02.2017.
 */

public class InternetImagesAdapter extends RecyclerView.Adapter<InternetImagesAdapter.Holder> {

    private List<Item> images;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_net_image, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.init();
    }

    @Override
    public int getItemCount() {
        return images != null ? images.size() : 0;
    }

    class Holder extends RecyclerView.ViewHolder {
        private ImageView ivDownload;
        private TextView tvTitle;
        private ImageView ivPic;

        public Holder(View itemView) {
            super(itemView);
            ivDownload = (ImageView) itemView.findViewById(R.id.ivDownload);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ivPic = (ImageView) itemView.findViewById(R.id.ivPic);
        }
        public void init() {
            Item item = images.get(getAdapterPosition());
            tvTitle.setText(item.title);
            Glide.with(ivPic.getContext()).load(item.link).into(ivPic);
        }
    }

    public void updateData(List<Item> images) {
        this.images = new ArrayList<>(images);
    }
}
