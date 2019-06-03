package com.rootstack.businessnearapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.rootstack.businessnearapp.R;
import com.rootstack.businessnearapp.base.BaseActivity;
import com.rootstack.businessnearapp.databinding.ActivityMainBinding;
import com.rootstack.businessnearapp.model.Result;
import com.rootstack.businessnearapp.view.viewAdapters.UsersRecyclerViewAdapter;
import com.rootstack.businessnearapp.viewModel.MainViewModel;

import java.util.List;

public class MainActivity extends BaseActivity {

    public ActivityMainBinding binding;
    private UsersRecyclerViewAdapter mUsersRecyclerViewAdapter;
    private MainViewModel mMainViewModel;
    private LinearLayoutManager linearLayoutManager;
    private boolean isFinishTipe = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mUsersRecyclerViewAdapter = new UsersRecyclerViewAdapter(this.getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainViewModel(mMainViewModel);
        binding.rcvUserList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rcvUserList.setAdapter(mUsersRecyclerViewAdapter);
        binding.editTextSearch.setEnabled(false);
        binding.rcvUserList.setLayoutManager(linearLayoutManager);

        mMainViewModel.listUser.observe(this, (List<Result> users) -> {
            if (users != null) {
                if (users.size() > 0) {
                    mUsersRecyclerViewAdapter.setUserList(users);
                    mUsersRecyclerViewAdapter.notifyDataSetChanged();
                    binding.editTextSearch.setEnabled(true);
                }
            } else {
                this.showAlertMessage(this.getBaseContext().getResources().getString(R.string.dialog_lost_connection_desc_list_empty));
            }
        });

        mMainViewModel.listVisibility.observe(this, visibility -> {
            binding.rcvUserList.setVisibility(visibility);
        });

        mMainViewModel.progressVisibility.observe(this, visibility -> {
            binding.progressBar.setVisibility(visibility);
        });

        binding.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String charString = charSequence.toString();
                isFinishTipe = false;
                mMainViewModel.filterList(charString);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                isFinishTipe = true;
            }
        });

        binding.rcvUserList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isFinishTipe){
                    if(binding.editTextSearch.getText().toString().isEmpty()){
                        if(isLastItemDisplaying(recyclerView)){
                            binding.editTextSearch.setText("");
                            listUsers();
                        }
                    }
                }
            }
        });

        listUsers();
    }


    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    private void listUsers(){
        binding.progressBar.setVisibility(View.VISIBLE);
        if(this.isNetworkAvailable()){
            this.mMainViewModel.fetchUsersList();
        }else{
            this.showAlertMessage(this.getBaseContext().getResources().getString(R.string.dialog_lost_connection_desc_list_fill));
        }
    }
}