package com.krislq.invitation.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.krislq.invitation.R;

public class WallFragment extends BaseFragment implements OnClickListener{
    private EditText mName;
    private EditText mContent;
    private Button mSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wall, null);
        mName = (EditText)view.findViewById(R.id.et_name);
        mContent = (EditText)view.findViewById(R.id.et_details);
        mSend = (Button)view.findViewById(R.id.btn_send);
        mSend.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_send:
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:kris@krislq.com"));
            
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.wall_subject));
            intent.putExtra(Intent.EXTRA_TEXT, mContent.getText().toString());
            startActivity(Intent.createChooser(intent, getString(R.string.wall_subject)));
            break;

        default:
            break;
        }
    }


}
