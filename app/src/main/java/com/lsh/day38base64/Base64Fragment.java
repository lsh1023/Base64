package com.lsh.day38base64;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;

public class Base64Fragment extends Fragment implements View.OnClickListener {

    private EditText etSrc;
    private EditText etDist;

    public Base64Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSrc = (EditText) view.findViewById(R.id.et_src);
        etDist = (EditText) view.findViewById(R.id.et_dist);
        view.findViewById(R.id.btn_encode).setOnClickListener(this);
        view.findViewById(R.id.btn_decode).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_encode://编码
                String src = etSrc.getText().toString().trim();
                if (!TextUtils.isEmpty(src)) {
                    //编码的过程
                    try {
                        byte[] encode = Base64.encode(src.getBytes("UTF-8"), Base64.DEFAULT);
                        etDist.setText(new String(encode));
                        etSrc.setText("");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_decode://解码
                String dist=etDist.getText().toString().trim();
                if (!TextUtils.isEmpty(dist)) {
                    try {
                        //解码的过程
                        byte[] decode=Base64.decode(dist.getBytes("UTF-8"),Base64.DEFAULT);
                        etSrc.setText(new String(decode));
                        etDist.setText("");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                break;

        }

    }
}
