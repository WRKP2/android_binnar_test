
package willybrodus.binar_test.resto;

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


public class Adapterresto extends RecyclerView.Adapter<Adapterresto.RestoHolder> {

    public Context context;
    private ArrayList<RestoCLASS> mRestoset;
    private int lastCheckedPosition = -1;


    public Adapterresto(ArrayList<RestoCLASS> myRestoset) {
        mRestoset = myRestoset;
    }

    @Override
    public RestoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restoadapter, parent, false);
        // set the view's size, margins, paddings and layout parameters


        RestoHolder vh = new RestoHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RestoHolder holder, final int position) {
        final RestoCLASS item = mRestoset.get(position);
        holder.mNama_Resto.setText(item.getNama_Resto());
        holder.mAlamat.setText(item.getAlamat());
        holder.mno_tlp.setText(item.getNo_tlp());

        //holder.imageView.getLayoutParams().height = 200;

    }

    @Override
    public int getItemCount() {
        return mRestoset.size();
    }

    public class RestoHolder extends RecyclerView.ViewHolder {

        public CardView mCardView;
        public TextView mNama_Resto;
        public TextView mAlamat;
        public TextView mno_tlp;
        public ImageView imageView;
        public Context context;

        public RestoHolder(View v) {
            super(v);
            context = v.getContext();

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mNama_Resto = (TextView) v.findViewById(R.id.txtNama_Resto);
            mAlamat = (TextView) v.findViewById(R.id.txtAlamat);
            mno_tlp = (TextView) v.findViewById(R.id.txtno_tlp);
//imageView = (ImageView) v . findViewById(R . id . iv_image);
        }
    }
}