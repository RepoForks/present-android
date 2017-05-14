package com.larry.present.loginregister.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.larry.present.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*    
* 项目名称：present-android      
* 类描述： 学生设置头像接口
* 创建人：Larry-sea   
* 创建时间：2017/4/19 14:54   
* 修改人：Larry-sea  
* 修改时间：2017/4/19 14:54   
* 修改备注：   
* @version    
*    
*/
public class StudentSetPortraitActivity extends AppCompatActivity {
    @BindView(R.id.iv_portrait_portrait)
    ImageView ivPortraitPortrait;

    final static String TAG = StudentSetPortraitActivity.class.toString();

    final String portraitPath = Environment.getExternalStorageDirectory() + "/here";

    @BindView(R.id.btn_portrait_take_picture)
    Button takePicture;

    //调用拍照结果结果以后
    boolean resultAfter = false;

    @OnClick(R.id.btn_portrait_take_picture)
    void takePicture(View view) {
        if (resultAfter) {
            Toast.makeText(this, R.string.uploading_portrait, Toast.LENGTH_SHORT).show();
        } else {
            startCameraByIntent();
        }
    }

    private void startCameraByIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = new File(portraitPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File portraitFile = new File(portraitPath + "/portrait.jpg");
        Uri photoUri = Uri.fromFile(portraitFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_set_portrait);
        ButterKnife.bind(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        File file = new File(portraitPath + "/portrait.jpg");
        if (requestCode == 2 && resultCode == -1) {
            if (file.exists()) {
                Glide.with(this).load(portraitPath + "/portrait.jpg").into(ivPortraitPortrait);
                takePicture.setText("上传");
                resultAfter = true;
            }
        }
    }
}
