package com.example.marketim.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.marketim.Model.Market;
import com.example.marketim.Model.ProductDetail;
import com.example.marketim.R;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter  extends RecyclerView.Adapter<ItemAdapter.MovieViewHolder> {
    private final ArrayList<? extends Market> markets;
    private final List<ProductDetail> detail;
    private int mExpandedPosition = -1;

    //Oluşturucu Yarat
    public ItemAdapter(ArrayList<? extends Market> markets, List<ProductDetail> detail) {
        this.markets = markets;
        this.detail = detail;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NotNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_market, parent, false);
        return new MovieViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        // Populate adapter with movies and description
        holder.bind(markets.get(position),detail.get(position));

    }

    @Override
    public int getItemCount() {
        return markets.size();

    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
         final TextView Date;
        final TextView Month;
        final TextView MarketName;
        final TextView OrderName;
        final TextView ProductPrice;
        final TextView ProductState;
        final TextView Summary;
        final TextView PriceSummary;
         final LinearLayout Details;

        MovieViewHolder(View itemView) {
            super(itemView);
            Date = itemView.findViewById(R.id.days);
            Month = itemView.findViewById(R.id.month);
             MarketName = itemView.findViewById(R.id.market_name);
            OrderName = itemView.findViewById(R.id.order_name);
            ProductPrice = itemView.findViewById(R.id.price);
            ProductState = itemView.findViewById(R.id.product_state);
            Summary = itemView.findViewById(R.id.summary);
            PriceSummary = itemView.findViewById(R.id.price_summary);
            Details = itemView.findViewById(R.id.linearLayout2);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        void bind(Market market, final ProductDetail detail) {
            Date.setText(market.getDate());
            String months =  new DateFormatSymbols(Locale.forLanguageTag("tr-TR")).getMonths()[Integer.parseInt(market.getMonth())-1];
            Month.setText(months);
            MarketName.setText(market.getMarketName());
            OrderName.setText(market.getOrderName());
            if (market.getProductState().contains("Yolda")){
                ProductState.setTextColor(Color.parseColor("#a4c639"));
            } else if (market.getProductState().contains("Onay Bekliyor")){

                ProductState.setTextColor(Color.parseColor("#FF4500"));

            }else if (market.getProductState().contains("Hazırlanıyor")){

                ProductState.setTextColor(Color.parseColor("#FFA500"));
            }
            ProductState.setText(market.getProductState());
            ProductPrice.setText(String.valueOf(market.getProductPrice()));



            final boolean isExpanded = getAdapterPosition() == mExpandedPosition;
            itemView.setActivated(isExpanded);
            if (!isExpanded){
                Details.setVisibility(View.GONE);
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint({"NewApi", "ResourceType"})
                @Override
                public void onClick(View v) {
                    mExpandedPosition = isExpanded ? -1 : getAdapterPosition();
                    Details.setVisibility(View.VISIBLE);
                    Summary.setText(detail.getOrderDetail());
                    PriceSummary.setText(String.valueOf(detail.getSummaryPrice()));
                    notifyDataSetChanged();
                }


            });


        }

    }
}
