package id.web.rizki.apotikku.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import id.web.rizki.apotikku.Interface.ItemClickListener;
import id.web.rizki.apotikku.R;

/**
 * Created by rizki on 19/12/17.
 */

public class ObatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView namaObat;
    public ImageView imgObat;
    public TextView deskripsi;
    public TextView status;
    public TextView harga;

    private ItemClickListener itemClickListener;



    public ObatViewHolder(View itemView) {
        super(itemView);

        namaObat = itemView.findViewById(R.id.nama_obat);
        imgObat = itemView.findViewById(R.id.img_obat);
        deskripsi = itemView.findViewById(R.id.deskripsi);
        status = itemView.findViewById(R.id.status);
        harga = itemView.findViewById(R.id.harga);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
