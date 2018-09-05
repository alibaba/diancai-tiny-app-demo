package com.koubei.demoapp.sell.tiny.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.koubei.cavalry.architect.csys.entry.AlipayUser;
import com.koubei.cavalry.architect.csys.service.AlipayUserService;
import com.koubei.demoapp.sell.tiny.app.core.constant.SystemConstant;
import com.koubei.demoapp.sell.tiny.app.core.entity.Shop;
import com.koubei.demoapp.sell.tiny.app.core.service.ShopService;
import com.koubei.demoapp.sell.tiny.app.web.Response;
import com.koubei.demoapp.sell.tiny.app.web.vo.UserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by jackey on 17/3/29.
 */
@Controller
@RequestMapping("/alipay")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AlipayUserService userService;
    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/auth")
    @ResponseBody
    public Response getBaseInfo(@RequestParam("shop_id") String shopId,
                                @RequestParam("auth_code") String authCode,
                                HttpServletRequest httpServletRequest) {
        logger.info(String.format("requested /alipay/auth, shopId: %s, authCode: %s", shopId, authCode));

        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(authCode);
        request.setGrantType("authorization_code");
        AlipaySystemOauthTokenResponse response;

        try {
            AlipayClient alipayClient = SystemConstant.getAlipayClient();

            HttpSession session = httpServletRequest.getSession();
            response = alipayClient.execute(request);

            if (response == null || !response.isSuccess())
                return Response.fail("获取用户信息异常");

            String accessToken = response.getAccessToken();
            String userId = response.getUserId();
//                long deskId = (long) httpServletRequest.getSession().getAttribute("deskId");
            long deskId = 2;

            session.setAttribute("userId", userId);
            AlipayUser user = userService.loadByUserId(userId);
            if (user != null) {
                // 未过期
                if (user.getGmtTokenExpire().before(new Date())) {
                    AlipayUserInfoShareResponse shareResponse = getUserShareInfo(accessToken, alipayClient);
                    user.setName(shareResponse.getNickName());
                    user.setAvatar(shareResponse.getAvatar());
                    user.setAccessToken(accessToken);
                    Long expiresIn = Long.valueOf(response.getExpiresIn());
                    user.setGmtTokenExpire(new Timestamp(System.currentTimeMillis() + expiresIn * 1000));
                    userService.updateByUserId(user);
                }
            } else {
                AlipayUserInfoShareResponse shareResponse = getUserShareInfo(accessToken, alipayClient);
                if (shareResponse != null && shareResponse.isSuccess()) {
                    user = new AlipayUser();
                    user.setUserId(userId);
                    user.setName(shareResponse.getNickName());
                    user.setAvatar(shareResponse.getAvatar());
                    user.setAccessToken(accessToken);
                    user.setGmtCreate(new Timestamp(System.currentTimeMillis()));
                    Long expiresIn = Long.valueOf(response.getExpiresIn());
                    user.setGmtTokenExpire(new Timestamp(System.currentTimeMillis() + expiresIn * 1000));
                    userService.insert(user);
                } else {
                    return Response.fail("获取用户信息异常");
                }
            }

            UserInfoVo userInfoVo = new UserInfoVo();
            userInfoVo.setNickName(user.getName());
            userInfoVo.setAvatar(user.getAvatar());

            JSONObject resp = new JSONObject();
            resp.put("userInfo", userInfoVo);
            Shop shop = shopService.loadByOutShopId(shopId);
            session.setAttribute("shopId", shop.getId());
            resp.put("shopInfo", shop);

            return Response.success(resp);
        } catch (AlipayApiException ext) {
            logger.error("request user info by auth error!!!", ext);
            return Response.fail("接口调用异常");
        }
    }

    private AlipayUserInfoShareResponse getUserShareInfo(String accessToken, AlipayClient client) throws AlipayApiException {
        AlipayUserInfoShareRequest shareRequest = new AlipayUserInfoShareRequest();
        return client.execute(shareRequest, accessToken);
    }

}