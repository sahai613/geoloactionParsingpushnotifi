package com.beginner.example2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentNews extends Fragment implements RewardedVideoAdListener {
    private ListView mListview;
    private List<String> mNewsList;
    private List<Concert> mConcertsList;
    private RewardedVideoAd mRewardedVideoAd;


    public fragmentNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView= inflater.inflate(R.layout.fragment_news, container, false);
        mListview= (ListView) fragmentView.findViewById(R.id.Mylist);
        Button ad = (Button) fragmentView.findViewById(R.id.ad);
        MobileAds.initialize(getActivity().getApplicationContext(),"ca-app-pub-3940256099942544~3347511713");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getContext());
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();


        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }



        });
        // Use an activity context to get the rewarded video instance.



        //mNewsList=new ArrayList<String>();
        mConcertsList=new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("news");
            query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> concertsList, ParseException e) {
                if (e == null) {
//                    Log.d("score", "Retrieved " + concertsList.size() + " scores");
                    for (ParseObject parseObject : concertsList) {
                        String title= (String) parseObject.get("title");
                        String link= (String) parseObject.get("link");
                        String imageLink= (String) parseObject.get("imagelink");
                        Concert concert=new Concert(title,link,imageLink);
                        mConcertsList.add(concert);
                    }
                    mListview.setAdapter(new Myadapter());
                }


                else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Concert concert=mConcertsList.get(position);
                String link1=concert.getLink();
                openbrower(link1);
            }
        });
        return fragmentView;
    }
    public void openbrower(String link){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }
    public void loadRewardedVideoAd() {
        if (!mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                    new AdRequest.Builder().build());
        }
    }


    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(getContext());
        super.onPause();

    }

    @Override
    public void onResume() {
        mRewardedVideoAd.resume(getContext());
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(getContext());
        super.onDestroy();
    }

    private class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mConcertsList.size();
        }

        @Override
        public Object getItem(int position) {
            return mConcertsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
             View rowView=getActivity().getLayoutInflater().inflate(R.layout.row,null);
             Concert rowConcert=mConcertsList.get(position);
             TextView textrow=(TextView) rowView.findViewById(R.id.textview);
             textrow.setText(rowConcert.getTitle());
             ImageView image= (ImageView) rowView.findViewById(R.id.imageViewpicasso);
             Picasso.with(getActivity()).load(rowConcert.getImageLink()).into(image);
             return rowView;

        }
    }

}
