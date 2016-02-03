package com.dante.knowledge.news.other;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dante.knowledge.R;
import com.dante.knowledge.news.interf.OnListFragmentInteractionListener;
import com.dante.knowledge.news.model.FreshItem;
import com.dante.knowledge.news.model.FreshNews;
import com.dante.knowledge.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yons on 16/2/3.
 */
public class FreshListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String FRESH_ITEM = "fresh_item";
    private Context context;
    private List<FreshItem> freshItems = new ArrayList<>();
    private FreshNews news;
    private OnListFragmentInteractionListener mListener;

    public FreshListAdapter(Context context, OnListFragmentInteractionListener listener) {
        this.context = context;
        mListener = listener;
    }

    public void addNews(FreshNews news) {
        this.news=news;
        freshItems.addAll(news.getPosts());
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_fresh_item, parent, false);
        return new FreshViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final FreshViewHolder viewHolder = (FreshViewHolder) holder;
        viewHolder.freshItem = freshItems.get(position);
        String imgUrl = viewHolder.freshItem.getCustom_fields().getThumb_c().get(0);

        viewHolder.mTitle.setText(viewHolder.freshItem.getTitle());
        ImageUtil.load(viewHolder.itemView.getContext(), imgUrl, viewHolder.mImage);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(viewHolder);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return freshItems.size();
    }

    public void clear() {
        freshItems.clear();
    }

    public class FreshViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImage;
        public final TextView mTitle;
        public final View mItem;
        public FreshItem freshItem;

        public FreshViewHolder(View view) {
            super(view);
            mImage = (ImageView) view.findViewById(R.id.story_img);
            mTitle = (TextView) view.findViewById(R.id.story_title);
            mItem = view.findViewById(R.id.story_item);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}
