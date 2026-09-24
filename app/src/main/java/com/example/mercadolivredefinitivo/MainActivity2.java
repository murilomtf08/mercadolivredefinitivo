package com.example.mercadolivredefinitivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements FavoritesAdapter.OnFavoriteChangeListener {

    private RecyclerView recyclerViewFavorites;
    private TextView tvFavoriteCount;
    private View emptyStateLayout;
    private final List<FavoriteProduct> productList = new ArrayList<>();
    private FavoritesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        recyclerViewFavorites = findViewById(R.id.recyclerViewFavorites);
        tvFavoriteCount = findViewById(R.id.tvFavoriteCount);
        emptyStateLayout = findViewById(R.id.emptyStateLayout);

        // Setup Sample Favorite Products
        setupProductList();

        // Setup Adapter & RecyclerView
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavoritesAdapter(this, productList, this);
        recyclerViewFavorites.setAdapter(adapter);

        // Update Count Display
        updateFavoriteCount();

        // Setup Bottom Navigation
        setupBottomNavigation();
    }

    private void setupProductList() {
        productList.add(new FavoriteProduct(
                "1",
                "Smartphone Samsung Galaxy A54 128GB",
                "R$ 1.399",
                "10x R$ 139,90 sem juros",
                "Frete grátis ⚡ FULL",
                R.drawable.captura_de_tela_2026_09_24__s_15_14_59,
                true
        ));

        productList.add(new FavoriteProduct(
                "2",
                "Fone de Ouvido Sem Fio Bluetooth",
                "R$ 129",
                "6x R$ 21,50 sem juros",
                "Frete grátis",
                R.drawable.captura_de_tela_2026_09_24__s_15_12_19,
                true
        ));

        productList.add(new FavoriteProduct(
                "3",
                "Smart TV 50\" 4K Ultra HD",
                "R$ 2.199",
                "10x R$ 219,90 sem juros",
                "Frete grátis ⚡ FULL",
                R.drawable.captura_de_tela_2026_09_24__s_15_16_24,
                true
        ));

        productList.add(new FavoriteProduct(
                "4",
                "Tênis Esportivo Corrida Confortável",
                "R$ 189",
                "3x R$ 63,00 sem juros",
                "Frete grátis",
                R.drawable.captura_de_tela_2026_09_24__s_15_18_14,
                true
        ));
    }

    private void updateFavoriteCount() {
        int favoriteCount = 0;
        for (FavoriteProduct product : productList) {
            if (product.isFavorite()) {
                favoriteCount++;
            }
        }

        if (favoriteCount == 1) {
            tvFavoriteCount.setText("1 produto salvo");
        } else {
            tvFavoriteCount.setText(favoriteCount + " produtos salvos");
        }

        if (favoriteCount == 0) {
            emptyStateLayout.setVisibility(View.VISIBLE);
            recyclerViewFavorites.setVisibility(View.GONE);
        } else {
            emptyStateLayout.setVisibility(View.GONE);
            recyclerViewFavorites.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFavoriteChanged() {
        updateFavoriteCount();
    }

    private void setupBottomNavigation() {
        LinearLayout navHome = findViewById(R.id.navHome);
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}