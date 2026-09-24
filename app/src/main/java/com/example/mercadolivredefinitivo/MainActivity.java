package com.example.mercadolivredefinitivo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Click on Favorites in Bottom Navigation -> Go to MainActivity2
        LinearLayout navFavoritesHome = findViewById(R.id.navFavoritesHome);
        if (navFavoritesHome != null) {
            navFavoritesHome.setOnClickListener(v -> openFavoritesScreen());
        }

        // Click on Ofertas Category -> Go to MainActivity2
        LinearLayout btnOfertasCategory = findViewById(R.id.btnOfertasCategory);
        if (btnOfertasCategory != null) {
            btnOfertasCategory.setOnClickListener(v -> openFavoritesScreen());
        }
    }

    private void openFavoritesScreen() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}