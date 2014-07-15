
package com.iplus.edu.jlpt_voc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.iplus.edu.jlpt_voc.model.Vocabulary;
import com.iplus.edu.jlpt_voc.utils.Loading;
import com.iplus.edu.jlpt_voc.utils.SimpleGestureFilter;
import com.iplus.edu.jlpt_voc.utils.SimpleGestureFilter.SimpleGestureListener;

public class Main extends Activity implements SimpleGestureListener {
    private static String TAG = "Main";
    private int currentCount = 0;
    private List<Vocabulary> itemList;
    private TextView numberTV;
    private TextView titleTV;
    private TextView kanaTV;
    private TextView sampleTV;

    LinearLayout layout_ad;
    AdView adView;

    private SimpleGestureFilter detector;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.adunitid));
        adView.setAdSize(AdSize.SMART_BANNER);

        layout_ad = (LinearLayout) findViewById(R.id.layout_ad);
        layout_ad.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        this.numberTV = (TextView) findViewById(R.id.number);
        this.titleTV = (TextView) findViewById(R.id.title);
        this.kanaTV = (TextView) findViewById(R.id.kana);
        this.sampleTV = (TextView) findViewById(R.id.sample);
        if (this.itemList == null || this.itemList.size() == 0) {
            this.itemList = this.getVocData();
        }
        Loading.hide();
        if (this.currentCount != 0) {
            this.setUI(this.currentCount);
        } else {
            this.setUI(0);
        }

        // Detect touched area
        detector = new SimpleGestureFilter(this, this);
    }

    private List<Vocabulary> getVocData() {
        XmlResourceParser xpp = getResources().getXml(R.xml.data);
        List<Vocabulary> itemList = new ArrayList<Vocabulary>();
        try {
            Vocabulary item = null;
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                final String name = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        // ドキュメント開始
                        Log.d(TAG, "Start Document");
                        break;
                    case XmlPullParser.START_TAG:
                        // 開始タグ <xxxx>
                        if ("item".equals(name)) {
                            // 開始タグ - <item>
                            item = new Vocabulary();
                        } else if ("title".equals(name)) {
                            // <title>タグのテキストを設定
                            item.title = xpp.nextText();
                        } else if ("kana".equals(name)) {
                            // <kana>タグのテキストを設定
                            item.kana = xpp.nextText();
                        } else if ("sample".equals(name)) {
                            // <sample>タグのテキストを設定
                            item.sample = xpp.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        // 終了タグ</xxxx>
                        if ("item".equals(name)) {
                            // 終了タグ - </item>
                            itemList.add(item);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xpp.next();
            }

            // ドキュメント終了
            Log.d(TAG, "End Document");

            // 結果をログ出力
            for (Vocabulary i : itemList) {
                Log.d(TAG, "title = " + i.title
                        + " / kana = " + i.kana);
            }
        } catch (XmlPullParserException e) {
            Log.e(TAG, e.toString());
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } finally {
            // 処理なし
        }

        return itemList;
    }

    private void setUI(int count) {
        if (this.itemList != null && this.itemList.get(count) != null) {
            String numberStr = String.valueOf(count + 1) + "/"
                    + String.valueOf(this.itemList.size());
            this.numberTV.setText(numberStr);
            Vocabulary item = this.itemList.get(count);
            String title = item.title;
            this.titleTV.setText(title);
            String kana = item.kana;
            this.kanaTV.setText(kana);
            String sample = item.sample;
            sample = sample.replace("\\n", System.getProperty("line.separator"));
            this.sampleTV.setText(sample);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onSwipe(int direction) {
        String str = "";
        switch (direction) {
            case SimpleGestureFilter.SWIPE_RIGHT:
                str = "Swipe Right";
                if (this.currentCount > 0) {
                    this.currentCount -= 1;
                } else {
                    this.currentCount = 0;
                }
                this.audioTone();
                break;
            case SimpleGestureFilter.SWIPE_LEFT:
                str = "Swipe Left";
                if (this.currentCount < this.itemList.size() - 1) {
                    this.currentCount += 1;
                } else {
                    this.currentCount = this.itemList.size() - 1;
                }
                this.audioTone();
                break;
            case SimpleGestureFilter.SWIPE_DOWN:
                str = "Swipe Down";
                break;
            case SimpleGestureFilter.SWIPE_UP:
                str = "Swipe Up";
                break;
        }
        this.setUI(this.currentCount);
        Log.d(TAG, str);
    }

    @Override
    public void onDoubleTap() {
        Log.d(TAG, "Double Tap");
    }

    @Override
    public void onPause() {
        adView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        adView.resume();
    }

    @Override
    public void onDestroy() {
        adView.destroy();
        super.onDestroy();
    }

    private ToneGenerator toneGenerator = new ToneGenerator(
            AudioManager.STREAM_SYSTEM,
            ToneGenerator.TONE_CDMA_DIAL_TONE_LITE);;

    private void audioTone() {
        toneGenerator.stopTone();
        toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP);
    }
}
