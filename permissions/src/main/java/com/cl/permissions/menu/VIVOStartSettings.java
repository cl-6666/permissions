package com.cl.permissions.menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/**
 * 项目：permissions-Text
 * 版权：蒲公英公司 版权所有
 * 作者：Arry
 * 版本：1.0
 * 创建日期：2019-12-03
 * 描述：
 * 修订历史：
 */
public class VIVOStartSettings implements IMenu {

    @Override
    public Intent getStartActivity(Context context) {
        Intent appIntent = context.
                getPackageManager().getLaunchIntentForPackage("coom.iqoo.secure");

        if (appIntent != null && Build.VERSION.SDK_INT < 23) {
            context.startActivity(appIntent);
        }

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Settings.ACTION_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        return intent;
    }
}
