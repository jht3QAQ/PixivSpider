package jht3;

import com.google.gson.JsonObject;

public class IllustMangaInfo {
    boolean getIllustInfo = false;

    int illustId;
    String illustTitle;
    int userId;
    int width;
    int height;

    String urlOriginal;
    int bookmarkCount;
    int viewCount;

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
