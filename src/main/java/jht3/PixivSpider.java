package jht3;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jht3.tools.GetImg;
import jht3.tools.Tools;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PixivSpider {
    public static void main(String[] args) throws InterruptedException {
        Setting.initSetting();
        switch (Setting.searchMode) {
            case "keyword":
                getByKetname();
                break;
            case "uid":
                getByUid();
                break;
        }
    }

    public static void getByKetname() throws InterruptedException {
        for(int i = Setting.startPage; i<= Setting.endPage; i++){
            System.out.println("正在获取第 "+i+" 页:");
            String body = Tools.getPage(Tools.getPageUrl(Setting.keyword, i));
            List<IllustMangaInfo> list=Tools.getIllustMangaInfoList(body);
            if(list.size()==0) {
                System.out.println("全部页面已获取完成");
                return;
            }
            //一阶段完成~~~~~~
            CountDownLatch count = new CountDownLatch(list.size());
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Setting.threadPoolSize);

            for(IllustMangaInfo info:list){
                fixedThreadPool.execute(new GetImg(info,count));
            }
            count.await();
            fixedThreadPool.shutdown();
        }
    }

    public static void getByUid() throws InterruptedException {
        System.out.println("正在获取画师 "+Setting.keyword+" 作品信息");
        String body=Tools.getPage(Setting.pixivUrl+"/ajax/user/"+Setting.keyword+"/profile/all?lang="+Setting.lang);
        JsonObject json = (JsonObject) Tools.jParser.parse(body);
        Set<Map.Entry<String, JsonElement>> set=json.getAsJsonObject("body").getAsJsonObject("illusts").entrySet();
        System.out.println(Setting.keyword+" 共有 "+set.size()+" 件作品");
        String sStart=Setting.pixivUrl+"/ajax/user/"+Setting.keyword+"/profile/illusts?";
        String sEnd="work_category=illustManga&is_first_page=1&lang=zh";
        StringBuffer sb = new StringBuffer(sStart);
        int i=0;
        for(Map.Entry<String, JsonElement> enty:set){
            if(i<48) {
                sb.append("ids[]=").append(enty.getKey()).append("&");
            }else{
                sb.append(sEnd);
                String body2=Tools.getPage(sb.toString());
                List<IllustMangaInfo> illustMangaInfos = new LinkedList<>();
                JsonObject json2 = (JsonObject) Tools.jParser.parse(body2);
                Set<Map.Entry<String, JsonElement>> set2=json2.getAsJsonObject("body").getAsJsonObject("works").entrySet();
                for(Map.Entry<String, JsonElement> entry:set2){
                    JsonObject illust=entry.getValue().getAsJsonObject();
                    IllustMangaInfo info=new IllustMangaInfo(
                            illust.get("illustId").getAsInt(),
                            illust.get("illustTitle").getAsString(),
                            illust.get("userId").getAsInt(),
                            illust.get("width").getAsInt(),
                            illust.get("height").getAsInt()
                    );
                    illustMangaInfos.add(info);
                }
                CountDownLatch count = new CountDownLatch(illustMangaInfos.size());
                ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Setting.threadPoolSize);
                for(IllustMangaInfo info:illustMangaInfos){
                    fixedThreadPool.execute(new GetImg(info,count));
                }
                count.await();
                fixedThreadPool.shutdown();

                sb=new StringBuffer(sStart);
                i=0;
            }
            i++;
        }
        sb.append(sEnd);
        String body2=Tools.getPage(sb.toString());
        List<IllustMangaInfo> illustMangaInfos = new LinkedList<>();
        JsonObject json2 = (JsonObject) Tools.jParser.parse(body2);
        Set<Map.Entry<String, JsonElement>> set2=json2.getAsJsonObject("body").getAsJsonObject("works").entrySet();
        for(Map.Entry<String, JsonElement> entry:set2){
            JsonObject illust=entry.getValue().getAsJsonObject();
            IllustMangaInfo info=new IllustMangaInfo(
                    illust.get("illustId").getAsInt(),
                    illust.get("illustTitle").getAsString(),
                    illust.get("userId").getAsInt(),
                    illust.get("width").getAsInt(),
                    illust.get("height").getAsInt()
            );
            illustMangaInfos.add(info);
        }
        CountDownLatch count = new CountDownLatch(illustMangaInfos.size());
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Setting.threadPoolSize);
        for(IllustMangaInfo info:illustMangaInfos){
            fixedThreadPool.execute(new GetImg(info,count));
        }
        count.await();
        fixedThreadPool.shutdown();
    }
}
