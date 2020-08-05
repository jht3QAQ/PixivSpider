package jht3.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jht3.IllustMangaInfo;
import jht3.Setting;
import java.util.LinkedList;
import java.util.List;

public class Tools {
    public static final String getPage(String url){
        return (String)GetPage.getPage(url,true);
    }

    public static final byte[] getByte(String url){
        return (byte[])GetPage.getPage(url,false);
    }
    public static final String getPageUrl(String keyWord,int p){
        return "https://www.pixiv.net/ajax/search/artworks/"+keyWord+"?word="+keyWord+"&mode="+ Setting.mode+"&p="+p+"&type="+Setting.type+"&lang"+Setting.lang+"s_mode"+Setting.s_mode;
    }


    private static final JsonParser jParser = new JsonParser();
    public static final List<IllustMangaInfo> getIllustMangaInfoList(String body){
        List<IllustMangaInfo> illustMangaInfos = new LinkedList<IllustMangaInfo>();
        JsonObject json = null;
        json = (JsonObject) Tools.jParser.parse(body);
        JsonArray datas = json.getAsJsonObject("body").getAsJsonObject("illustManga").getAsJsonArray("data");
        for(JsonElement data:datas){
            JsonObject dataObj=data.getAsJsonObject();
            int illustId=dataObj.get("illustId").getAsInt();
            String illustTitle=dataObj.get("illustTitle").getAsString();
            int userId= dataObj.get("userId").getAsInt();
            int width=dataObj.get("width").getAsInt();
            int height=dataObj.get("height").getAsInt();
            IllustMangaInfo info=new IllustMangaInfo(illustId,illustTitle,userId,width,height);
            illustMangaInfos.add(info);
            System.out.println("illustId: "+illustId+"\tillustTitle: "+illustTitle+"\tuserId"+userId+"\twidth"+width+"\theight"+height);
        }
        return illustMangaInfos;
    }
}
