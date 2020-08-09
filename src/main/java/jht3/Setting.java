package jht3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Setting {
    public static String pixivUrl="https://pixiv.net";

    public static int startPage=1;
    public static int endPage=1000;

    public static String cookie = "first_visit_datetime_pc=2020-07-11+20%3A54%3A09; p_ab_id=8; p_ab_id_2=4; p_ab_d_id=310766639; yuid_b=NhhVSVQ; _ga=GA1.2.749579682.1594468589; device_token=6d152f962c69a8f4d68e3264d711f144; c_type=68; a_type=0; b_type=1; ki_r=; login_ever=yes; __utmv=235335808.|2=login%20ever=yes=1^3=plan=normal=1^5=gender=male=1^6=user_id=25577783=1^9=p_ab_id=8=1^10=p_ab_id_2=4=1^11=lang=zh=1; _fbp=fb.1.1594539814721.1368744765; __gads=ID=0a83e9715196f792:T=1594701684:S=ALNI_MZDTHbnSVSQL-jaiPtO2Daz9zqqgg; __cfduid=dfc63655afae895c9b129303e7bbb0c331594912907; __utmz=235335808.1594941663.13.2.utmcsr=saucenao.com|utmccn=(referral)|utmcmd=referral|utmcct=/search.php; adr_id=QPlyLZAoYvFTCUHnm60xJBQVcBs9j59e3XN6C0405f7rQtgb; PHPSESSID=25577783_TJ5VN6a00lF1AFscoQbsHI8p1kIUb1bS; privacy_policy_agreement=2; ki_s=208879%3A0.0.0.0.0; howto_recent_view_history=78912973%2C79089472%2C79441383%2C80691922%2C81278999%2C81959286%2C82061312%2C82235999%2C82296103%2C82580986; categorized_tags=1DJe5jv-kK~1VRevGSbqy~8NfvpmigcD~GX5cZxE2GY~IVwLyT8B6k~MLnEk0g1Gm~NqnXOnazer~O2wfZxfonb~OT-C6ubi9i~RFVdOq-YjA~RcahSSzeRf~X7AFr6h3gr~Xy-YedFebu~b8b4-hqot7~dzOu_hTFu9~gZWEWFTj-b~koAVe5IPJ8~m2-1TiSWsb~m3EJRa33xU~pvU1D1orJa~ukwGkrxn11~ybxe6VnJAU; __utmc=235335808; ki_t=1594468962806%3B1595917005660%3B1595917005660%3B7%3B12; _gid=GA1.2.295584022.1595918129; tag_view_ranking=0xsDLqCEW6~r_Jjn6Ua2V~RTJMXD26Ak~uY38__PA23~aKhT3n4RHZ~Ew4p81FXLl~8y_FO1og5v~uGQeWvelyQ~Gs_7dRY1i7~O2wfZxfonb~0CaTbfGZYk~Lt-oEicbBr~rgxOsa3XtV~HLWLeyYOUF~E8zdna9OwN~IYclAM59kT~uzveofupuL~SqVgDNdq49~IOEPYP3pVd~_vCZ2RLsY2~NKaWczYEa-~RjFcqlQWJ2~2u-0Jtvqqd~qJ0gM6EMFd~CL-YHLOEXx~KhhTM1zuNN~K_10rc6LuN~ckoqr0bPHv~-H5Z3AWM57~04cFU3QC9k~x_jB0UM4fe~GX5cZxE2GY~75zhzbk0bS~X7AFr6h3gr~R7uJJkeuCN~zIv0cf5VVk~aOGQhsapNP~LfZ-Nmmq_l~K8esoIs2eW~b_G3UDfpN0~gpglyfLkWs~ETjPkL0e6r~98FF78f4J0~nYcKY2370U~qIdR-zIXpb~Ce-EdaHA-3~9wN-K8_crj~RFVdOq-YjA~a-yCMcqYxL~AgZY8GdPPj~59dAqNEUGJ~UnhDA4LpTp~rLu37T8oPF~OT4SuGenFI~6o6m4yJCaj~wmxKAirQ_H~-_wohwIfBN~Zk9OUJ2sC6~11UskbJwWv~ZaaASMwi3l~ViAVbnWMws~DXPphy6Z-6~YDV6PRaWD7~Ngz9KxUrJt~nriWjM9urd~cxG7coNmIs~dzOu_hTFu9~9uvt32l2jX~RwV4Pp7z56~ybxe6VnJAU~2zYRbtDepa~-qQoC8nrnQ~JCFnA-6eQh~FQHUiBZOQu~koAVe5IPJ8~Oa9b6mEc1T~nQRrj5c6w_~sJ0rgKtOyc~x3hskY9tUh~fJbkA47XnJ~i6xpz1p4ti~XkKqtav02W~iwSOpeqrRV~Gk2EW6rz4c~ZvFqQuOcZX~1cPITA00VB~dUhrZMpRPB~5rz4DXqggE~YJtQxxgwZx~C0bB4ry6CF~zwhHOEK0yh~BtXd1-LPRH~76YTRtDCzh~p27QC63XHD~_pwIgrV8TB~E5nFezN1vr~GV1cfVtr59~FfTcTP24BT~jFhET3tD4W~14hPDRJgr9; __utma=235335808.749579682.1594468589.1595916998.1595939846.24; __utmt=1; _gat_UA-1830249-3=1; __utmb=235335808.5.10.1595939846";
    public static int connectTimeout = 120;
    public static int readTimeout = 120;

    public static String keyword;
    public static String mode="safe";
    public static String lang="zh";
    public static String type="all";
    public static String s_mode = "s_tag";

    public static int threadPoolSize=6;
    public static int retrySleepTime=5000;
    public static int maxImg=5;

    public static int minViewCount;
    public static int minBookmarkCount;
    public static String how;

    public static String fileUrl="img";
    public static String fileName="${userId}_${baseName}";

    public static void initSetting(int startPage,int endPage,String ketword,int minViewCount,int minBookmarkCount,String how,String fileUrl){
        Setting.startPage=startPage;
        Setting.endPage=endPage;
        Setting.keyword =ketword;
        Setting.minViewCount=minViewCount;
        Setting.minBookmarkCount=minBookmarkCount;
        Setting.how=how;
        Setting.fileUrl=fileUrl;
        Setting.printSetting();
    }

    public static void initSetting(){
        PropertiesReader properties = new PropertiesReader();
        File file = new File("config.properties");
        try {
            FileInputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Setting.pixivUrl=properties.getString("pixivUrl","https://pixiv,net");

        Setting.startPage=properties.getInt("startPage",1);
        Setting.endPage=properties.getInt("endPage",1000);

        Setting.cookie=properties.getString("cookie");
        Setting.connectTimeout=properties.getInt("connectTimeout", 120);
        Setting.readTimeout=properties.getInt("readTimeout", 120);

        Setting.keyword =properties.getString("keyword");
        Setting.mode=properties.getString("mode","safe");
        Setting.lang=properties.getString("lang","zh");
        Setting.type=properties.getString("type","all");
        Setting.s_mode=properties.getString("s_mode","s_type");

        Setting.threadPoolSize=properties.getInt("threadPoolSize", 6);
        Setting.retrySleepTime=properties.getInt("retrySleepTime", 500);
        Setting.maxImg=properties.getInt("maxImg",5);

        Setting.minViewCount=properties.getInt("minViewCount", 2000);
        Setting.minBookmarkCount=properties.getInt("minBookmarkCount", 500);
        Setting.how=properties.getString("how","and");

        Setting.fileUrl=properties.getString("fileUrl","img");
        Setting.fileName=properties.getString("fileName","${userId}_${baseName}");

        Setting.printSetting();
    }

    static private void printSetting(){
        System.out.println("startPage:\t"+Setting.startPage);
        System.out.println("endPage:\t"+Setting.endPage);
        System.out.println("keyword:\t"+Setting.keyword);
        System.out.println("how:\t"+Setting.how);
        System.out.println("minViewCount:\t"+Setting.minViewCount);
        System.out.println("minBookmarkCount:\t"+Setting.minBookmarkCount);
        System.out.println("maxImg:\t"+Setting.maxImg);
    }

    static private class PropertiesReader extends Properties{
        int getInt(String key){
            String val=this.getProperty(key);
            if(val==null||val.isEmpty()){
                throw new NullPointerException(key+"不能为空");
            }else{
                return Integer.parseInt(val);
            }
        }
        int getInt(String key,int defaultValue){
            String val=this.getProperty(key, String.valueOf(defaultValue));
            if(val==null||val.isEmpty()){
                return defaultValue;
            }else {
                return Integer.parseInt(val);
            }
        }

        String getString(String key){
            String val = this.getProperty(key);
            if(val==null||val.isEmpty()){
                throw new NullPointerException(key+"不能为空");
            }else{
                return val;
            }
        }
        String getString(String key,String defaultValue){
            String val=this.getProperty(key, String.valueOf(defaultValue));
            if(val==null||val.isEmpty()){
                return defaultValue;
            }else {
                return val;
            }
        }
    }
}
