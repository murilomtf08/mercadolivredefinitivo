package com.example.mercadolivredefinitivo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    public interface OnFavoriteChangeListener {
        void onFavoriteChanged();
    }

    private final Context context;
    private final List<FavoriteProduct> productList;
    private final OnFavoriteChangeListener listener;

    public FavoritesAdapter(Context context, List<FavoriteProduct> productList, OnFavoriteChangeListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite_product, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoriteProduct product = productList.get(position);

        holder.imgProduct.setImageResource(product.getImageResId());
        holder.tvTitle.setText(product.getTitle());
        holder.tvPrice.setText(product.getPrice());
        holder.tvInstallments.setText(product.getInstallments());
        holder.tvShipping.setText(product.getShipping());

        // Heart Icon State
        updateHeartIcon(holder.btnFavorite, product.isFavorite());

        // Toggle Favorite Action
        holder.btnFavorite.setOnClickListener(v -> {
            boolean newState = !product.isFavorite();
            product.setFavorite(newState);
            updateHeartIcon(holder.btnFavorite, newState);

            if (newState) {
                Toast.makeText(context, "Adicionado aos favoritos: " + product.getTitle(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Removido dos favoritos: " + product.getTitle(), Toast.LENGTH_SHORT).show();
            }

            if (listener != null) {
                listener.onFavoriteChanged();
            }
        });

        // Share Action
        holder.btnShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareMessage = "Confira este produto no Mercado Livre!\n" +
                    product.getTitle() + "\nPor apenas " + product.getPrice() +
                    "\n\nhttps://www.mercadolivre.com.br";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, "Compartilhar produto via"));

            Toast.makeText(context, "Compartilhando " + product.getTitle(), Toast.LENGTH_SHORT).show();
        });

        // Add to Cart Action
        holder.btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(context, "Adicionado ao carrinho: " + product.getTitle(), Toast.LENGTH_SHORT).show();
        });
    }

    private void updateHeartIcon(ImageView imageView, boolean isFavorite) {
        if (isFavorite) {
            imageView.setImageResource(R.drawable.ic_heart_filled);
        } else {
            imageView.setImageResource(R.drawable.ic_heart_outline);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvTitle, tvPrice, tvInstallments, tvShipping, btnAddToCart;
        ImageView btnFavorite, btnShare;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvInstallments = itemView.findViewById(R.id.tvInstallments);
            tvShipping = itemView.findViewById(R.id.tvShipping);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
            btnShare = itemView.findViewById(R.id.btnShare);
        }
    }
}