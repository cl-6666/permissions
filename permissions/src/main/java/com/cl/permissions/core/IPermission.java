package com.cl.permissions.core;

/**
 * 项目：permissions-Text
 * 版权：蒲公英公司 版权所有
 * 作者：Arry
 * 版本：1.0
 * 创建日期：2019-12-03
 * 描述：
 * 修订历史：
 */
public interface IPermission {

    void ganted(); // 已经授权

    void cancel(); // 取消授权

    void denied(); // 拒绝授权

}
