package com.kingnet.common.base;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 *
 * Created by livvy on 16-12-30.
 */

public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment,int frameId){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId,fragment);
        transaction.commit();
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment,int frameId,
                                             @NonNull String tag){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId,fragment,tag);
        transaction.commit();
    }
}
