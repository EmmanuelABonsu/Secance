package com.example.secance;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class AddPropertyFragment extends DialogFragment implements View.OnClickListener {
    EditText addressline2, postalCode, address1;
    Button submitbtn;
    TextView closePopup;

    private String address_1;
    private String address_2;
    private String mobile;

    public AddPropertyFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_property, container, false);
        getDialog().setCanceledOnTouchOutside(false);

        submitbtn = (Button) rootView.findViewById(R.id.submit);
        address1 = (EditText) rootView.findViewById(R.id.address_line1);
        addressline2 = (EditText) rootView.findViewById(R.id.address_line2);
        postalCode = (EditText) rootView.findViewById(R.id.postal_code);
        closePopup = (TextView) rootView.findViewById(R.id.close_pop_up);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
//        address_2 = addressline2.getText().toString().trim();
//        address_1 = address1.getText().toString().trim();
//        mobile = postalCode.getText().toString().trim();

    }


}
