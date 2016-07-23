package com.lsh.day38base64;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by LSH on 2016/7/22.
 */
public class UrlEncodeFragment extends Fragment implements View.OnClickListener {

    private TextView tvEncode;
    private TextView tvDecode;

    public UrlEncodeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_url,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvEncode= (TextView) view.findViewById(R.id.tv_encode);
        tvDecode= (TextView) view.findViewById(R.id.tv_decoode);
        view.findViewById(R.id.btn_encode).setOnClickListener(this);
        view.findViewById(R.id.btn_decode).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            //编码
            case  R.id.btn_encode:

                try {
                    String encode= URLEncoder.encode("南 / 海 : 是 & 中 ? 国 = 的", "UTF-8");
                    tvEncode.setText("编码的结果是="+encode);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            //解码
            case R.id.btn_decode:

                try {
                    String encode= URLEncoder.encode("南 / 海 : 是 & 中 ? 国 = 的", "UTF-8");

                    String decode=URLDecoder.decode(encode,"UTF-8");
                    tvDecode.setText("解码的结果="+decode);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                break;
        }

    }


}
