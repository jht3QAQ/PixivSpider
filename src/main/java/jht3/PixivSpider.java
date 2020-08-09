package jht3;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jht3.tools.Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PixivSpider {
    public static void main(String[] args) throws InterruptedException {
        Setting.initSetting();
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
                fixedThreadPool.execute(new Runnable() {
                    IllustMangaInfo info;
                    public String url = Setting.pixivUrl+"/ajax/illust/";

                    public Runnable setInfo(IllustMangaInfo info){
                        this.info=info;
                        return this;
                    }

                    @Override
                    public void run() {
                        this.getInfo();
                        count.countDown();
                    }


                    public final JsonParser jParser = new JsonParser();
                    public void getInfo(){
                        String body = Tools.getPage(url + info.illustId);
                        JsonObject jsonObj = (JsonObject) this.jParser.parse(body);
                        int viewCount = jsonObj.getAsJsonObject("body").get("viewCount").getAsInt();
                        int bookmarkCount = jsonObj.getAsJsonObject("body").get("bookmarkCount").getAsInt();
                        System.out.println("illustTitle: "+info.illustTitle+"\tviewCount"+viewCount+"\tbookmarkCount"+bookmarkCount);
                        info.viewCount = viewCount;
                        info.bookmarkCount = bookmarkCount;
                        if ((Setting.how.equals("and")&&viewCount >= Setting.minViewCount && bookmarkCount >= Setting.minBookmarkCount)||
                            (Setting.how.equals("or")&&(viewCount>=Setting.minViewCount||bookmarkCount>=Setting.minBookmarkCount))) {
                            System.out.println("获取下载链接中: "+info.illustId+"\t"+info.illustTitle);
                            this.getDownloadUrl();
                        }
                    }

                    public void getDownloadUrl(){
                        String url = Setting.pixivUrl+"/ajax/illust/";
                        String body = Tools.getPage(url + info.illustId + "/pages?lang=zh");
                        JsonObject jsonObj = (JsonObject) this.jParser.parse(body);
                        JsonArray jsonArr = jsonObj.getAsJsonArray("body");
                        int i = 0;
                        if(jsonArr.size()> Setting.maxImg){
                            System.out.println("跳过多图下载: "+jsonArr.size());
                            return;
                        }
                        for (JsonElement imgs : jsonArr) {
                            if(imgs.getAsJsonObject().getAsJsonObject("urls").get("original").getAsString().contains("ugoira")) {
                                System.out.println("跳过动画下载");
                                return;
                            }
                            System.out.println("正在下载: " + imgs.getAsJsonObject().getAsJsonObject("urls").get("original").getAsString());
                            Pattern pattern = Pattern.compile("[0-9]+_p[0-9]+.[a-zA-Z]+");
                            Matcher matcher = pattern.matcher(imgs.getAsJsonObject().getAsJsonObject("urls").get("original").getAsString());
                            matcher.find();
                            info.baseName=matcher.group(0);
                            this.download(imgs.getAsJsonObject().getAsJsonObject("urls").get("original").getAsString(), Tools.getName(info));
                        }
                    }

                    public void download(String url,String filename){
                        if(filename.contains("ugoira")){
                            System.out.println("跳过下载动画");
                            return;
                        }
                        FileOutputStream fos;
                        try {
                            File file = new File(Setting.fileUrl, filename);
                            if(!file.exists()){
                                byte[] body= Tools.getByte(url);
                                file.getParentFile().mkdirs();
                                file.createNewFile();
                                fos = new FileOutputStream(file);
                                fos.write(body);
                                fos.close();
                            }else{
                                System.out.println("文件已存在，跳过下载: "+filename);
                            }
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.setInfo(info));
            }
            count.await();
            fixedThreadPool.shutdown();
        }
    }
}
