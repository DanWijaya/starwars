package starwars.coding.com.ParkLah.Review;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import starwars.coding.com.ParkLah.R;


public class CancelDialogFragment extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(R.string.cancelconfirm)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    //@Override
                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
                        Toast.makeText(context, " Review cancelled", Toast.LENGTH_SHORT).show();
                        ((FragmentActivity) context).finish();

                    }

                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

        return builder.create();

    }
}
