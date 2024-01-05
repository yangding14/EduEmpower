package com.example.forum.instructors;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduempoweryd.forum.DiscussionItem;
import com.example.eduempoweryd.forum.DiscussionItemAdapter;
import com.example.forum.R;
import com.example.eduempoweryd.forum.RecyclerViewClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InViewForum extends AppCompatActivity implements DiscussionItemAdapter.OnEditButtonClickListener {
    private List<DiscussionItem> discussionItemList = new ArrayList<>();
    private DiscussionItemAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_view_forum);

        // Assuming you have a DatabaseReference reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Discussion");

        // Assuming myRef points to the "discussionItems" node in your Firebase Database
        databaseReference.addValueEventListener(new ValueEventListener() {
            final RecyclerView recyclerView = findViewById(R.id.recycler);

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                discussionItemList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DiscussionItem discussionItem = snapshot.getValue(DiscussionItem.class);
                    discussionItemList.add(discussionItem);
                }

                // Now you have the list of DiscussionItem objects, you can use it as needed.
                // For example, you can pass it to your RecyclerView adapter.
                DiscussionItemAdapter adapter = new DiscussionItemAdapter(InViewForum.this, discussionItemList, InViewForum.this);
                adapter.setStudentView(false);
                recyclerView.setAdapter(adapter);
                recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(InViewForum.this, recyclerView, new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Handle item click, for example, launch a new activity
                        Intent intent = new Intent(InViewForum.this, InEditForum.class);
                        startActivity(intent);
                    }
                }));


                // Set the itemCount in your adapter
                adapter.setItemCount(discussionItemList.size());

                searchView = findViewById(R.id.searchViewSearch);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        List<DiscussionItem> filteredList = new ArrayList<>();

                        for (DiscussionItem item : discussionItemList) {
                            if (item.getTopic().toLowerCase().contains(newText.toLowerCase())) {
                                filteredList.add(item);
                            }
                        }

                        adapter.updateList(filteredList);
                        return true;
                    }

                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                CharSequence text = "Fail to get data!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(InViewForum.this, text, duration);
                toast.show();
            }

        });
    }

    @Override
    public void onEditButtonClick(int position) {
        // Handle the edit button click for the item at the given position
        // You can open an editing activity or dialog here
        // Pass the necessary data to the editing interface

        DiscussionItem selectedDiscussionItem = discussionItemList.get(position);

        // Start the InEditForum activity and pass the item key
        Intent intent = new Intent(this, InEditForum.class);
        intent.putExtra("itemKey", selectedDiscussionItem.getKey());
        startActivity(intent);
    }
}
