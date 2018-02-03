package com.teamtesla.satish.slambook.model;

import com.teamtesla.satish.slambook.FriendDetails;

import java.util.List;

/**
 * Created by user2 on 2/3/2018.
 */

public class ShowDetailsResponse {
    List<FriendDetails> friend_details;
    int response;

    public List<FriendDetails> getFriends()
    {
        return friend_details;
    }

    public void setFriends(List<FriendDetails> friend) {
        friend_details = friend;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
    this.response = response;
}

}

