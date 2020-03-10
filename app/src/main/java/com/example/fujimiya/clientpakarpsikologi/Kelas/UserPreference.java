package com.example.fujimiya.clientpakarpsikologi.Kelas;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    private String KEY_USERNAME = "username";
    private String KEY_ALAMAT = "alamat";
    private String KEY_FOTO = "foto";
    private String KEY_TOKEN = "token";
    private String KEY_ID_USER = "iduser";
    private String KEY_EMAIL = "email";
    private String KEY_IS_LOGGED_IN = "islogin";
    private String KEY_NAMA = "nama";
    private String KEY_NO_HP = "nope";
    private String KEY_PASSWORD = "password";
    private String KEY_LEVEL = "level";
    private String KEY_DISCLAIMER = "disclaimer";
    private SharedPreferences preferences;

    public UserPreference(Context context){
        String PREFS_NAME = "UserPref";
        preferences = context.getSharedPreferences(PREFS_NAME,context.MODE_PRIVATE);
    }



    public void setUsername(String username){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERNAME,username);
        editor.apply();
    }

    public void setDisclaimer(boolean disclaimer){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_DISCLAIMER,disclaimer);
        editor.apply();
    }

    public void setEmail(String email){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_EMAIL,email);
        editor.apply();
    }

    public void setLevel(String level){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_LEVEL,level);
        editor.apply();
    }


    public void setPassword(String password){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_PASSWORD,password);
        editor.apply();
    }



    public void setNama(String nama){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NAMA,nama);
        editor.apply();
    }

    public void setAlamat(String alamat){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_ALAMAT,alamat);
        editor.apply();
    }


    public void setFoto(String foto){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_FOTO,foto);
        editor.apply();
    }


    public void setNope(String nope){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NO_HP,nope);
        editor.apply();
    }

    public void setToken(String token){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_TOKEN,token);
        editor.apply();
    }

    public void setIdUser(String idUser){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_ID_USER,idUser);
        editor.apply();
    }

    public void setIsLoggedIn(String isLoggedIn){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_IS_LOGGED_IN,isLoggedIn);
        editor.apply();
    }

    public String getIsLoggedIn(){
        return preferences.getString(KEY_IS_LOGGED_IN,null);
    }


    public String getUsername(){
        return preferences.getString(KEY_USERNAME,null);
    }

    public String getEmail(){
        return preferences.getString(KEY_EMAIL,null);
    }

    public String getLevel(){
        return preferences.getString(KEY_LEVEL,null);
    }


    public String getPassword(){
        return preferences.getString(KEY_PASSWORD,null);
    }

    public String getAlamat(){
        return preferences.getString(KEY_ALAMAT,null);
    }


    public String getFoto(){
        return preferences.getString(KEY_FOTO,null);
    }


    public String getIdUser(){
        return preferences.getString(KEY_ID_USER,null);
    }

    public String getToken(){
        return preferences.getString(KEY_TOKEN,null);
    }

    public String getNama(){
        return preferences.getString(KEY_NAMA,null);
    }

    public String getNope(){
        return preferences.getString(KEY_NO_HP,null);
    }

    public boolean getDisclaimer(){
        return preferences.getBoolean(KEY_DISCLAIMER,false);
    }
}