package com.example.marketim.Presenter;


import com.example.marketim.View.LoginActivity.ILogin;

public class MainPresenter implements IMainPresenter {

    private ILogin iLogin;



    //Oluşturucu Yarat
    public MainPresenter(ILogin iLogin){
        this.iLogin = iLogin;
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
