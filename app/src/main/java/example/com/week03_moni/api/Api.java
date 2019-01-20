package example.com.week03_moni.api;

public class Api {
    public static final String BASE_URL = "http://172.17.8.100";
    //首页商品信息列表
    public static String SHOPLIST = BASE_URL + "/small/commodity/v1/commodityList";
    //登录接口
    public static String LOGIN = BASE_URL + "/small/user/v1/login";
    //注册接口
    public static String REGIN = BASE_URL + "/small/user/v1/register";
    //圈子
    public static String QUNA=BASE_URL+"/small/circle/v1/findCircleList?page=1&count=10";
    //详情
    public static String XIANG="http://172.17.8.100/small/commodity/v1/findCommodityDetailsById";
                                // http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=6
    //购物车
    public static String CAR="http://www.zhaoapi.cn/product/getCarts";
}
