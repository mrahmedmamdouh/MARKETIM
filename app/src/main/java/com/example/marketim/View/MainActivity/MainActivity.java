package com.example.marketim.View.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.marketim.Adapter.ItemAdapter;
import com.example.marketim.Model.Market;
import com.example.marketim.Model.ProductDetail;
import com.example.marketim.Presenter.MainPresenter;
import com.example.marketim.R;
import com.example.marketim.View.LoginActivity.Login;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.marketim.View.LoginActivity.Login.SHARED;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private ArrayList<? extends Market> mdata = new ArrayList<>();
    private List<ProductDetail> mdata2 = new ArrayList<>();
    private RecyclerView recycleView;
    private ItemAdapter adapter;
    private MainPresenter presenter;
    private FrameLayout fr;
    private ShimmerFrameLayout shimmerFrameLayout;
    private LinearLayoutManager mLayoutManager;
    private Button LogOut,MyOrders;
    private AlertDialog.Builder alert;
    SharedPreferences ref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((savedInstanceState != null)
                && (savedInstanceState.getParcelableArrayList("Market") != null)&& (savedInstanceState.getParcelableArrayList("Ürün") != null)) {
            mdata =  savedInstanceState.getParcelableArrayList("Market");
            mdata2 =  savedInstanceState.getParcelableArrayList("Ürün");}
        Init();
        OnClick();
    }

    private void OnClick() {

        MyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               presenter.getMyList();
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                alert.setTitle(getString(R.string.logingout_str));
                alert.setMessage(getString(R.string.want_Logout_str));
                alert.setPositiveButton(getString(R.string.yes_str), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        editor.putBoolean("BENİ HATIRLA", false);
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                        finish();
                    }

                });
                alert.setNegativeButton(getString(R.string.no_str) ,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        return;
                    }
                });
                alert.show();
            }


        });

    }

    private void Init() {
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                setContentView(R.layout.activity_main);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                setContentView(R.layout.activity_main1);
                break;
        }
        Objects.requireNonNull(this.getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
         alert = new AlertDialog.Builder(this);
        presenter = new MainPresenter(this);
        ref = getSharedPreferences(SHARED, MODE_PRIVATE);
        editor = ref.edit();
        shimmerFrameLayout = findViewById(R.id.shimmer_view);
        fr = findViewById(R.id.frame);
        MyOrders = findViewById(R.id.muOrders);
        LogOut = findViewById(R.id.logOut);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();
        fr.setVisibility(View.GONE);
        recycleView = findViewById(R.id.recycleListView);
        mLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycleView.getContext(), new LinearLayoutManager(this).getOrientation());
        recycleView.addItemDecoration(dividerItemDecoration);
        presenter.getMyList();

    }

    @Override
    public void onSuccess(List<Market> markets, List<ProductDetail> productDetails) {

        mdata = (ArrayList<? extends Market>) markets;
        mdata2 = productDetails;
        adapter = new ItemAdapter(mdata,mdata2);
        shimmerFrameLayout.stopShimmerAnimation();
        shimmerFrameLayout.setVisibility(View.GONE);
        fr.setVisibility(View.VISIBLE);
        recycleView.setAdapter(adapter);
    }

    @Override
    public void onError(String Error) {
        Toast.makeText(getApplicationContext(),Error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mdata =  savedInstanceState.getParcelableArrayList("Market");
        mdata2 =  savedInstanceState.getParcelableArrayList("Ürün");

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("Market",  mdata);
        outState.putParcelableArrayList("Ürün", (ArrayList<? extends Parcelable>) mdata2);

    }
}
