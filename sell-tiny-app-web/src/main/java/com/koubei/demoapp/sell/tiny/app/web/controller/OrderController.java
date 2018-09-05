/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.koubei.demoapp.sell.tiny.app.core.constant.SystemConstant;
import com.koubei.demoapp.sell.tiny.app.core.dto.ItemDTO;
import com.koubei.demoapp.sell.tiny.app.core.dto.OrderItemDTO;
import com.koubei.demoapp.sell.tiny.app.core.dto.OrderSnapShotDTO;
import com.koubei.demoapp.sell.tiny.app.core.entity.Order;
import com.koubei.demoapp.sell.tiny.app.core.entity.Shop;
import com.koubei.demoapp.sell.tiny.app.core.enums.OrderStatusEnum;
import com.koubei.demoapp.sell.tiny.app.core.service.DishOrderSyncService;
import com.koubei.demoapp.sell.tiny.app.core.service.ItemService;
import com.koubei.demoapp.sell.tiny.app.core.service.OrderService;
import com.koubei.demoapp.sell.tiny.app.core.service.ShopService;
import com.koubei.demoapp.sell.tiny.app.web.Response;
import com.koubei.demoapp.sell.tiny.app.web.constant.OpenApiParam;
import com.koubei.demoapp.sell.tiny.app.web.vo.OrderInfoVO;
import com.koubei.demoapp.sell.tiny.app.web.vo.OrderListVO;
import com.koubei.demoapp.sell.tiny.app.web.vo.OrderPageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jianheng.hjh
 * @version $Id: ItemController.java, v 0.1 2017-03-28 涓嬪崍5:29 jianheng.hjh Exp $$
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopService shopService;

    @Autowired
    private DishOrderSyncService dishOrderSyncService;

    @Value("${alipay.signType}")
    private String signType;
    @Value("${alipay.version}")
    private String version;


    @RequestMapping("/pageData.json")
    public Response getOrderData(@RequestParam String items) {
        return new Response(genOrderDetail(items), 200, null);
    }

    @RequestMapping("/info.json")
    public Response getOrderInfo(@RequestParam String orderNo) {
        Order orderDTO = orderService.loadByOrderNO(orderNo);

        OrderPageVO orderPageVO = new OrderPageVO();
        orderPageVO.setOrderNo(orderNo);

        OrderSnapShotDTO snapshot = JSON.parseObject(orderDTO.getSnapshot(), OrderSnapShotDTO.class);
        orderPageVO.setShopName(snapshot.getShopName());
        Shop shopDTO = shopService.loadById(snapshot.getShopId());
        orderPageVO.setShopAvatar(shopDTO.getAvatar());
        orderPageVO.setOrderItems(snapshot.getOrderItems());
        orderPageVO.setPrice(snapshot.getPrice());
        orderPageVO.setOrderStatus(orderDTO.getOrderStatus());
        orderPageVO.setOrderStatusDesc("订单" + OrderStatusEnum.fromCode(orderDTO.getOrderStatus()).getDesc());
        orderPageVO.setAlipayTradeNo(orderDTO.getAlipayTradeNO());
        return Response.success(orderPageVO);
    }

    @RequestMapping("/items.json")
    public Response getItemsInOrder(@RequestParam String orderNo) {
        Order order = orderService.loadByOrderNO(orderNo);
        if (order == null)
            return Response.success(Collections.emptyList());

        List<OrderItemDTO> orderItems = JSON.parseObject(order.getSnapshot(), OrderSnapShotDTO.class).getOrderItems();
        return Response.success(orderItems);
    }

    @RequestMapping("/createOrderAndPay.json")
    public Response createOrderAndPay(@RequestParam String items, HttpServletRequest request) {
        OrderPageVO orderDetail = genOrderDetail(items);
        Order order = orderService.createOrder((String) request.getSession().getAttribute("userId"), JSON.toJSONString(orderDetail));
        String orderStr = getOrderStr(order.getOrderNO(), "云点餐", orderDetail.getPrice().toString());
        OrderInfoVO orderInfoVO = new OrderInfoVO();
        orderInfoVO.setOrderStr(orderStr);
        orderInfoVO.setOrderNo(order.getOrderNO());
        //回流订单数据 订单已下单到厨房, 待支付
//        syncOrderByAlipayOpenApi(request, order);
        return Response.success(orderInfoVO);
    }

    @RequestMapping("/createOrder.json")
    public Response createOrder(@RequestParam String items, HttpServletRequest request) {
        OrderPageVO orderDetail = genOrderDetail(items);
        Order order = orderService.createOrder((String) request.getSession().getAttribute("userId"), JSON.toJSONString(orderDetail));
        //回流订单数据 订单已下单到厨房, 待支付
//        syncOrderByAlipayOpenApi(request, order);
        return Response.success(order.getOrderNO());
    }

    @RequestMapping("/addDishToOrder.json")
    public Response addDishToOrder(@RequestParam String orderNo, @RequestParam String items, HttpServletRequest request) {
        Order order = orderService.loadByOrderNO(orderNo);
        OrderSnapShotDTO snapshot = JSON.parseObject(order.getSnapshot(), OrderSnapShotDTO.class);

        Map<Object, Object> map = JSON.parseObject(items, Map.class);

        OrderPageVO orderPageVO = new OrderPageVO();

        List<OrderItemDTO> orderItems = snapshot.getOrderItems();
        Map<Long, OrderItemDTO> orderItemMap = new HashMap<>();
        for (OrderItemDTO orderItem : orderItems) {
            orderItemMap.put(orderItem.getId(), orderItem);
        }

        BigDecimal price = snapshot.getPrice();
        for (Map.Entry entry : map.entrySet()) {
            Long id = Long.parseLong(entry.getKey().toString());
            ItemDTO itemDTO = itemService.loadById(id);
            if (itemDTO != null) {
                orderPageVO.setShopId(itemDTO.getShopId());

                Integer qty = Integer.valueOf(entry.getValue().toString());
                price = price.add(itemDTO.getPrice().multiply(new BigDecimal(qty)));

                if (!orderItemMap.containsKey(id)) {
                    OrderItemDTO orderItem = new OrderItemDTO();
                    orderItem.setId(id);
                    orderItem.setName(itemDTO.getName());
                    orderItem.setPrice(itemDTO.getPrice());
                    orderItem.setQuantity(qty);
                    orderItemMap.put(id, orderItem);
                } else {
                    OrderItemDTO orderItemDTO = orderItemMap.get(id);
                    orderItemDTO.setQuantity(orderItemDTO.getQuantity() + 1);
                }

            }
        }

        snapshot.setOrderItems(new ArrayList<>(orderItemMap.values()));
        snapshot.setPrice(price);
        order.setSnapshot(JSON.toJSONString(snapshot));
        orderService.updateOrderByOrderNO(order);
//        syncOrderByAlipayOpenApi(request, order);
        return Response.success();
    }

    @RequestMapping("/payOrder.json")
    public Response payOrder(@RequestParam String orderNo) {
        Order order = orderService.loadByOrderNO(orderNo);
        OrderSnapShotDTO snapshot = JSON.parseObject(order.getSnapshot(), OrderSnapShotDTO.class);
        String orderStr = getOrderStr(orderNo, "云点餐", snapshot.getPrice().toString());

        logger.info(String.format("orderNo: %s, orderStr: %s", orderNo, orderStr));

        return Response.success(orderStr);
    }

