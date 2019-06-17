package com.iflytek.luoluo.myactivity;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioTrack;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iflytek.luoluo.ExceUtil.ExcelReader;
import com.iflytek.luoluo.pojo.Api;
import com.iflytek.luoluo.util.DataBaseUtil;
import com.iflytek.mytestluoluo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ApiPutActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvwhitchisgeshireal;
    private TextView tvtagisgeshireal;
    private  String strpath;
    private  String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_api_put);
        str = getIntent().getStringExtra("msg");
        LinearLayout tvconfirmisgeshireal = findViewById(R.id.tvconfirmisgeshireal);
        tvwhitchisgeshireal = findViewById(R.id.tvwhitchisgeshireal);
        tvtagisgeshireal = findViewById(R.id.tvtagisgeshireal);
        if ("dbappkeyput".equals(str)){
            tvtagisgeshireal.setText("dbappkeyput"+"导入确定");
        }else if ("dbusertaxput".equals(str)){
            tvtagisgeshireal.setText("dbusertaxput"+"导入确定");
        }
        tvconfirmisgeshireal.setOnClickListener(this);
        tvwhitchisgeshireal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvwhitchisgeshireal:
                fileSelect();
                break;
            case R.id.tvconfirmisgeshireal:
                if (strpath.equals("")){
                    Toast.makeText(this,"请选择正确的文件格式重新验证",Toast.LENGTH_LONG).show();
                }else {
                    apistore(strpath);
                }
                break;
            default:
                Toast.makeText(this,"请选择正确的文件格式重新验证",Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void apistore(String strpath) {
        try {

            if ("dbappkeyput".equals(str)){
                ExcelReader reader  = new ExcelReader(strpath,1);
                List<Object> list = reader.excelReader();
                if (list.size()>0){
                    DataBaseUtil.putAppkeyData(list);
                    Toast.makeText(this,"数据写入成功",Toast.LENGTH_LONG).show();
                    finish();

                }else {
                    Toast.makeText(this,"没有数据或excel不存在",Toast.LENGTH_LONG).show();
                }
            }else if ("dbusertaxput".equals(str)){
                ExcelReader reader  = new ExcelReader(strpath,2);
                List<Object> list = reader.excelReader();
                if (list.size()>0){
                    DataBaseUtil.putUsertaxData(list);
                    Toast.makeText(this,"数据写入成功",Toast.LENGTH_LONG).show();
                    finish();

                }else {
                    Toast.makeText(this,"没有数据或excel不存在",Toast.LENGTH_LONG).show();
                }
            }else {
                ExcelReader reader  = new ExcelReader(strpath,0);
                List<Object> list = reader.excelReader();
                if (list.size()>0){
                    DataBaseUtil.putApiData(list);
                    Toast.makeText(this,"数据写入成功",Toast.LENGTH_LONG).show();
                    finish();

                }else {
                    Toast.makeText(this,"没有数据或excel不存在",Toast.LENGTH_LONG).show();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    private void fileSelect() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setType(“image/*”);//选择图片
        //intent.setType(“audio/*”); //选择音频
        //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
        //intent.setType(“video/*;image/*”);//同时选择视频和图片
        intent.setType("*/*");//无类型限制
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 获取当前活动的Activity实例
        //判断是否实现返回值接口
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Uri uri = data.getData();
            strpath = getPath(this,uri);
            tvwhitchisgeshireal.setText(strpath);

        }
    }

    @SuppressLint("NewApi")
    public String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }
    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
}
