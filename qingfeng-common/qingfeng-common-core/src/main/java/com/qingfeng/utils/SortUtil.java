package com.qingfeng.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.MyConstant;
import com.qingfeng.entity.QueryRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * @ProjectName SortUtil
 * @author Administrator
 * @version 1.0.0
 * @Description 处理排序工具类
 * @createTime 2021/4/3 0003 19:54
 */
public abstract class SortUtil {
    /**
     * 处理排序（分页情况下） for mybatis-plus
     *
     * @param request           QueryRequest
     * @param page              Page
     * @param defaultSort       默认排序的字段
     * @param defaultOrder      默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handlePageSort(QueryRequest request, Page<?> page, String defaultSort, String defaultOrder, boolean camelToUnderscore) {
        page.setCurrent(request.getPageNum());
        page.setSize(request.getPageSize());
        String sortField = request.getField();
        if (camelToUnderscore) {
            sortField = MyUtil.camelToUnderscore(sortField);
            defaultSort = MyUtil.camelToUnderscore(defaultSort);
        }
        if (StringUtils.isNotBlank(request.getField())
                && StringUtils.isNotBlank(request.getOrder())
                && !StringUtils.equalsIgnoreCase(request.getField(), "null")
                && !StringUtils.equalsIgnoreCase(request.getOrder(), "null")) {
            if (StringUtils.equals(request.getOrder(), MyConstant.ORDER_DESC)) {
                page.addOrder(OrderItem.desc(sortField));
            } else {
                page.addOrder(OrderItem.asc(sortField));
            }
        } else {
            if (StringUtils.isNotBlank(defaultSort)) {
                if (StringUtils.equals(defaultOrder, MyConstant.ORDER_DESC)) {
                    page.addOrder(OrderItem.desc(defaultSort));
                } else {
                    page.addOrder(OrderItem.asc(defaultSort));
                }
            }
        }
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request QueryRequest
     * @param page    Page
     */
    public static void handlePageSort(QueryRequest request, Page<?> page) {
        handlePageSort(request, page, null, null, false);
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request           QueryRequest
     * @param page              Page
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handlePageSort(QueryRequest request, Page<?> page, boolean camelToUnderscore) {
        handlePageSort(request, page, null, null, camelToUnderscore);
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request           QueryRequest
     * @param wrapper           wrapper
     * @param defaultSort       默认排序的字段
     * @param defaultOrder      默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handleWrapperSort(QueryRequest request, QueryWrapper<?> wrapper, String defaultSort, String defaultOrder, boolean camelToUnderscore) {
        String sortField = request.getField();
        if (camelToUnderscore) {
            sortField = MyUtil.camelToUnderscore(sortField);
            defaultSort = MyUtil.camelToUnderscore(defaultSort);
        }
        if (StringUtils.isNotBlank(request.getField())
                && StringUtils.isNotBlank(request.getOrder())
                && !StringUtils.equalsIgnoreCase(request.getField(), "null")
                && !StringUtils.equalsIgnoreCase(request.getOrder(), "null")) {
            if (StringUtils.equals(request.getOrder(), MyConstant.ORDER_DESC)) {
                wrapper.orderByDesc(sortField);
            } else {
                wrapper.orderByAsc(sortField);
            }
        } else {
            if (StringUtils.isNotBlank(defaultSort)) {
                if (StringUtils.equals(defaultOrder, MyConstant.ORDER_DESC)) {
                    wrapper.orderByDesc(defaultSort);
                } else {
                    wrapper.orderByAsc(defaultSort);
                }
            }
        }
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request QueryRequest
     * @param wrapper wrapper
     */
    public static void handleWrapperSort(QueryRequest request, QueryWrapper<?> wrapper) {
        handleWrapperSort(request, wrapper, null, null, false);
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request           QueryRequest
     * @param wrapper           wrapper
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handleWrapperSort(QueryRequest request, QueryWrapper<?> wrapper, boolean camelToUnderscore) {
        handleWrapperSort(request, wrapper, null, null, camelToUnderscore);
    }
}
