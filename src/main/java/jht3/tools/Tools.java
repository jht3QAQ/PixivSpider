package jht3.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jht3.IllustMangaInfo;
import jht3.Setting;
import okhttp3.Response;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
    public static String getPage(String url) {
        return (String) GetPage.getPage(url, true);
    }

    public static Response getHead(String url){return GetPage.getHead(url);}

    public static byte[] getByte(String url) {
        return (byte[]) GetPage.getPage(url, false);
    }

    public static byte[] getByte(String url,int from){
        return (byte[]) GetPage.getPage(url, false, (builder) -> builder.addHeader("Range", "bytes=" + from + '-').removeHeader("Cookie"));
    }

    public static byte[] getByte(String url,int from,int to){
        return (byte[]) GetPage.getPage(url, false, (builder) -> builder.addHeader("Range", "bytes=" + from + '-' + to).removeHeader("Cookie"));
    }

    public static String getPageUrl(String keyWord, int p) {
        return Setting.pixivUrl+"/ajax/search/artworks/" + keyWord + "?word=" + keyWord + "&mode=" + Setting.mode + "&p=" + p + "&type=" + Setting.type + "&lang=" + Setting.lang + "&s_mode=" + Setting.s_mode;
    }

    public static final JsonParser jParser = new JsonParser();

    public static List<IllustMangaInfo> getIllustMangaInfoList(String body) {
        List<IllustMangaInfo> illustMangaInfos = new LinkedList<>();
        JsonObject json;
        json = (JsonObject) Tools.jParser.parse(body);
        JsonArray datas = json.getAsJsonObject("body").getAsJsonObject("illustManga").getAsJsonArray("data");
        for (JsonElement data : datas) {
            JsonObject dataObj = data.getAsJsonObject();
            int illustId;
            if(dataObj.has("illustId")){
                illustId = dataObj.get("illustId").getAsInt();
            }else{
                illustId = dataObj.get("id").getAsInt();
            }
            String illustTitle;
            if(dataObj.has("illustTitle")){
                illustTitle = dataObj.get("illustTitle").getAsString();
            }else{
                illustTitle = dataObj.get("title").getAsString();
            }
            int userId = dataObj.get("userId").getAsInt();
            int width = dataObj.get("width").getAsInt();
            int height = dataObj.get("height").getAsInt();
            IllustMangaInfo info = new IllustMangaInfo(illustId, illustTitle, userId, width, height);
            illustMangaInfos.add(info);
            System.out.println("illustId: " + illustId + "\tillustTitle: " + illustTitle + "\tuserId" + userId + "\twidth" + width + "\theight" + height);
        }
        return illustMangaInfos;
    }

    public static String getName(IllustMangaInfo info) {
        Map<String, Object> map = new HashMap<>();
        map.put("baseName", info.baseName);
        map.put("illustId", info.illustId);
        map.put("illustTitle", info.illustTitle);
        map.put("bookmarkCount", info.bookmarkCount);
        map.put("viewCount", info.viewCount);
        map.put("urlOriginal", info.urlOriginal);
        map.put("width", info.width);
        map.put("height", info.height);
        map.put("userId", info.userId);

        return processTemplate(Setting.fileName, map);
    }

    private static String processTemplate(String template, Map<String, Object> params) {
        StringBuffer sb = new StringBuffer();
        Matcher m = Pattern.compile("\\$\\{\\w+}").matcher(template);
        while (m.find()) {
            String param = m.group();
            Object value = params.get(param.substring(2, param.length() - 1));
            m.appendReplacement(sb, value == null ? "" : value.toString());
        }
        m.appendTail(sb);
        return sb.toString();
    }
}