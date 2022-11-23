package com.example.oyunsayac;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class FragmentKontrol {
    private FragmentManager manager;
    private static FragmentManager manager2;

    private static FragmentKontrol instance = new FragmentKontrol();

    public static FragmentKontrol getInstance() {
        return instance;
    }

    private FragmentKontrol() {
    }

    public void setManager(FragmentManager manager) {
        this.manager = manager;
    }

    public void FragmentReplace(FragmentManager fragmentManager, Fragment fragment, String tag) {
        manager = fragmentManager;
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_yuvasi, fragment, tag);
        transaction.commit();
    }

    public void FragmentReplace(Context context, Fragment fragment, String tag) {
        manager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_yuvasi, fragment, tag);
        transaction.commit();
    }

    public void FragmentReplace2(Context context, Fragment fragment, String tag) {
        Bundle bundle = new Bundle();
        manager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_yuvasi, fragment, tag);
        transaction.commit();
    }

    public void YeniFragmentAc(Context context, Fragment fragment, String tag) {
        manager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_yuvasi, fragment, tag);
        transaction.commit();
    }

    public void ListeyiGuncelleyerekAc(Context context, Fragment fragment, String tag) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.LISTEYI_GUNCELLLE,Constants.LISTEYI_GUNCELLLE);
        fragment.setArguments(bundle);
        manager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_yuvasi, fragment, tag);
        transaction.commit();
    }

    public void FragmentiKapat(Context context, Fragment fragment) {
        manager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }

    public void FragmentiKapat(Context context, String tag) {
        manager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(tag);
        transaction.remove(fragment);
        transaction.commit();
    }

    public void HideFragment(String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(tag);
        transaction.hide(fragment);
        transaction.commit();
    }

    public void ShowFragment(String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(tag);
        transaction.show(fragment);
        transaction.commit();
    }

    public static void FragmentReplace2(FragmentManager fragmentManager, Fragment fragment, String tag) {
        manager2 = fragmentManager;
        FragmentTransaction transaction = manager2.beginTransaction();
        transaction.replace(R.id.fragment_yuvasi, fragment, tag);
        transaction.commit();
    }

    /*
    * veri gonderme
    *
    * Bundle bundle=new Bundle();
    * bundle.putString("gonderi",gonder);
    * fragment.setArguments(bundle);
    *
    *
    * */
}
