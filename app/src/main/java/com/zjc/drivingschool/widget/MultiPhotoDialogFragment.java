package com.zjc.drivingschool.widget;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.mobo.mobolibrary.logs.Logs;
import com.mobo.mobolibrary.util.Util;
import com.mobo.mobolibrary.util.UtilPhoto;
import com.mobo.mobolibrary.util.UtilUri;
import com.ns.mutiphotochoser.constant.Constant;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.eventbus.ActionCameraEvent;
import com.zjc.drivingschool.eventbus.ActionMultiPhotoEvent;
import com.zjc.drivingschool.utils.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/6/30.
 */
public class MultiPhotoDialogFragment extends DialogFragment {
    private String photoName = Constants.DIR_IMAGE + Util.getPhotoFileName();
    private View rootView;
    public int max = 6;

    /**
     * 拍照
     */
    public static final int IMAGE_REQUEST_CODE = 0;
    public static final int CAMERA_REQUEST_CODE = 3;

    private int action;

    public MultiPhotoDialogFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("photoName")) {
                photoName = savedInstanceState.getString("photoName");
                Logs.i(photoName);
                getDialog().dismiss();
            }
        } else {
            rootView = inflater.inflate(R.layout.comm_dialog_photo_frg, container, false);
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            initView();
        }
        return rootView;
    }

    private void initView() {
        TextView tvAddress = (TextView) rootView.findViewById(R.id.public_dialog_photo_tvlocal);
        TextView tvContent = (TextView) rootView.findViewById(R.id.public_dialog_photo_tvcamera);
        TextView tvCancel = (TextView) rootView.findViewById(R.id.public_dialog_photo_tvcancel);

        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.zjc.drivingschool.action.CHOSE_PHOTOS");
                //指定图片选择数
                intent.putExtra(Constant.EXTRA_PHOTO_LIMIT, max);
                startActivityForResult(intent, IMAGE_REQUEST_CODE);
            }
        });

        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断存储卡是否可以用，可用进行存储
                if (Util.hasSdcard()) {
                    Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Uri imageUri = Uri.fromFile(new File(photoName));
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    Logs.i(photoName);
                    startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
                } else {
                    Util.showCustomMsg("未找到存储卡，无法存储照片！");
                }
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_CANCELED) {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    ArrayList<String> images = data.getStringArrayListExtra(Constant.EXTRA_PHOTO_PATHS);
                    sendMultiPhotoUrisToView(images);
                    break;
                case CAMERA_REQUEST_CODE:
                    //生成输入文件路径，进行压缩
                    String filename = Constants.DIR_IMAGE + Util.getPhotoFileName();
                    UtilPhoto.getSmallFileByFile(photoName, filename);
                    sendCameraUriToView(filename);
                    deleteSourceFile(photoName);
                    break;
            }
        } else {
            getDialog().dismiss();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void deleteSourceFile(String photoName) {
        //删除压缩前的照相文件
        File temp = new File(photoName);
        if (temp.exists()) {
            temp.delete();
        }
    }

    /**
     * 发送事件到界面
     */
    private void sendCameraUriToView(String uri) {
        try {
            EventBus.getDefault().post(new ActionCameraEvent(uri, action));
            getDialog().dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送事件到界面
     */
    private void sendMultiPhotoUrisToView(List<String> uris) {
        try {
            EventBus.getDefault().post(new ActionMultiPhotoEvent(uris, action));
            getDialog().dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("photoName", photoName);
    }

    @Override
    public void onResume() {
        super.onResume();
        //宽度设为屏幕的0.9倍
        int width = getDialog().getWindow().getWindowManager().getDefaultDisplay().getWidth();
        getDialog().getWindow().setLayout((int) (width * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}

