package com.example.mercadolivredefinitivo;

public class FavoriteProduct {
    private final String id;
    private final String title;
    private final String price;
    private final String installments;
    private final String shipping;
    private final int imageResId;
    private boolean isFavorite;

    public FavoriteProduct(String id, String title, String price, String installments, String shipping, int imageResId, boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.installments = installments;
        this.shipping = shipping;
        this.imageResId = imageResId;
        this.isFavorite = isFavorite;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getPrice() { return price; }
    public String getInstallments() { return installments; }
    public String getShipping() { return shipping; }
    public int getImageResId() { return imageResId; }
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
}