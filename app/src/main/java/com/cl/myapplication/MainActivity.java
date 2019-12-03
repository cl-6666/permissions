package com.cl.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cl.permissions.annotation.Permission;
import com.cl.permissions.annotation.PermissionCancel;
import com.cl.permissions.annotation.PermissionDenied;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickPermission(View view) {
        testRequest();

    }



    @Permission(value = Manifest.permission.READ_EXTERNAL_STORAGE, requestCode = 200)
    public void testRequest() {
        Toast.makeText(this, "权限申请成功...", Toast.LENGTH_SHORT).show();
    }

    @PermissionCancel
    public void testCancel() {
        Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied
    public void testDenied() {
        Toast.makeText(this, "权限被拒绝（用户勾选了，不再提醒）", Toast.LENGTH_SHORT).show();
    }
}
