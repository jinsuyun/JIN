package ssm.hel_per;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class FoodNumPicker extends DialogFragment {

    int mNewValue;
    Button btnOk;
    Button btnCancel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialog = inflater.inflate(R.layout.foodnum_select_dialog, null);

        btnOk = dialog.findViewById(R.id.foodnum_btn_ok);
        btnCancel = dialog.findViewById(R.id.foodnum_btn_cancel);

        final NumberPicker np = (NumberPicker) dialog.findViewById(R.id.foodnumPicker);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mNewValue = newVal;
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FoodNumPicker.this.getDialog().cancel();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FoodNumPicker.this.getDialog().cancel();
            }
        });

        np.setMinValue(1);
        np.setMaxValue(10);
        np.setValue(1);

        builder.setView(dialog);

        return builder.create();
    }

    public int getmNewValue() {
        return mNewValue;
    }
}
