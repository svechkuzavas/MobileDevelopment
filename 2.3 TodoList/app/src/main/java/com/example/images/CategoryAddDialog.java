package com.example.images;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CategoryAddDialog extends DialogFragment {
    public interface CategoryAddDialogListener {
        public void onDialogPositiveClick(String catName);
    }

    CategoryAddDialogListener listener;
    public CategoryAddDialog () { }

    public static CategoryAddDialog newInstance(){
        CategoryAddDialog dialog = new CategoryAddDialog();
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.category_add_dialog, container, false);

        TextView actionOk = (TextView) view.findViewById(R.id.cat_add_positive);
        TextView actionCancel = (TextView) view.findViewById(R.id.cat_add_negative);
        EditText input = (EditText) view.findViewById(R.id.cat_inp);

        actionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        actionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDialogPositiveClick(input.getText().toString());
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listener = (CategoryAddDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement AuthDialogListener");
        }
    }
}
