package com.vi.generator.server;

import com.vi.generator.util.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {
    static String moudule = "business";
    static String toServicePath = "server/src/main/java/com/vi/server/service/";
    static String toControllerPath = moudule+"/src/main/java/com/vi/"+moudule+"/controller/admin/";

    public static void main(String[] args) throws IOException, TemplateException {
        serviceGenerate();
    }

    public static void serviceGenerate() throws IOException, TemplateException {
        String upDomain = "Section";
        String domain = "section";
        String tableNameCn = "小节";
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", upDomain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        // 生成service
        FreeMarkerUtil.initConfig("service.ftl");
        FreeMarkerUtil.generator(toServicePath + upDomain + "Service.java", map);
        System.out.println(upDomain + "Service已生成");

        // 生成controller
        FreeMarkerUtil.initConfig("controller.ftl");
        FreeMarkerUtil.generator(toControllerPath + upDomain + "Controller.java", map);
        System.out.println(upDomain + "Controller已生成");
    }
}
