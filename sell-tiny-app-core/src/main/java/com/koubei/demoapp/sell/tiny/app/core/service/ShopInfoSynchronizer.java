/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.request.AlipayOfflineMarketShopQuerydetailRequest;
import com.alipay.api.response.AlipayOfflineMarketShopQuerydetailResponse;
import com.koubei.demoapp.sell.tiny.app.core.constant.SystemConstant;
import com.koubei.demoapp.sell.tiny.app.core.entity.Shop;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jianheng.hjh
 * @version $Id: ShopInfoSynchronizer.java, v 0.1 2017-04-07 下午5:59 jianheng.hjh Exp $$
 */
@Component
public class ShopInfoSynchronizer {

    private static final Logger logger = LoggerFactory.getLogger(ShopInfoSynchronizer.class);

    private static final int limit = 50;

    @Autowired
    private ShopService shopService;

//    @Scheduled(cron = "0 * * * * ?")
    public void syncShopInfo() {
        int offset = 0;

        List<Shop> shops;
        while ((shops = shopService.find(offset, limit)).size() > 0) {
            for (Shop shop : shops) {
                AlipayOfflineMarketShopQuerydetailRequest request = new AlipayOfflineMarketShopQuerydetailRequest();

                JSONObject bizContent = new JSONObject();
                bizContent.put("shop_id", shop.getOutShopId());
                bizContent.put("op_role", "PROVIDER");

                request.setBizContent(bizContent.toJSONString());
                try {
                    AlipayOfflineMarketShopQuerydetailResponse response = SystemConstant.getAlipayClient().execute(request);
                    if (response != null && response.isSuccess()) {
                        String mainShopName = response.getMainShopName();
                        String branchShopName = response.getBranchShopName();
                        shop.setName(mainShopName + (StringUtils.isNotBlank(branchShopName) ? String.format("（%s）", branchShopName) : ""));
                        shop.setBulletin(response.getValueAdded());
                        shop.setAddress(response.getAddress());
                        shop.setAvatar(response.getBrandLogo());
                        shopService.updateByShopId(shop);
                    }
                } catch (Exception e) {
                    logger.error(String.format("sync shop info failed, shopId = %s, outShopId = %s", shop.getId(), shop.getOutShopId()), e);
                }
            }
            offset += limit;
        }
    }

}