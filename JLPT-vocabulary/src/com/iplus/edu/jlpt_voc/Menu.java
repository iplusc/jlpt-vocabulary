
package com.iplus.edu.jlpt_voc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.iplus.edu.jlpt_voc.utils.Loading;

public class Menu extends Activity {
    private TableLayout root;
    private ImageView n1Btn;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        root = (TableLayout) findViewById(R.id.root);
        n1Btn = (ImageView) findViewById(R.id.button_n1);

        OnClickListener clickListener = new OnClickListener() {
            Menu self = Menu.this;

            public void onClick(View v) {
                if (v.equals(n1Btn)) {
                    root.setAlpha((float) 0.0);
                    Intent intent = new Intent(Menu.this, Main.class);
                    Loading.show(Menu.this);
                    self.startActivity(intent);
                    overridePendingTransition(R.drawable.fade_in, R.drawable.fade_out);
                }
            }
        };

        n1Btn.setOnClickListener(clickListener);
    }
}
