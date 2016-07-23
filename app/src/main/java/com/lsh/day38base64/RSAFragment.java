package com.lsh.day38base64;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by LSH on 2016/7/22.
 */
public class RSAFragment extends Fragment implements View.OnClickListener {

    private EditText etSrc,etDist;

    public RSAFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        KeyPair keyPair=RSAUtil.generaterKeyPaor(1024);
        assert keyPair!=null;
        PublicKey publicKey=keyPair.getPublic();
        PrivateKey privateKey=keyPair.getPrivate();
        Log.i("TAG","publicKey="+publicKey.toString());
        Log.i("TAG","privateKey="+privateKey.toString());

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

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_encode://加密

                String src=etSrc.getText().toString().trim();
                if (!TextUtils.isEmpty(src)) {
                    byte[] encrcyt=RSAUtil.encrypt(src.getBytes());
                    //base64编码,否则会乱码
                    byte[] encode= Base64.encode(encrcyt,Base64.DEFAULT);
                    etDist.setText(new String(encode));
                    etSrc.setText("");
                }
                
                break;
            case R.id.btn_decode://解密
                String dist = etDist.getText().toString().trim();
                if (!TextUtils.isEmpty(dist)) {
                    byte[] decode = Base64.decode(dist.getBytes(), Base64.DEFAULT);
                    byte[] decrypt = RSAUtil.decrypt(decode);
                    assert decrypt != null;
                    etSrc.setText(new String(decrypt));
                    etDist.setText("");
                }
                break;

        }


    }
}
