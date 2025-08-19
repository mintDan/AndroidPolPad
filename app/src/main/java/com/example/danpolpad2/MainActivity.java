package com.example.danpolpad2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.TextView;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView JsoupTxtMain;

    private Button TranslateBtn,btnExperiTxt, btnSwitch;
    private Button msnpolska,redditpolska,businessinsiderpl,elmundo,wikipl,wikies,wprost;

    private String api = "";// Insert API for google translate here!!!
    ProgressDialog progressDialog;
    private int ScreenState = 1;
    int translateMethod = 2; //1 for WebScrape, 2 for Google url method hack
    private ScrollWebView browser,browser2;

    String urlGet = "";
    private String urlbody;
    private String title = "HELLOTHERE";
    private String sourceLang = "pl";
    Elements txt2;

    String outputtmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsoupTxtMain = findViewById(R.id.JsoupTxtMain);
        TranslateBtn = findViewById(R.id.TranslateBtn);
        btnExperiTxt = findViewById(R.id.ExperiBtn);
        btnSwitch = findViewById(R.id.Switch);
        Button msnpolska = (Button) findViewById(R.id.msnpolska);
        Button redditpolska = (Button) findViewById(R.id.redditpolska);
        Button businessinsiderpl = (Button) findViewById(R.id.businessinsidercompl);
        Button elmundo = (Button) findViewById(R.id.elmundo);
        Button wikipl = (Button) findViewById(R.id.wikipl);
        Button wikies = (Button) findViewById(R.id.wikies);
        Button wprost = (Button) findViewById(R.id.Wprost);


        JsoupTxtMain.setMovementMethod(new ScrollingMovementMethod());

        String UrlMSNFinanse = "https://www.msn.com/pl-pl/finanse";
        String UrlMSNFinanseTranslateFromWindows = "https://www-msn-com.translate.goog/pl-pl/finanse?_x_tr_sl=pl&_x_tr_tl=en&_x_tr_hl=da&_x_tr_pto=wapp";

        String UrlBInsider = "https://businessinsider.com.pl/";
        String UrlBInsiderTranslate = "https://businessinsider-com-pl.translate.goog/?_x_tr_sl=pl&_x_tr_tl=en&_x_tr_hl=da&_x_tr_pto=wapp";

        String UrlReddit = "https://www.reddit.com/r/Polska/";
        String UrlRedditTranslateFromWindows2 = "https://www-reddit-com.translate.goog/r/Polska/?_x_tr_sl=pl&_x_tr_tl=en&_x_tr_hl=da&_x_tr_pto=wapp";

        String Urlrp = "https://www.rp.pl/ekonomia";
        String UrlrpTranslateFromWindows2 = "https://www-rp-pl.translate.goog/ekonomia?_x_tr_sl=pl&_x_tr_tl=en&_x_tr_hl=da&_x_tr_pto=wapp";

        String URLWyb = "https://wyborcza.biz/biznes/0,0.html#s=logo_gora";
        String UrlWybTranslateFromWindows2 = "https://wyborcza-biz.translate.goog/biznes/0,0.html?disableRedirects=true&_x_tr_sl=pl&_x_tr_tl=en&_x_tr_hl=da&_x_tr_pto=wapp#s=logo_gora";


        String UrlWprost = "https://biznes.wprost.pl/";
        String UrlWprostTranslated = "";


        String URLElMundo = "https://www.elmundo.es/espana/2025/07/12/68728076e85ece7e058b45bd.html";
        String URLElMundoTranslate = "https://www-elmundo-es.translate.goog/espana/2025/07/12/68728076e85ece7e058b45bd.html?_x_tr_sl=es&_x_tr_tl=en&_x_tr_hl=da&_x_tr_pto=wapp";


        String URLwikipl = "https://pl.wikipedia.org/wiki/Mitologia_grecka";

        String URLwikies = "https://es.wikipedia.org/wiki/Econom%C3%ADa_de_Espa%C3%B1a";







        //JsoupTxtMain.setText("Ryanair komentuje awarię na lotniskach i uderza w UE. Ciągły chaos i nieudolność");

        ScrollWebView browser = findViewById(R.id.webview);
        ScrollWebView browser2 = findViewById(R.id.webview2);


        browser.setWebViewClient(
                new SSLTolerantWebViewClient()
        );
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);
        browser.getSettings().setMediaPlaybackRequiresUserGesture(false);
        browser.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setDatabaseEnabled(true);//DENNE CHANGED
        browser.getSettings().setDomStorageEnabled(true);
        //These 4 Supposedly helps for scrolling? Inside scrollview?
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);
        browser.getSettings().setSupportZoom(true);
        browser.getSettings().setBuiltInZoomControls(true);

        browser.loadUrl(UrlBInsider);
        //Måske repeat denne her.... for hvergang man update
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(browser, true);
        } else {
            CookieManager.getInstance().setAcceptCookie(true);
        }

        browser2.setWebViewClient(
                new SSLTolerantWebViewClient()
        );
        browser2.getSettings().setLoadWithOverviewMode(true);
        browser2.getSettings().setUseWideViewPort(true);
        browser2.getSettings().setMediaPlaybackRequiresUserGesture(false);
        browser2.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        browser2.getSettings().setJavaScriptEnabled(true);
        browser2.getSettings().setDatabaseEnabled(true);//DENNE CHANGED
        browser2.getSettings().setDomStorageEnabled(true);
        //These 4 Supposedly helps for scrolling? Inside scrollview?
        browser2.getSettings().setLoadWithOverviewMode(true);
        browser2.getSettings().setUseWideViewPort(true);
        browser2.getSettings().setSupportZoom(true);
        browser2.getSettings().setBuiltInZoomControls(true);


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(browser2, true);
         } else {
            CookieManager.getInstance().setAcceptCookie(true);
         }







        TranslateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TranslateBtn","translateMethod = " + Integer.toString(translateMethod));
                if (translateMethod == 1) {
                    //DO GOOGLE TRANSLATE API METHOD
                    Log.d("TranslateBtn","Using Google Translate API");
                    ContentGoogleTranslate contentGoogleTranslate = new ContentGoogleTranslate();
                    contentGoogleTranslate.execute();
                }
                else{
                    //DO URL HACK HERE
                    Log.d("TranslateBtn","Using Google URL method");
                    String URLcurrent = browser.getUrl();
                    //browser2.loadUrl("https://www-msn-com.translate.goog/pl-pl/wiadomosci/nauka-i-technika/rosja-chce-zniszczy%C4%87-satelity-starlink-oto-jak-mo%C5%BCna-tego-dokona%C4%87/ar-AAWhBQk?ocid=EMMX&cvid=6996f72e4f484ce7bb3b4b179ac30da8&_x_tr_sl=pl&_x_tr_tl=en&_x_tr_hl=da&_x_tr_pto=wapp");
                    //So here I need to transform the url, into a google translate url

                    String Url1 = URLcurrent.replace("https://www.msn.com","https://www-msn-com.translate.goog");

                    Url1 = Url1.replace("https://www.reddit.com/","https://www-reddit-com.translate.goog/");
                    //String AppendURL = "&_x_tr_sl=pl&_x_tr_tl=en&_x_tr_hl=da&_x_tr_pto=wapp";
                    Url1  = Url1.replace("https://www.rp.pl/","https://www-rp-pl.translate.goog/");
                    //String UrlrpTranslateFromWindows2 = "https://www-rp-pl.translate.goog/
                    Url1 = Url1.replace("https://www.elmundo.es","https://www-elmundo-es.translate.goog/");
                    Url1 = Url1.replace("https://pl.m.wikipedia.org","https://pl-m-wikipedia-org.translate.goog/");
                    //Her, der tilføjes .m. vidst, måske for mobile? SÅ då det er DESKTOP vs mobile
                    Url1 = Url1.replace("https://es.m.wikipedia.org/","https://es-m-wikipedia-org.translate.goog/");

                    Url1 = Url1.replace("https://biznes.wprost.pl/","https://biznes-wprost-pl.translate.goog/");
                    Url1 = Url1.replace("https://businessinsider.com.pl/","https://businessinsider-com-pl.translate.goog/");
                    String AppendURL = "";

                    //AppendURL = "?_x_tr_sl=pl&_x_tr_tl=en&_x_tr_hl=da&_x_tr_pto=wapp";
                    if (sourceLang == "pl") {
                        AppendURL = "?_x_tr_sl=pl&_x_tr_tl=en&_x_tr_hl=da&_x_tr_pto=wapp";
                    } else {
                        AppendURL = "?_x_tr_sl=es&_x_tr_tl=en&_x_tr_hl=da&_x_tr_pto=wapp";
                    }

                    String Url2 = Url1+AppendURL;

                    Log.d("TranslateBtn Url2",Url2);
                    //?_x_tr_sl=es&_x_tr_tl=en&_x_tr_hl=da&_x_tr_pto=wapp

                    //browser2.loadUrl(URLcurrent);
                    browser2.loadUrl(Url2);
                    if (android.os.Build.VERSION.SDK_INT >= 21) {
                        CookieManager.getInstance().setAcceptThirdPartyCookies(browser2, true);
                    } else {
                        CookieManager.getInstance().setAcceptCookie(true);
                    }
                }
            }
        });


        btnExperiTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                urlGet = browser.getUrl();
                ContentWebScrape contentWebScrape = new ContentWebScrape();
                contentWebScrape.execute();
            }
        });


        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //browser.setVisibility(View.GONE);
                //browser2.setVisibility(View.VISIBLE);
                //title = "heytitle";
                Log.d("btnSwitch",title);
                Log.d("btnSwitch, translateMethod", Integer.toString(translateMethod));
                //JsoupTxtMain.setText(title);
                //if (Screen){
                if (ScreenState == 1){
                    //right now, both are visible
                    //Now, set webview to GONE
                    browser.setVisibility(View.GONE);
                    // eText.setEnabled(false);
                    //eText.setVisibility(View.INVISIBLE);
                    //Screen = false;

                    //Browser vs view text
                    if(translateMethod == 1){
                        //Use Google translate api for text
                        JsoupTxtMain.setVisibility(View.VISIBLE);
                    } else
                    {
                        //Use URL hack
                        browser2.setVisibility(View.VISIBLE);
                    }



                    ScreenState = 2;
                    btnSwitch.setText("Switch "+ ScreenState);



                } else if (ScreenState == 2){
                    //right now, webview is gone, notepad is visible
                    //set webview visible, and notepad gone. So invert.
                    browser.setVisibility(View.VISIBLE);
                    //ViewGroup.LayoutParams vc=webView.getLayoutParams();
                    //vc.height=700;
                    //vc.width=450;

                    //Browser vs textview
                    //In this, just set them both invisible, doesn't hurt
                    browser2.setVisibility(View.GONE);
                    JsoupTxtMain.setVisibility(View.GONE);

                    ScreenState = 1;
                    btnSwitch.setText("Switch "+ ScreenState);
                }
            }
        });


        msnpolska.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sourceLang = "pl";
                browser.loadUrl(UrlMSNFinanse);
                translateMethod = 2;
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    CookieManager.getInstance().setAcceptThirdPartyCookies(browser, true);
                } else {
                    CookieManager.getInstance().setAcceptCookie(true);
                }


            }
        });


        redditpolska.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sourceLang = "pl";
                browser.loadUrl(UrlReddit);
                translateMethod = 1;
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    CookieManager.getInstance().setAcceptThirdPartyCookies(browser, true);
                } else {
                    CookieManager.getInstance().setAcceptCookie(true);
                }

            }
        });


        businessinsiderpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sourceLang = "pl";
                browser.loadUrl(UrlBInsider);
                translateMethod = 2;
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    CookieManager.getInstance().setAcceptThirdPartyCookies(browser, true);
                } else {
                    CookieManager.getInstance().setAcceptCookie(true);
                }

            }
        });


        wikipl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sourceLang = "pl";
                browser.loadUrl(URLwikipl);
                translateMethod = 2;

                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    CookieManager.getInstance().setAcceptThirdPartyCookies(browser, true);
                } else {
                    CookieManager.getInstance().setAcceptCookie(true);
                }

            }
        });

        wprost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sourceLang = "pl";
                browser.loadUrl(UrlWprost);
                translateMethod = 2;

                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    CookieManager.getInstance().setAcceptThirdPartyCookies(browser, true);
                } else {
                    CookieManager.getInstance().setAcceptCookie(true);
                }

            }
        });

        elmundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sourceLang = "es";
                browser.loadUrl(URLElMundo);
                translateMethod = 2;
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    CookieManager.getInstance().setAcceptThirdPartyCookies(browser, true);
                } else {
                    CookieManager.getInstance().setAcceptCookie(true);
                }
            }
        });


        wikies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sourceLang = "es";
                browser.loadUrl(URLwikies);
                translateMethod = 2;
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    CookieManager.getInstance().setAcceptThirdPartyCookies(browser, true);
                } else {
                    CookieManager.getInstance().setAcceptCookie(true);
                }
            }
        });

    }






    private class ContentGoogleTranslate extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();


        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d("request!", "starting");


            String txt = JsoupTxtMain.getText().toString();

            String targetLang = "en";
            Translate translate = TranslateOptions.newBuilder().setApiKey(api).build().getService();
            Translation translation = translate.translate(txt,
                    Translate.TranslateOption.targetLanguage(targetLang),
                    Translate.TranslateOption.sourceLanguage(sourceLang));


            String translatedTxt = translation.getTranslatedText();
            // viewText.setText(translatedTxt);

            Log.d("contentGoogleTranslate",translatedTxt);

            JsoupTxtMain.setText(translatedTxt);
            return null;
        }
    }



    private class ContentWebScrape extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();

        }


        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            //JsoupTxt.setText("HELLO LOL");
            JsoupTxtMain.setText(title + outputtmp);
            progressDialog.dismiss();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d("request!", "starting");
            Document doc = null;

            try {
                //
                doc = Jsoup.connect(urlGet).timeout(12000).get();
                Log.d("after connect","after connect");
                Log.d("urlGet",urlGet);
                //title = doc.title();
                urlbody = doc.body().text();

                //Elements txt = doc.getElementsByTag("lead");
                Elements txt = doc.getElementsByTag("p");
                //Elements txt = doc.getElementsByClass("article-body polished");
                //txt2 = doc.select("#lead");
                txt2 = doc.select("body");
                //txt3 = doc.article-body polished
                //Log.d("LOL",title);
                Log.d("LOL1",urlbody);
                Log.d("LOL2",txt2.text());
                //Log.d("LOL3",txt.text());


                //Elements txt3 = doc.getElementsByTag("body");
                //Element txt3 = doc.select("body").first();
                Element txt3 = doc.select("body").first();
                Elements divChildren = txt3.children();


                for (Element el : divChildren  ) {
                    Log.d("ITERATE",el.text());
                    Log.d("ITERATE TAG",el.tagName().toString());
                    Log.d("ITERATE ATTRIBUTES",el.attributes().toString());
                }
                Log.d("BREAK ============","BREAK===============");

                //for (Element el : txt  ) {
                //Log.d("ITERATE",el.text());
                //Log.d("ITERATE TAG",el.tagName().toString());
                //  Log.d("ITERATE ATTRIBUTES",el.attributes().toString());
                //}
                //textView.setText(txt2.text());

                // title = txt.text();


                Elements paragraphs = doc.select("p");
                for(Element p : paragraphs)
                    Log.d("ITERATE d.select('p'')",p.text());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            Elements elements = doc.body().select("*");
            for (Element element : elements) {
                Log.d("nxt it",element.tagName().toString());
                Log.d("nxt it .text()",element.text());
                Log.d("nxt it .ToString()",element.toString());
            }

            Elements elements2 = doc.select("*");

            for (Element element : elements2) {
                Log.d("nxt it2",element.tagName().toString());
                Log.d("nxt it2 .text()",element.text());
                Log.d("nxt it2 .toString()",element.toString());
                //try {
                //    Log.d("nxt it2 children",element.children().first().tagName().toString());
                // } catch (Exception e) {
                ////     throw new RuntimeException(e);
                // }

                if (element.children().size() > 0)
                    Log.d("nxt it2 child",element.children().toString());


            }


            Elements elements3 = doc.body().children().select("*");
            for (Element element : elements3) {
                Log.d("nxt it3",element.tagName().toString());
                Log.d("nxt it3 .text()",element.text());
                Log.d("nxt it3 .toString()",element.toString());
            }

            outputtmp = "";
            Element elements4 = doc.select("div").first();
            for (Element element : elements4) {
                Log.d("nxt it4 doc.select('div').first()...tagName()",element.tagName().toString());
                Log.d("nxt it4 doc.select('div').first()...toString()",element.toString());

                if (element.tagName().toString() == "p"){
                    Log.d("UBER SELECT",element.text().toString());
                    outputtmp = outputtmp + element.text().toString();
                    outputtmp = outputtmp + "\n";
                }
            }


            for(Element e : doc.getAllElements()){      // all elements in html
                //Log.d("nxt it5",e.toString());
                Log.d("nxt it5",e.tag().toString());
                // tags.add(e.tagName().toLowerCase());    // add each tag in tags List
                //System.out.println("Tag: "+ e.tag()+" attributes = "+e.attributes());  // attributes with values in string
                //System.out.println("Tag: "+ e.tag()+" attributes = "+e.attributes().asList()); //attributes in List<Attribute>

                // for(Attribute att : e.attributes().asList()){ // for each tag get all attributes in one List<Attribute>
                //    System.out.print("Key: "+att.getKey()+ " , Value: "+att.getValue());
                //     System.out.println();
                // }
            }


            //Log.d("doc.toString()",doc.toString());

            Log.d("doc.toString(something)",doc.select("h1").text());
            Log.d("doc.select(p.article_p)",doc.select("p.article_p").text());
            //<p class="article_p" denne her, på businessinder tror jeg er good

            //doc.select("p.article_p").text();
            //title=txt2.text();

            String outputtmp_2 = doc.select("p.article_p").text().toString();
            title = doc.select("h1").text().toString();
            Log.d("doc.select('h1')",title);

            //String totaloutputtmp = "";
            //if(browser.getUrl().toString().contains("pl.wikipedia.org")){

            //    totaloutputtmp = title + "\n" + outputtmp;
           // }
           // else {

            //    totaloutputtmp = title + "\n" + outputtmp_2;
           // }

            //Prøvved post execute??
            //JsoupTxtMain.setText(title + outputtmp);// + "\n" + outputtmp_2);
            return null;
        }
    }





}