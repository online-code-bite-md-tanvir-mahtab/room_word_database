package com.tanvircodder.exmple.roomtanvir;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tanvircodder.exmple.roomtanvir.model.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordView>{
    private Context context;
    private List<Word> mData;

    public WordAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WordView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.word_list_item,parent,false);
        return new WordView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordView holder, int position) {
        Word word = mData.get(position);
        holder.mTextView.setText(word.getWordName());
    }

    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        return mData.size();
    }
    public void swapData(List<Word> mData){
        this.mData = mData;
        if (mData != null){
            Toast.makeText(context,"There is  Word",Toast.LENGTH_LONG).show();
            notifyDataSetChanged();
        }else{
            
        }
    }

    class WordView extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextView;
        public WordView(@NonNull View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textViewStatus);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Word word = mData.get(getAdapterPosition());
            Intent intent = new Intent(context,UpdateWordActivity.class);
            intent.putExtra("word",word);
            Toast.makeText(context,"the position:" + word.getId(),Toast.LENGTH_LONG).show();
//            intent.putExtra("word", Word.class);

            context.startActivity(intent);
        }
    }
}
