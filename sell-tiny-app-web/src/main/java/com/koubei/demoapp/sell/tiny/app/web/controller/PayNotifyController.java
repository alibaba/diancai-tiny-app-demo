package com.koubei.demoapp.sell.tiny.app.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.koubei.demoapp.sell.tiny.app.core.constant.SystemConstant;
import com.koubei.demoapp.sell.tiny.app.core.entity.AppInfo;
import com.koubei.demoapp.sell.tiny.app.core.entity.Order;
import com.koubei.demoapp.sell.tiny.app.core.entity.OrderDetail;
import com.koubei.demoapp.sell.tiny.app.core.entity.Shop;
import com.koubei.demoapp.sell.tiny.app.core.enums.AlipayOrderStatusEnum;
import com.koubei.demoapp.sell.tiny.app.core.enums.OrderStatusEnum;
import com.koubei.demoapp.sell.tiny.app.core.service.DishOrderSyncService;
import com.koubei.demoapp.sell.tiny.app.core.service.OrderService;
import com.koubei.demoapp.sell.tiny.app.core.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jackey on 17/3/29.
 */
@RestController
@RequestMapping("/pay")
public class PayNotifyController {

    private static final Logger logger = LoggerFactory.getLogger(PayNotifyController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private DishOrderSyncService dishOrderSyncService;

    @Autowired
    private ShopService shopService;

    @RequestMapping("/notify.htm")
    public void callBack(@RequestParam(value = "trade_no",required = false) String tradeNo,
                         @RequestParam(value = "out_trade_no",required = false) String outTradeNo,
                         @RequestParam(value = "trade_status",required = false) String tradeStatus,
                         @RequestParam(value = "notify_id",required = false) String notifyId) {
        String callBackValue = String.format("tradeNo : %s, outTradeNo : %s , tradeStatus : %s ,notifyId : %s ", tradeNo, outTradeNo, tradeStatus, notifyId);
        if (StringUtils.isEmpty(tradeNo) || StringUtils.isEmpty(outTradeNo) || StringUtils.isEmpty(tradeStatus) || StringUtils.isEmpty(notifyId)) {
            logger.error("PayNotifyController call back info is null");
            return;
        }

        logger.info(callBackValue);
//        String executeResult = OpenApiParam.payNotifyInfoMap.get(notifyId);
//        if (!StringUtils.isEmpty(executeResult)) {
//            logger.error("PayNotifyController duplicate call back, detail = " + callBackValue);
//            return;
//        }
        //TODO 验证out_trade_no是否本应用的订单号

        int status = 0;
        if (tradeStatus.equalsIgnoreCase("TRADE_SUCCESS")) {
            status = OrderStatusEnum.PAYED.getCode();
        } else if (tradeStatus.equalsIgnoreCase("WAIT_BUYER_PAY")) {
            status = OrderStatusEnum.UNPAY.getCode();
        } else if (tradeStatus.equalsIgnoreCase("TRADE_CLOSED")) {
            status = OrderStatusEnum.CLOSED.getCode();
        }

        Order order = orderService.loadByOrderNO(outTradeNo);
        order.setOrderStatus(status);
        order.setAlipayTradeNO(tradeNo);
        orderService.updateOrderByOrderNO(order);
//        OpenApiParam.payNotifyInfoMap.put(notifyId, "done");

        // 回流支付完成状态 ,
        String userId = order.getUserId();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDiscountPrice(1L);
        orderDetail.setHasClosed("0");// 未清桌
        orderDetail.setOrderNO(order.getOrderNO());
        orderDetail.setRealPrice(2L);
        orderDetail.setTotalPrice(3L);
        orderDetail.setUserId(userId);
        AppInfo appInfo = new AppInfo();
        appInfo.setAppId(SystemConstant.getAppId());
        appInfo.setIp(System.getenv().get("notify_url"));
        JSONArray actionUrlList = new JSONArray();
        JSONObject action3 = new JSONObject();
        JSONObject snapshot = JSONObject.parseObject(order.getSnapshot());
        String shopId = String.valueOf(snapshot.get("shopId"));
        action3.put("name", "详情");//描述
        Shop shopDTO = shopService.loadById(Long.valueOf(shopId));
        String outShopId = shopDTO.getOutShopId();// 口碑的门店id
        String outerId = order.getOrderNO();
        String actionUrl = "http://" + appInfo.getIp() + ":8080/main.html?shopId=" + outShopId + "#/payresult/" + outerId;
        action3.put("actionUrl", actionUrl);
        action3.put("type", "");//（官方常量  addDish（加菜） pay （去买单），官方会根据类型做业务逻辑，如果不在官方说明，可以自己定义或者不传入，官网会更具类型做业务）
        actionUrlList.add(action3);
        dishOrderSyncService.syncStatus(order, orderDetail, appInfo, AlipayOrderStatusEnum.payed, actionUrlList);
    }

}