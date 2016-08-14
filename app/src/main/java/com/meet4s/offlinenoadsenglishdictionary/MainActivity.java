package com.meet4s.offlinenoadsenglishdictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    TreeMap<String, Integer> map = new TreeMap<String, Integer>();
    ArrayList<Word> list = new ArrayList<Word>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readAssets();
        setSearchEvent();
    }

    private void setSearchEvent() {
        EditText search = (EditText) findViewById(R.id.search);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString().trim().toLowerCase();
                if (keyword.isEmpty()) {
                    WordAdapter adapter = new WordAdapter(MainActivity.this, list);
                    ListView listView = (ListView) findViewById(R.id.list);
                    listView.setAdapter(adapter);
                } else {
                    ArrayList<Word> showing = new ArrayList<Word>();
                    SortedMap<String, Integer> sm = map.subMap(keyword, keyword + Character.MAX_VALUE);
                    for(Map.Entry<String,Integer> entry : sm.entrySet()) {
                        String key = entry.getKey();
                        Integer value = entry.getValue();
                        showing.add(list.get(value));
                    }

                    WordAdapter adapter = new WordAdapter(MainActivity.this, showing);
                    ListView listView = (ListView) findViewById(R.id.list);
                    listView.setAdapter(adapter);
                }
            }
        });
    }

    private void readDictionaryAsset(int index) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("wordnet.txt." + index), "UTF-8"));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                String[] parts = mLine.split("\\|\\|");
                map.put(parts[0].toLowerCase(), list.size());
                list.add(new Word(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private void readAssets() {
        for (int i = 1; i <= 12; i++) {
            readDictionaryAsset(i);
        }
        WordAdapter adapter = new WordAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
