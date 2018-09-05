package com.koubei.demoapp.sell.tiny.app.core.service;

import com.alibaba.fastjson.JSONArray;
import com.koubei.demoapp.sell.tiny.app.core.entity.AppInfo;
import com.koubei.demoapp.sell.tiny.app.core.entity.Order;
import com.koubei.demoapp.sell.tiny.app.core.entity.OrderDetail;
import com.koubei.demoapp.sell.tiny.app.core.enums.AlipayOrderStatusEnum;

/**
 * Created by jackey on 17/4/5.
 */
public interface DishOrderSyncService {

    boolean syncStatus(Order order, OrderDetail orderDetail, AppInfo appInfo, AlipayOrderStatusEnum statusEnum, JSONArray actionUrlList);

}
