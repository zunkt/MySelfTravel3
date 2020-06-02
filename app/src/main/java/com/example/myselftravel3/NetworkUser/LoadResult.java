package com.example.myselftravel3.NetworkUser;

import com.example.myselftravel3.Account;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoadResult extends Result {
    @SerializedName("data")
    public List<Account> accounts;
}