//    private void syncOrderByAlipayOpenApi(HttpServletRequest request, Order order) {
//        String userId = String.valueOf(request.getSession().getAttribute("userId"));
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setDiscountPrice(1L);
//        orderDetail.setHasClosed("0");// 未清桌
//        orderDetail.setOrderNO(order.getOrderNO());
//        orderDetail.setRealPrice(2L);
//        orderDetail.setTotalPrice(3L);
//        orderDetail.setUserId(userId);
//        AppInfo appInfo = new AppInfo();
//        appInfo.setAppId(appId);
//        appInfo.setIp(OpenApiParam.IP);
//
//        // 获取订单门店
//        JSONObject snapshot = JSONObject.parseObject(order.getSnapshot());
//        String shopId = String.valueOf(snapshot.get("shopId"));
//        Shop shopDTO = shopService.loadById(Long.valueOf(shopId));
//        String outShopId = shopDTO.getOutShopId();// 口碑的门店id
//        String outerId = order.getOrderNO();
//
//        JSONArray actionUrlList = new JSONArray();
//
//        // 去买单链接
//        JSONObject action3 = new JSONObject();
//        action3.put("name", "去买单");//描述
//        String actionUrl3 = String.format("http://%s:8080%s?shopId=%s&orderNo=%s", appInfo.getIp(), ShopPageEntryUrl.orderDetailUrl, outShopId, outerId);
//        action3.put("actionUrl", actionUrl3);
//        action3.put("type", "pay");//（官方常量  addDish（加菜） pay （去买单），官方会根据类型做业务逻辑，如果不在官方说明，可以自己定义或者不传入，官网会更具类型做业务）
//        actionUrlList.add(action3);
//
//        // 加菜链接
//        JSONObject action2 = new JSONObject();
//        action2.put("name", "加菜");//描述
////        String actionUrl2 = String.format("http://%s:8080/main.html?shopId=%s#/goods/%s", appInfo.getIp(), outShopId, outerId);
//        String actionUrl2 = String.format("http://%s:8080%s?shopId=%s&orderNo=%s", appInfo.getIp(), ShopPageEntryUrl.addDishUrl, outShopId, outerId);
//        action2.put("actionUrl", actionUrl2);//菜品名称 必填
//        action2.put("type", "addDish");
//        actionUrlList.add(action2);
//
//        dishOrderSyncService.syncStatus(order, orderDetail, appInfo, AlipayOrderStatusEnum.waitSellerAccept, actionUrlList);
//    }

    /**
     * @param
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


    @RequestMapping("/pay")
    public Response pay(@RequestParam String orderNo) {
        Order order = orderService.loadByOrderNO(orderNo);
        OrderSnapShotDTO snapshot = JSON.parseObject(order.getSnapshot(), OrderSnapShotDTO.class);
        String orderStr = getOrderStr(orderNo, "云点餐", snapshot.getPrice().toString());
        OrderInfoVO orderInfoVO = new OrderInfoVO();
        orderInfoVO.setOrderStr(orderStr);
        orderInfoVO.setOrderNo(order.getOrderNO());
        return Response.success(orderInfoVO);
    }

    @RequestMapping("/my.json")
    public Response getMyOrders(HttpServletRequest request) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//        List<Order> orderDTOs = orderService.getOrdersByUserId((String) request.getSession().getAttribute("userId"));
        List<Order> orderDTOs = orderService.getOrdersByUserId("2088802267853599");
        List<OrderListVO> orderList = new ArrayList<>();
        for (Order orderDto : orderDTOs) {
            String snapShot = orderDto.getSnapshot();
            OrderSnapShotDTO snapOb = JSONObject.parseObject(snapShot, OrderSnapShotDTO.class);
            OrderListVO listVo = new OrderListVO();
//            listVo.setDateTime(df.format(orderDto.getGmtCreate()));
            listVo.setOrderNo(orderDto.getOrderNO());
            listVo.setShopName(snapOb.getShopName());
            listVo.setPrice(snapOb.getPrice().toString());
            listVo.setOrderStatus(OrderStatusEnum.fromCode(orderDto.getOrderStatus()).getDesc());
            listVo.setItems(Lists.transform(snapOb.getOrderItems(), new Function<OrderItemDTO, String>() {
                @Override
                public String apply(OrderItemDTO s) {
                    return s.getName();
                }
            }));
            listVo.setDateTime(df.format(orderDto.getGmtCreate()));
            listVo.setShopAvatar(shopService.loadById(snapOb.getShopId()).getAvatar());
            orderList.add(listVo);
        }
        return Response.success(orderList);
    }

    @RequestMapping("/cancel.json")
    public Response cancelOrder(@RequestParam String orderNo) {
        orderService.cancelOrder(orderNo);
        return Response.success(null);
    }

    @RequestMapping("/attachDeskIdToOrder")
    public Response attachDeskIdToOrder(@RequestParam String orderNo, @RequestParam int deskId) {
        return null;
    }

    private OrderPageVO genOrderDetail(String query) {
        Map<Object, Object> map = JSON.parseObject(query, Map.class);

        OrderPageVO orderPageVO = new OrderPageVO();

        List<OrderItemDTO> orderItems = new ArrayList<>();
        BigDecimal price = new BigDecimal(0);
        for (Map.Entry entry : map.entrySet()) {
            Long id = Long.parseLong(entry.getKey().toString());
            ItemDTO itemDTO = itemService.loadById(id);
            if (itemDTO != null) {
                orderPageVO.setShopId(itemDTO.getShopId());
                OrderItemDTO orderItem = new OrderItemDTO();
                orderItem.setId(id);
                orderItem.setName(itemDTO.getName());
                orderItem.setPrice(itemDTO.getPrice());
                Integer qty = Integer.valueOf(entry.getValue().toString());
                orderItem.setQuantity(qty);
                price = price.add(itemDTO.getPrice().multiply(new BigDecimal(qty)));
                orderItems.add(orderItem);
            }
        }
        Shop shop = shopService.loadById(orderPageVO.getShopId());
        orderPageVO.setShopName(shop.getName());
        orderPageVO.setOrderItems(orderItems);
        orderPageVO.setPrice(price);
        orderPageVO.setShopAvatar(shop.getAvatar());

        return orderPageVO;
    }

    public String getOrderStr(String orderNO, String subject, String totalAmount) {
//        if (StringUtils.isEmpty(subject) || StringUtils.isEmpty(totalAmount)) {
//            return new Response(null, 404, "illegal param");
//        }
        String bizContent = String.format("{\"timeout_express\":\"30m\",\"seller_id\":\"%s\","
                + "\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"%s\","
                + "\"subject\":\"%s\",\"BODY\":\"%s\",\"out_trade_no\":\"%s\"}", OpenApiParam.SELLER_ID, totalAmount, subject, OpenApiParam.BODY, orderNO);
        String configInfo = "&charset=utf-8&format=json&method=alipay.trade.app.pay&";

        Long timestamp = System.currentTimeMillis();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time_stamp = df.format(new Date(timestamp));

        String signContent = buildSignContent(bizContent, configInfo, time_stamp);
        try {
            String sign = AlipaySignature.rsaSign(signContent, SystemConstant.getAppPrivateKey(), "utf-8", "RSA2");
            return buildeEncodeContent(bizContent, configInfo, sign, time_stamp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String buildSignContent(String bizContent, String configInfo, String time_stamp) {
        logger.info("buildSignContent using url " + OpenApiParam.NOTIFY_URL);
        StringBuilder signContent = new StringBuilder();
        signContent.append("app_id").append("=").append(SystemConstant.getAppId()).append("&")
                .append("biz_content").append("=").append(bizContent).append(configInfo)
                .append("notify_url").append("=").append(OpenApiParam.NOTIFY_URL).append("&")
                .append("sign_type").append("=").append(signType).append("&")
                .append("timestamp").append("=").append(time_stamp).append("&")
                .append("version").append("=").append(version);
        return String.valueOf(signContent);
    }

    private String buildeEncodeContent(String bizContent, String configInfo, String sign, String time_stamp) throws UnsupportedEncodingException {
        StringBuilder encodeContent = new StringBuilder();
        String encodeBizContent = URLEncoder.encode(bizContent, "UTF-8");
        String encodeNotifyUrl = URLEncoder.encode(OpenApiParam.NOTIFY_URL, "UTF-8");
        String encodeTimeStamp = URLEncoder.encode(time_stamp, "UTF-8");
        String encodeSign = URLEncoder.encode(sign, "UTF-8");
        encodeContent.append("app_id").append("=").append(SystemConstant.getAppId()).append("&")
                .append("biz_content").append("=").append(encodeBizContent).append(configInfo)
                .append("notify_url").append("=").append(encodeNotifyUrl).append("&")
                .append("sign_type").append("=").append(signType).append("&")
                .append("timestamp").append("=").append(encodeTimeStamp).append("&")
                .append("version").append("=").append(version).append("&")
                .append("sign").append("=").append(encodeSign);
        return String.valueOf(encodeContent);
    }

    public DishOrderSyncService getDishOrderSyncService() {
        return dishOrderSyncService;
    }

    public void setDishOrderSyncService(DishOrderSyncService dishOrderSyncService) {
        this.dishOrderSyncService = dishOrderSyncService;
    }
//
//    public static void main(String[] args) {
//
//    }
}