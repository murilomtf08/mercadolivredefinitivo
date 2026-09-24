package com.example.mercadolivredefinitivo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Click on "Início" -> Return to Home Page (MainActivity)
        LinearLayout navHome = findViewById(R.id.navHome);
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
                finish();
            });
        }

        // Click on "Favoritos" -> Go to MainActivity2
        LinearLayout navFavorites = findViewById(R.id.navFavorites);
        if (navFavorites != null) {
            navFavorites.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
                finish();
            });
        }

        // Buttons inside Purchase Card
        TextView btnViewPurchase = findViewById(R.id.btnViewPurchase);
        if (btnViewPurchase != null) {
            btnViewPurchase.setOnClickListener(v ->
                Toast.makeText(this, "Exibindo detalhes da compra do Samsung Galaxy A54", Toast.LENGTH_SHORT).show()
            );
        }

        TextView btnBuyAgain = findViewById(R.id.btnBuyAgain);
        if (btnBuyAgain != null) {
            btnBuyAgain.setOnClickListener(v ->
                Toast.makeText(this, "Adicionado ao carrinho para comprar novamente!", Toast.LENGTH_SHORT).show()
            );
        }
    }
}