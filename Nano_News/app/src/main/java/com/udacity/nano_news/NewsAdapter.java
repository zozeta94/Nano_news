package com.udacity.nano_news;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zozeta on 27/09/2017.
 */
public class NewsAdapter extends ArrayAdapter<News> {


        public NewsAdapter(Activity context, List<News> NewsList) {
            super(context, 0, NewsList);
        }

        /**
         * Provides a view for an AdapterView (ListView, GridView, etc.)
         *
         * @param position    The position in the list of data that should be displayed in the
         *                    list item view.
         * @param convertView The recycled view to populate.
         * @param parent      The parent ViewGroup that is used for inflation.
         * @return The View for the position in the AdapterView.
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Check if the existing view is being reused, otherwise inflate the view
            View listItemView = convertView;
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
            }

            News currentNews = getItem(position);

            TextView TitleTextView = (TextView) listItemView.findViewById(R.id.title_tv);

            TitleTextView.setText(currentNews.getTitle());

            TextView AuthorTextView = (TextView) listItemView.findViewById(R.id.author_tv);

            AuthorTextView.setText(currentNews.getAuthor());

            TextView SectionTextView = (TextView) listItemView.findViewById(R.id.section_tv);

            SectionTextView.setText(currentNews.getSection());

            TextView DateTextView = (TextView) listItemView.findViewById(R.id.dateP_tv);

            DateTextView.setText(currentNews.getDate());


            return listItemView;
        }
    }

