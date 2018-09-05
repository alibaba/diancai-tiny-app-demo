/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.koubei.demoapp.sell.tiny.app.core.service.impl;

import com.koubei.demoapp.sell.tiny.app.core.entity.Category;
import com.koubei.demoapp.sell.tiny.app.core.entity.CategoryMapper;
import com.koubei.demoapp.sell.tiny.app.core.entity.Item;
import com.koubei.demoapp.sell.tiny.app.core.entity.ItemMapper;
import com.koubei.demoapp.sell.tiny.app.core.service.BaseServiceImpl;
import com.koubei.demoapp.sell.tiny.app.core.service.ItemService;
import com.koubei.demoapp.sell.tiny.app.core.dto.CategoryDTO;
import com.koubei.demoapp.sell.tiny.app.core.dto.ItemDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jianheng.hjh
 * @version $Id: ItemServiceImpl.java, v 0.1 2017-03-28 下午5:45 jianheng.hjh Exp $$
 */
@Service
public class ItemServiceImpl extends BaseServiceImpl implements ItemService {

    @Override
    public List<CategoryDTO> getItemsByShopId(Long shopId) {
        List<CategoryDTO> result = new ArrayList<>();

        // 获取菜单类目
        List<Category> categories = jdbcTemplate.query("select * from da_category where shop_id = ?", new CategoryMapper(), shopId);

        // 获取类目下菜品
        for (Category category : categories) {
            CategoryDTO categoryDto = new CategoryDTO();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            List<Map<String, Object>> categoryItems = jdbcTemplate.queryForList("select * from da_category_item where category_id = ?", category.getId());
            List<Item> items = new ArrayList<>();
            for (Map<String, Object> itemMap : categoryItems) {
                Long itemId = Long.valueOf(String.valueOf(itemMap.get("item_id")));
                Item item = jdbcTemplate.queryForObject("select * from da_item where id = ?", new ItemMapper(), itemId);
                if (item != null)
                    items.add(item);
            }
            categoryDto.setItems(items);
            result.add(categoryDto);
        }
        return result;
    }

    @Override
    public ItemDTO loadById(long itemId) {
        return queryItem(itemId);
    }

    @Override
    public List<Item> findByIds(List<Long> itemIds) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("itemIds", itemIds);
        return namedParameterJdbcTemplate.query("select * from da_item where id in (:itemIds)", source, new ItemMapper());
    }

    private ItemDTO queryItem(long itemId) {
        Map itemDtoMap = jdbcTemplate.queryForMap("select * from da_item where id = ?", new Object[]{itemId});
        ItemDTO itemDto = new ItemDTO();
        if (itemDtoMap.get("description") != null) {
            itemDto.setDescription(String.valueOf(itemDtoMap.get("description")));
        }
        if (itemDtoMap.get("id") != null) {
            itemDto.setId(Long.valueOf(String.valueOf(itemDtoMap.get("id"))));
        }
        if (itemDtoMap.get("image") != null) {
            itemDto.setImage(String.valueOf(itemDtoMap.get("image")));
        }
        if (itemDtoMap.get("info") != null) {
            itemDto.setInfo(String.valueOf(itemDtoMap.get("info")));
        }
        if (itemDtoMap.get("name") != null) {
            itemDto.setName(String.valueOf(itemDtoMap.get("name")));
        }
        if (itemDtoMap.get("origin_price") != null) {
            itemDto.setOriginPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(itemDtoMap.get("origin_price")))));
        }
        if (itemDtoMap.get("price") != null) {
            itemDto.setPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(itemDtoMap.get("price")))));
        }
        if (itemDtoMap.get("rating") != null) {
            itemDto.setRating(BigDecimal.valueOf(Double.valueOf(String.valueOf(itemDtoMap.get("rating")))));
        }
        if (itemDtoMap.get("sell_count") != null) {
            itemDto.setSellCount(Long.valueOf(String.valueOf(itemDtoMap.get("sell_count"))));
        }
        if (itemDtoMap.get("shop_id") != null) {
            itemDto.setShopId(Long.valueOf(String.valueOf(itemDtoMap.get("shop_id"))));
        }
        return itemDto;
//        Connection connection = null;
//        try {
//            connection = ds.getConnection();
//            PreparedStatement itemStat = connection.prepareStatement("select * from da_item where id = ?");
//            itemStat.setLong(1, itemId);
//            ResultSet itemRes = itemStat.executeQuery();
//
//            if (!itemRes.next()){
//                itemRes.close();
//                itemStat.close();
//                return null;
//            }
//
//
//            ItemDTO item = new ItemDTO();
//            item.setId(itemRes.getLong("id"));
//            item.setName(itemRes.getString("name"));
//            item.setShopId(itemRes.getLong("shop_id"));
//            item.setPrice(itemRes.getBigDecimal("price"));
//            item.setOriginPrice(itemRes.getBigDecimal("origin_price"));
//            item.setDescription(itemRes.getString("description"));
//            item.setSellCount(itemRes.getLong("sell_count"));
//            item.setRating(itemRes.getBigDecimal("rating"));
//            item.setInfo(itemRes.getString("info"));
//            item.setImage(itemRes.getString("image"));
//            itemRes.close();
//            itemStat.close();
//            return item;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            if(connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

}