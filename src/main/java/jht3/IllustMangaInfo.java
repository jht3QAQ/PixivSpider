package jht3;

import com.google.gson.JsonObject;

public class IllustMangaInfo {
    public boolean getIllustInfo = false;

    public int illustId;
    public String illustTitle;
    public int userId;
    public int width;
    public int height;

    public String urlOriginal;
    public int bookmarkCount;
    public int viewCount;

    public String baseName;

    public IllustMangaInfo(int illustId,String illustTitle,int userId,int width,int height){
        this.illustId=illustId;
        this.illustTitle=illustTitle;
        this.userId=userId;
        this.width=width;
        this.height=height;
    }

    public JsonObject getJsonObj(){
        JsonObject jo=new JsonObject();
        jo.addProperty("getIllustInfo",getIllustInfo);
        jo.addProperty("illustId",illustId);
        jo.addProperty("illustTitle",illustTitle);
        jo.addProperty("userId",userId);
        jo.addProperty("width",width);
        jo.addProperty("height",height);
        jo.addProperty("urlOriginal",urlOriginal);
        jo.addProperty("bookmarkCount",bookmarkCount);
        jo.addProperty("viewCount",viewCount);
        return jo;
    }
}
