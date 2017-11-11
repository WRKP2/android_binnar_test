
package willybrodus.binar_test.menu;

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


public class Adaptermenu extends RecyclerView.Adapter<Adaptermenu.MenuHolder> {

    public Context context;
    private ArrayList<MenuCLASS> mMenuset;
    private int lastCheckedPosition = -1;


    public Adaptermenu(ArrayList<MenuCLASS> myMenuset) {
        mMenuset = myMenuset;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menuadapter, parent, false);
        // set the view's size, margins, paddings and layout parameters


        MenuHolder vh = new MenuHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, final int position) {
        final MenuCLASS item = mMenuset.get(position);
        holder.mNAMA_MENU.setText(item.getNAMA_MENU());
        holder.mID_RESTORAN.setText(item.getID_RESTORAN());
        holder.mHARGA.setText(item.getHARGA());
        holder.mFOTO_MENU.setText(item.getFOTO_MENU());
        holder.mKETERANGAN.setText(item.getKETERANGAN());

        //holder.imageView.getLayoutParams().height = 200;

    }

    @Override
    public int getItemCount() {
        return mMenuset.size();
    }

    public class MenuHolder extends RecyclerView.ViewHolder {

        public CardView mCardView;
        public TextView mNAMA_MENU;
        public TextView mID_RESTORAN;
        public TextView mHARGA;
        public TextView mFOTO_MENU;
        public TextView mKETERANGAN;
        public ImageView imageView;
        public Context context;

        public MenuHolder(View v) {
            super(v);
            context = v.getContext();

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mNAMA_MENU = (TextView) v.findViewById(R.id.txtNAMA_MENU);
            mID_RESTORAN = (TextView) v.findViewById(R.id.txtID_RESTORAN);
            mHARGA = (TextView) v.findViewById(R.id.txtHARGA);
            mFOTO_MENU = (TextView) v.findViewById(R.id.txtFOTO_MENU);
            mKETERANGAN = (TextView) v.findViewById(R.id.txtKETERANGAN);
//imageView = (ImageView) v . findViewById(R . id . iv_image);
        }
    }
}