package com.teamtesla.satish.slambook.model;

import com.teamtesla.satish.slambook.friends;

import java.util.List;

/**
 * Created by user4 on 2/2/2018.
 */

public class mainResponse
{
   List<friends> friends;
    int response;

    public List<friends> getFriends()
    {
        return friends;
    }

    public void setFriends(List<friends> friends) {
        this.friends = friends;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }
}
