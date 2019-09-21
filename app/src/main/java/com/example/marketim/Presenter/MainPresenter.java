package com.example.marketim.Presenter;

import com.example.marketim.Model.Market;
import com.example.marketim.Model.ProductDetail;
import com.example.marketim.Retrofit.API;
import com.example.marketim.Retrofit.RetroRepository;
import com.example.marketim.View.LoginActivity.ILogin;
import com.example.marketim.View.MainActivity.IMainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements IMainPresenter {

    private  IMainActivity iMainActivity;
    private ILogin iLogin;
    private List<Market> marketItems = new ArrayList<>();
    private final List<ProductDetail> productDetails = new ArrayList<>();


    //Oluşturucu Yarat
    public MainPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
    }

    //Oluşturucu Yarat
    public MainPresenter(ILogin iLogin){
        this.iLogin = iLogin;
    }
    //Sunucudan veri al
    public void getMyList(){


        API api = RetroRepository.getInstance().create(API.class);

        api.getMyOrder()
                .enqueue(new Callback<List<Market>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Market>> call, @NonNull Response<List<Market>> response) {
                        if (response.isSuccessful()) {
                            marketItems = response.body();
                            for (int i = 0; i< Objects.requireNonNull(marketItems).size(); i++){
                                productDetails.add(marketItems.get(i).getProductDetail());
                            }
                            // Görünüme veri gönder
                            iMainActivity.onSuccess(marketItems,productDetails);

                        } else {
                            //görünüme mesaj gönder
                            iMainActivity.onError("Bağlantınızı Kontrol Edin!!");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<Market>> call, @NotNull Throwable t) {
                        //görünüme mesaj gönder
                        iMainActivity.onError("Bağlantınızı Kontrol Edin!!");

                    }
                });


    }
    // Giriş bilgilerini kontrol et
    public void LoginAuth(String UserName, String Password){
        if (UserName.equals("kariyer") && Password.equals("2019ADev")){
            // Giriş Aktivitesine Giriş Başarısını Gönder
            iLogin.LogInSuccessfully();
        }else {
            // Login Aktivitesine Login Failure mesajı gönder
            iLogin.OnFailure("yanlış kimlik bilgileri geçersiz kullanıcı adı veya şifre");
        }

    }

}
