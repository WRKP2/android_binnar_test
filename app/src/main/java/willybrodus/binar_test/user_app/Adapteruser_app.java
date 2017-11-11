
package willybrodus.binar_test.user_app;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import willybrodus.binar_test.R;


public class Adapteruser_app extends RecyclerView.Adapter<Adapteruser_app.User_appHolder> {

    public Context context;
    private ArrayList<User_appCLASS> mUser_appset;
    private int lastCheckedPosition = -1;


    public Adapteruser_app(ArrayList<User_appCLASS> myUser_appset) {
        mUser_appset = myUser_appset;
    }

    @Override
    public User_appHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_appadapter, parent, false);
        // set the view's size, margins, paddings and layout parameters


        User_appHolder vh = new User_appHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(User_appHolder holder, final int position) {
        final User_appCLASS item = mUser_appset.get(position);
        holder.mNAMA.setText(item.getNAMA());
        holder.mALAMAT.setText(item.getALAMAT());
        holder.mEMAIL.setText(item.getEMAIL());
        holder.mPASWORD.setText(item.getPASWORD());

        //holder.imageView.getLayoutParams().height = 200;

    }

    @Override
    public int getItemCount() {
        return mUser_appset.size();
    }

    public class User_appHolder extends RecyclerView.ViewHolder {

        public CardView mCardView;
        public TextView mNAMA;
        public TextView mALAMAT;
        public TextView mEMAIL;
        public TextView mPASWORD;
        public ImageView imageView;
        public Context context;

        public User_appHolder(View v) {
            super(v);
            context = v.getContext();

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mNAMA = (TextView) v.findViewById(R.id.txtNAMA);
            mALAMAT = (TextView) v.findViewById(R.id.txtALAMAT);
            mEMAIL = (TextView) v.findViewById(R.id.txtEMAIL);
            mPASWORD = (TextView) v.findViewById(R.id.txtPASWORD);
//imageView = (ImageView) v . findViewById(R . id . iv_image);
        }
    }
}