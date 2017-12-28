package id.web.rizki.apotikku.Holder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.web.rizki.apotikku.Interface.ItemClickListener;
import id.web.rizki.apotikku.Model.Order;
import id.web.rizki.apotikku.R;

/**
 * Created by rizki on 20/12/17.
 */

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_cart_name,txt_harga;
    public ImageView img_cart_count;

    private ItemClickListener itemClickListener;

    public void setTxt_cart_name(TextView txt_cart_name) {
        this.txt_cart_name = txt_cart_name;
    }

    public void setTxt_harga(TextView txt_harga) {
        this.txt_harga = txt_harga;
    }

    public CartViewHolder(View itemView) {
        super(itemView);
        txt_cart_name = (TextView) itemView.findViewById(R.id.cart_item_nama);
        txt_harga = (TextView) itemView.findViewById(R.id.cart_item_harga);
        img_cart_count = (ImageView) itemView.findViewById(R.id.cart_item_count);

    }

    @Override
    public void onClick(View view) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<Order> ListData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        ListData = listData;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout,parent,false);


        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+ListData.get(position).getQuantity(), Color.RED);
        holder.img_cart_count.setImageDrawable(drawable);

        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int harga = (Integer.parseInt(ListData.get(position).getPrice()) * (Integer.parseInt(ListData.get(position).getQuantity())));
        holder.txt_harga.setText(fmt.format(harga));
        holder.txt_cart_name.setText(ListData.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }
}
