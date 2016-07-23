package com.lsh.day38base64;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by LSH on 2016/7/22.
 */
public class Md5Fragment extends Fragment implements View.OnClickListener {

    private EditText etSrc;

    public Md5Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_md5, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSrc = (EditText) view.findViewById(R.id.et_src);
        view.findViewById(R.id.btn_encode).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_encode://编码
                String src = etSrc.getText().toString().trim();
                if (!TextUtils.isEmpty(src)) {

                    try {
                        //得到MD5的信息摘要实例
                        MessageDigest digest = MessageDigest.getInstance("MD5");

                        byte[] encode = digest.digest(src.getBytes("UTF-8"));
                        StringBuilder sb = new StringBuilder(encode.length);
                        for (byte anEncode : encode) {
                            if ((anEncode & 0xFF) < 0x10) { //如果小于10，0x10
                                sb.append("0");
                            }
                            //如果>10,xx--->变成16进制
                            sb.append(Integer.toHexString(anEncode & 0xFF));
                        }
                        //要用base64进行一下编码，否则会出现乱码
                        String result = Base64.encodeToString(sb.toString().getBytes(), Base64.DEFAULT);
                        etSrc.setText(result);


                    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
