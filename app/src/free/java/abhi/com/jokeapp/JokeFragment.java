package abhi.com.jokeapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import abhi.com.uilib.JokeActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokeFragment extends Fragment implements IAsyncListener{

    InterstitialAd mInterstitialAd;

        public JokeFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_joke, container, false);

            AdView mAdView = (AdView) root.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);


            mInterstitialAd = new InterstitialAd(getActivity());

            // set the ad unit ID
            mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));

            final AdRequest interAdRequest = new AdRequest.Builder()
                    .build();

            // Load ads into Interstitial Ads
            mInterstitialAd.loadAd(interAdRequest);

            mInterstitialAd.setAdListener(new AdListener(){

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    mInterstitialAd.loadAd(interAdRequest);
                }
            });
            ((Button) root.findViewById(R.id.getJoke)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tellJoke();
                }
            });

            return root;
        }

        private void tellJoke() {
            new GetJokeAsync(this).execute();
        }

        @Override
        public void onAsyncStart() {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }

        @Override
        public void onAsyncFinish(String joke) {

            Intent intent = new Intent(getActivity(), JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE, joke);
            startActivity(intent);
        }


}
