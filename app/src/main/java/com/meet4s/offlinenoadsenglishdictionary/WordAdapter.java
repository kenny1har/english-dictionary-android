package com.meet4s.offlinenoadsenglishdictionary;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word current = getItem(position);

        TextView word = (TextView) listItemView.findViewById(R.id.word);
        word.setText(current.getWord());

        TextView type = (TextView) listItemView.findViewById(R.id.type);
        type.setText(current.getType());

        TextView definition = (TextView) listItemView.findViewById(R.id.definition);
        definition.setText(current.getDefinition());

        return listItemView;
    }

    public WordAdapter(Activity context, ArrayList<Word> words) {
        super(context, 0, words);
    }
}
