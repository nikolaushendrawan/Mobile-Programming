package umn.ac.utsnew;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<MusicFile> mFile;

    MusicAdapter(Context mContext, ArrayList<MusicFile> mFile)
    {
        this.mFile = mFile;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.music_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.file_name.setText(mFile.get(position).getTitle());
    byte[] image = getAlbumArt(mFile.get(position).getPath());
    if (image != null)
    {
        Glide.with(mContext).asBitmap()
                .load(image)
                .into(holder.album_art);
    }
    else {
        Glide.with(mContext)
                .load(R.drawable.smile)
                .into(holder.album_art);
    }
    holder.itemView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(mContext, MusicPlayerActivity.class);
            intent.putExtra("position", position);
                    mContext.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return mFile.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView file_name;
        ImageView album_art;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        file_name = itemView.findViewById(R.id.music_file_name);
        album_art = itemView.findViewById(R.id.music_img);
    }
    }
    private byte[] getAlbumArt (String uri)
    {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}
