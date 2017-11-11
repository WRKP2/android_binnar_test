
package willybrodus.binar_test.order_menu;

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


public class Adapterorder_menu extends RecyclerView.Adapter<Adapterorder_menu.Order_menuHolder> {

    public Context context;
    private ArrayList<Order_menuCLASS> mOrder_menuset;
    private int lastCheckedPosition = -1;


    public Adapterorder_menu(ArrayList<Order_menuCLASS> myOrder_menuset) {
        mOrder_menuset = myOrder_menuset;
    }

    @Override
    public Order_menuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_menuadapter, parent, false);
        // set the view's size, margins, paddings and layout parameters


        Order_menuHolder vh = new Order_menuHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(Order_menuHolder holder, final int position) {
        final Order_menuCLASS item = mOrder_menuset.get(position);
        holder.mID_RESTO.setText(item.getID_RESTO());
        holder.mID_MENU.setText(item.getID_MENU());
        holder.mID_USERAPP.setText(item.getID_USERAPP());
        holder.mJUMLAH.setText(item.getJUMLAH());
        holder.mORDER_DATE.setText(item.getORDER_DATE());
        holder.mSTATUS.setText(item.getSTATUS());
        holder.mALAMAT_PENGIRIMAN.setText(item.getALAMAT_PENGIRIMAN());

        //holder.imageView.getLayoutParams().height = 200;

    }

    @Override
    public int getItemCount() {
        return mOrder_menuset.size();
    }

    public class Order_menuHolder extends RecyclerView.ViewHolder {

        public CardView mCardView;
        public TextView mID_RESTO;
        public TextView mID_MENU;
        public TextView mID_USERAPP;
        public TextView mJUMLAH;
        public TextView mORDER_DATE;
        public TextView mSTATUS;
        public TextView mALAMAT_PENGIRIMAN;
        public ImageView imageView;
        public Context context;

        public Order_menuHolder(View v) {
            super(v);
            context = v.getContext();

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mID_RESTO = (TextView) v.findViewById(R.id.txtID_RESTO);
            mID_MENU = (TextView) v.findViewById(R.id.txtID_MENU);
            mID_USERAPP = (TextView) v.findViewById(R.id.txtID_USERAPP);
            mJUMLAH = (TextView) v.findViewById(R.id.txtJUMLAH);
            mORDER_DATE = (TextView) v.findViewById(R.id.txtORDER_DATE);
            mSTATUS = (TextView) v.findViewById(R.id.txtSTATUS);
            mALAMAT_PENGIRIMAN = (TextView) v.findViewById(R.id.txtALAMAT_PENGIRIMAN);
//imageView = (ImageView) v . findViewById(R . id . iv_image);
        }
    }
}