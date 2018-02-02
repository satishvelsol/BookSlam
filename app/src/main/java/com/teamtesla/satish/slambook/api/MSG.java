package com.teamtesla.satish.slambook.api;

/**
 * Created by Admin on 1/3/2018.
 */

public class MSG
{
    public Integer success;
    public String message;

    public MSG(Integer success, String message)
    {
        this.success = success;
        this.message = message;
    }

    public Integer getSuccess()
    {
        return success;
    }

    public void setSuccess(Integer success)
    {
        this.success = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
