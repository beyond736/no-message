package com.volfine.common;

import com.jfinal.config.*;
import com.jfinal.config.Constants;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.volfine.controller.*;
import com.volfine.interceptor.UrlInterceptor;
import com.volfine.model._MappingKit;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * <p>
 * API引导式配置
 */
public class VolfineConfig extends JFinalConfig {

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        // 加载少量必要配置，随后可用PropKit.get(...)获取值
        PropKit.use("volfine_config.txt");
        me.setDevMode(PropKit.getBoolean("devMode", false));
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.setBaseViewPath("/WEB-INF/pages");
        //me.add("/", IndexController.class);    // 第三个参数为该Controller的视图存放路径
        me.add("/", AdminController.class);
        me.add("/product", ProductController.class);
        me.add("/category", CategoryController.class);
        me.add("/email", EmailController.class);
        me.add("/supplier", SupplierController.class);
        me.add("/order", OrderController.class);
        me.add("/customer", CustomerController.class);
        me.add("/quoted", QuotedController.class);
    }

    public void configEngine(Engine me) {
        me.addSharedFunction("/WEB-INF/pages/include/pagination.html");
        me.addSharedFunction("/WEB-INF/pages/include/box-header.html");
        me.addSharedObject("sk", new com.jfinal.kit.StrKit());
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        // 配置 druid 数据库连接池插件
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        me.add(druidPlugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        // 所有映射在 MappingKit 中自动化搞定
        arp.setShowSql(PropKit.getBoolean("showSql", true));
        _MappingKit.mapping(arp);
        me.add(arp);
    }

    public static DruidPlugin createDruidPlugin() {
        return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {
        me.add(new SessionInViewInterceptor());
        me.add(new UrlInterceptor());
    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {
        me.add(new ContextPathHandler("ctx"));
    }
}
