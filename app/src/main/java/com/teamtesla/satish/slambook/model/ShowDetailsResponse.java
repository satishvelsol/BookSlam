package com.teamtesla.satish.slambook.model;

import com.teamtesla.satish.slambook.FriendDetails;

/**
 * Created by user2 on 2/3/2018.
 */

public class ShowDetailsResponse {

    public FriendDetails FriendDetails;
    int response;

    public com.teamtesla.satish.slambook.FriendDetails getFriendDetails() {
        return FriendDetails;
    }

    public void setFriendDetails(com.teamtesla.satish.slambook.FriendDetails friendDetails) {
        FriendDetails = friendDetails;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }




}
