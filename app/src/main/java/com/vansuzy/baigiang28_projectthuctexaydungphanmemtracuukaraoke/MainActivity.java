package com.vansuzy.baigiang28_projectthuctexaydungphanmemtracuukaraoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

import com.vansuzy.adapter.MusicAdapter;
import com.vansuzy.model.Music;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvBaiHatGoc;
    ArrayList<Music> dsBaiHatGoc;
    MusicAdapter adapterBaiHatGoc;

    ListView lvBaiHatYeuThich;
    ArrayList<Music> dsBaiHatYeuThich;
    MusicAdapter adapterBaiHatYeuThich;

    TabHost tabHost;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("t1")) {
                    xuLyHienThiBaiHatGoc();
                } else if (tabId.equalsIgnoreCase("t2")) {
                    xuLyHienThiBaiHatYeuThich();
                }
            }
        });
    }

    private void xuLyHienThiBaiHatYeuThich() {
        dsBaiHatYeuThich.clear();   // xóa dữ liệu cũ
        for (Music music:dsBaiHatGoc) {
            if (music.isThich()) {
                dsBaiHatYeuThich.add(music);
            }
            adapterBaiHatYeuThich.notifyDataSetChanged();
        }
    }

    private void xuLyHienThiBaiHatGoc() {
    }

    private void addControls() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(R.drawable.music));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.musicfavorite));
        tabHost.addTab(tab2);

        lvBaiHatGoc = (ListView) findViewById(R.id.lvBaiHatGoc);
        dsBaiHatGoc = new ArrayList<>();
        adapterBaiHatGoc = new MusicAdapter(
                MainActivity.this,
                R.layout.item,
                dsBaiHatGoc
        );
        lvBaiHatGoc.setAdapter(adapterBaiHatGoc);

        lvBaiHatYeuThich = (ListView) findViewById(R.id.lvBaiHatYeuThich);
        dsBaiHatYeuThich = new ArrayList<>();
        adapterBaiHatYeuThich = new MusicAdapter(
                MainActivity.this,
                R.layout.item,
                dsBaiHatYeuThich
        );
        lvBaiHatYeuThich.setAdapter(adapterBaiHatYeuThich);

        giaLapBaiHat();
    }

    private void giaLapBaiHat() {
        dsBaiHatGoc.add(new Music("55555", "Không yêu đừng nói lời cay đắng", "Ngọt Ngào", false));
        dsBaiHatGoc.add(new Music("33333", "Lòng mẹ", "Ngọc Sơn", true));
        dsBaiHatGoc.add(new Music("12345", "Riêng một góc trời", "Tuấn Ngọc", false));
        dsBaiHatGoc.add(new Music("67890", "Ly cà phê Ban Mê", "Siu Black", false));
        adapterBaiHatGoc.notifyDataSetChanged();
    }
}
