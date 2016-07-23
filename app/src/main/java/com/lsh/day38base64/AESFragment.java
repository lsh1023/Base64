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

import javax.crypto.Cipher;

/**
 * Created by LSH on 2016/7/22.
 */
public class AESFragment extends Fragment implements View.OnClickListener{

    private EditText etSrc,etDist;

    public AESFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aes,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSrc= (EditText) view.findViewById(R.id.et_src);
        etDist= (EditText) view.findViewById(R.id.et_dist);
        view.findViewById(R.id.btn_encode).setOnClickListener(this);
        view.findViewById(R.id.btn_decode).setOnClickListener(this);
    }

    private String key="0123456789ABCDEF";

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_encode://加密
                String src=etSrc.getText().toString().trim();
                if (!TextUtils.isEmpty(src)) {
                    byte[] encrypt= EncrytUtil.encrypt(Cipher.ENCRYPT_MODE,key,src.getBytes());
                    //base64编码，否则会乱码
                    byte[] encode= Base64.encode(encrypt,Base64.DEFAULT);
                    etDist.setText(new String(encode));
                    etSrc.setText("");
                }

                break;

            case R.id.btn_decode://解密

                String dist=etDist.getText().toString().trim();
                if(!TextUtils.isEmpty(dist))
                {
                    byte[] decode=Base64.decode(dist.getBytes(),Base64.DEFAULT);
                    byte[] encrypt=EncrytUtil.encrypt(Cipher.DECRYPT_MODE,key,decode);
                    etSrc.setText(new String(encrypt));
                    etDist.setText("");
                }
                break;

        }

    }
}
