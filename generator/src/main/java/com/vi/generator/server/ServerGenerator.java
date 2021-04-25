package com.vi.generator.server;

import com.vi.generator.util.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {
    static String toServicePath = "server/src/main/java/com/vi/server/service/";
    static String toControllerPath = "business/src/main/java/com/vi/business/controller/admin/";

    public static void main(String[] args) throws IOException, TemplateException {
//        FreeMarkerUtil.initConfig("test.ftl");
//        FreeMarkerUtil.generator(toPath+"Test.java");
        serviceGenerate();
    }

    public static void serviceGenerate() throws IOException, TemplateException {
        String upDomain = "Section";
        String domain = "section";
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", upDomain);
        map.put("domain", domain);
        FreeMarkerUtil.initConfig("service.ftl");
        FreeMarkerUtil.generator(toServicePath + upDomain + "Service.java", map);
        System.out.println(upDomain + "Service已生成");

        FreeMarkerUtil.initConfig("controller.ftl");
        FreeMarkerUtil.generator(toControllerPath + upDomain + "Controller.java", map);
        System.out.println(upDomain + "Controller已生成");
    }
}
