package com.zzzhyun.subwayapp.module;

/**
 * Created by ZHYUN on 2017-10-19.
 */

public class ErrorMessage
{
    private String total;

    private String message;

    private String status;

    private String developerMessage;

    private String link;

    private String code;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getDeveloperMessage ()
    {
        return developerMessage;
    }

    public void setDeveloperMessage (String developerMessage)
    {
        this.developerMessage = developerMessage;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+", message = "+message+", status = "+status+", developerMessage = "+developerMessage+", link = "+link+", code = "+code+"]";
    }
}