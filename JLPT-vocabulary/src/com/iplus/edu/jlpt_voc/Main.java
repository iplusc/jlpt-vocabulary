
package com.iplus.edu.jlpt_voc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.iplus.edu.jlpt_voc.model.Vocabulary;

public class Main extends Activity {
    private static String TAG = "Main";
    private int currentCount = 0;
    private List<Vocabulary> itemList;
    private TextView numberTV;
    private TextView titleTV;
    private TextView kanaTV;
    private TextView sampleTV;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.numberTV = (TextView) findViewById(R.id.number);
        this.titleTV = (TextView) findViewById(R.id.title);
        this.kanaTV = (TextView) findViewById(R.id.kana);
        this.sampleTV = (TextView) findViewById(R.id.sample);

        this.itemList = this.getVocData();
        if (this.currentCount != 0) {
            this.setUI(this.currentCount);
        } else {
            this.setUI(0);
        }
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
                        Log.d("tag", "Start Document");
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
            Log.d("tag", "End Document");

            // 結果をログ出力
            for (Vocabulary i : itemList) {
                Log.d("tag", "title = " + i.title
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
            String kana = " ひながな: " + item.kana;
            this.kanaTV.setText(kana);
            String sample = " 用例: " + item.sample;
            this.sampleTV.setText(sample);
        }
    }

}
