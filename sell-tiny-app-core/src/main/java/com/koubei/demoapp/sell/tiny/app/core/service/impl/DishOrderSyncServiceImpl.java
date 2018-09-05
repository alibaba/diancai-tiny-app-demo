package com.koubei.demoapp.sell.tiny.app.core.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.koubei.demoapp.sell.tiny.app.core.entity.AppInfo;
import com.koubei.demoapp.sell.tiny.app.core.entity.Order;
import com.koubei.demoapp.sell.tiny.app.core.entity.OrderDetail;
import com.koubei.demoapp.sell.tiny.app.core.enums.AlipayOrderStatusEnum;
import com.koubei.demoapp.sell.tiny.app.core.service.BaseServiceImpl;
import com.koubei.demoapp.sell.tiny.app.core.service.DishOrderSyncService;
import com.koubei.demoapp.sell.tiny.app.core.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 同步订单状态
 * Created by jackey on 17/4/5.
 */
@Service
public class DishOrderSyncServiceImpl extends BaseServiceImpl implements DishOrderSyncService {

    private static final Logger logger = LoggerFactory.getLogger(DishOrderSyncServiceImpl.class);

    @Autowired
    private ShopService shopService;

    @Override
    public boolean syncStatus(Order order, OrderDetail orderDetail, AppInfo appInfo, AlipayOrderStatusEnum statusEnum, JSONArray actionUrlList) {
//
//        AlipayOfflineProviderUseractionRecordRequest recordRequest = new AlipayOfflineProviderUseractionRecordRequest();
//        // 生成当前时间戳
//        Long timestamp = System.currentTimeMillis();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        String time_stamp = df.format(new Date(timestamp));
//        // 获取当前点菜用户Id
//        String userId = orderDetail.getUserId();
//        // 获取当前订单所在门店
//        JSONObject snapshot = JSONObject.parseObject(order.getSnapshot());
//        String shopId = String.valueOf(snapshot.get("shopId"));
//        Shop shopDTO = shopService.loadById(Long.valueOf(shopId));
//        String outShopId = shopDTO.getOutShopId();// 口碑的门店id
//        // 获取当前订单菜品信息
//        List<OrderItemDTO> orderItems = JSONObject.parseArray(String.valueOf(snapshot.get("orderItems")), OrderItemDTO.class);
//        String outerId = order.getOrderNO();
//        String actionOuterId = UUIDGenerator.getUUID();
//
//        JSONObject object = new JSONObject();
//        object.put("action_type", "order_dishes");//上传用户菜单
//        object.put("entity", "user");//用户行为数据
//        object.put("user_id", userId);//支付宝用户ID 2088的
//        object.put("order_channel", "alipay");//值定义：alipay、weixin、other、isv  值意义说明：alipay：支付宝  weixin：微信  isv：isv自己的系统未通过第三方点菜， other：其他  标示当前订单的创建来源
//        object.put("source", "alipay");
//        object.put("date_time", time_stamp);
//        object.put("industry", "REPAST");//写死
//        object.put("action_outer_id", outerId);
//        //object.put("is_test_user", "1");//一定是调试使用，设置用前面user_id的iD可以看到数据， 可以设置后可以直接在口碑tab，右上角我的订单出现
//        // 构造门店数据信息
//        JSONObject shopDoJson = createShopJson(outShopId);
//        object.put("outer_shop_do", shopDoJson);
//
//        JSONObject detail = new JSONObject();
//        detail.put("totalPrice", orderDetail.getTotalPrice());
//        detail.put("discountPrice", orderDetail.getDiscountPrice());//  优惠金额（单位分，如果定义类型是long，金额单位都是分）
//        detail.put("realPrice", orderDetail.getRealPrice());
//        detail.put("status", statusEnum.getOrderStatusCode());
//        detail.put("hasClosed", orderDetail.getHasClosed());// hasClosed 是
//        detail.put("statusDesc", statusEnum.getOrderStatusMsg());
//        detail.put("payTime", new Date().getTime());
//        detail.put("channel", "alipay");
//        detail.put("outOrderId", outerId);
//        detail.put("description", "描述");
//        String detailUrl = "http://" + appInfo.getIp() + ":8080" + ShopPageEntryUrl.orderDetailUrl + "?shopId=" + outShopId + "&orderNo=" + outerId;
//        detail.put("detailUrl", detailUrl);//订单详情url
//        JSONArray orderArray = new JSONArray();
//        for (OrderItemDTO orderItemd : orderItems) {
//            JSONObject obj = new JSONObject();
//            obj.put("goodsId", "goodsId" + orderItemd.getId());//菜品ID 必填
//            obj.put("goodsName", orderItemd.getName());//菜品名称 必填
//            obj.put("price", String.valueOf(orderItemd.getPrice().longValue()));//菜品价格 必填
//            obj.put("num", 22);//菜品分数 必填
//            Random r1 = new Random(10);
//            long sku = (long) (Math.random() * 1E10);
//            obj.put("skuId", String.valueOf(sku));//菜品skuIdId 非必填
//            orderArray.add(obj);
//        }
//        detail.put("dish", orderArray);//点菜的个数
//
//        //拼接actionUrlList  页面上跳转
//        detail.put("actionUrlList", actionUrlList);// 服务卡交互链接
//
//        //拼接realtionUserList 订单关联的用户
//        JSONArray realtionUserList = new JSONArray();
//        //支付
//        JSONObject realtion = new JSONObject();
//        realtion.put("userId", userId);//支付宝用户ID
//        realtion.put("type", "pay");// 支付者 type 说明：   pay:支付者pushOrder：推送订单到厨房的用户normal：普通用户，没有做特殊说明的都是普通用户
//        realtionUserList.add(realtion);
//        detail.put("realtionUserList", realtionUserList);
//
////        String actionDetail = "{\"outer_dish_id\":\"20\",\"name\":\"红烧猪蹄\",\"price\":4500,\"quantity\":2}";
//        object.put("action_detail", detail);
//
//        recordRequest.setBizContent(object.toJSONString());
//        try {
//            AlipayOfflineProviderUseractionRecordResponse response = defaultAlipayClient.execute(recordRequest);
//            logger.info("sync status to AlipayOfflineProviderUseractionRecord , recordRequest : " + JSONObject.toJSONString(recordRequest));
//            logger.info("sync status to AlipayOfflineProviderUseractionRecord , response :" + JSONObject.toJSONString(response));
//            if (response != null && response.isSuccess()) {
//                return true;
//            }
//        } catch (AlipayApiException e) {
//            logger.error("sync status to AlipayOfflineProviderUseractionRecord error. response : " + JSONObject.toJSONString(recordRequest), e);
//        }
        return false;
    }

    /**
     * @param object
     * @return
     */
    private static JSONObject createShopJson(String shopId) {
        JSONObject shopDoJson = new JSONObject();
        shopDoJson.put("shop_id", shopId);//口碑店铺ID
        shopDoJson.put("outer_id", shopId);//外部店铺ID
        shopDoJson.put("type", "koubei.com");//你的平台域名（请保证在支付宝开放平台申请的appID都在一个商户ID下面）2dfire.com
        //内部会保存一条 shop_id  outer_id type isvPid 一条记录
        return shopDoJson;
    }

    public ShopService getShopService() {
        return shopService;
    }

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

}