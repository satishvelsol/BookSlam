package com.teamtesla.satish.slambook.model;

import com.teamtesla.satish.slambook.FriendDetails;

import java.util.List;

/**
 * Created by user2 on 2/3/2018.
 */

public class ShowDetailsResponse {
    List<FriendDetails> friend_details;
    int response;

    public List<FriendDetails> getFriend_details() {
        return friend_details;
    }

    public int getResponse() {
        return response;
    }

    public void setFriend_details(List<FriendDetails> friend_details) {
        this.friend_details = friend_details;
    }

    public void setResponse(int response) {
        this.response = response;
    }
}

