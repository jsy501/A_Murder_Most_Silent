package com.noname.mrch.helper;

public class AssetLoader {
    public static AssetLoader Instance = new AssetLoader();

    private AssetLoader() {

    }

    public static AssetLoader getInstance(){
        return Instance;
    }
}
