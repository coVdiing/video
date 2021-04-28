package com.vi.generator.server;

import com.vi.generator.util.DbUtil;
import com.vi.generator.util.Field;
import com.vi.generator.util.FreeMarkerUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ServerGenerator {
    static String moudule = "business";
    static String toServicePath = "server/src/main/java/com/vi/server/service/";
    static String toDtoPath = "server/src/main/java/com/vi/server/dto/";
    static String toControllerPath = moudule + "/src/main/java/com/vi/" + moudule + "/controller/admin/";
    static String generatorConfigPath = "server/src/main/resources/generator/generatorConfig.xml";

    public static void main(String[] args) throws Exception {
        serviceGenerate();
    }

    public static void serviceGenerate() throws Exception {
        // 只生成配置文件中第一个table节点
        File file = new File(generatorConfigPath);
        SAXReader reader = new SAXReader();
        // 读取xml文件到Document中
        Document doc = reader.read(file);
        // 获取xml的根节点
        Element rootElement = doc.getRootElement();
        // 读取context节点
        Element contextElement = rootElement.element("context");
        // 定义一个Element用于遍历
        Element tableElement;
        // 取第一个"table"的节点
        tableElement = contextElement.elementIterator("table").next();
        String upDomain = tableElement.attributeValue("domainObjectName");
        String tableName = tableElement.attributeValue("tableName");
        String domain = upDomain.substring(0, 1).toLowerCase() + upDomain.substring(1);
        String tableNameCn = DbUtil.getTableComment(tableName);

        Map<String, Object> map = new HashMap<>();
        List<Field> fields = DbUtil.getColumnByTableName(domain);
        Set<String> typeSet = getJavaTypes(fields);
        map.put("Domain", upDomain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("fieldList", fields);
        map.put("typeSet", typeSet);
        // 生成DTO
        FreeMarkerUtil.initConfig("dto.ftl");
        FreeMarkerUtil.generator(toDtoPath+upDomain+"Dto.java",map);
        System.out.println("Dto已生成");
        // 生成service
        FreeMarkerUtil.initConfig("service.ftl");
        FreeMarkerUtil.generator(toServicePath + upDomain + "Service.java", map);
        System.out.println(upDomain + "Service已生成");

        // 生成controller
        FreeMarkerUtil.initConfig("controller.ftl");
        FreeMarkerUtil.generator(toControllerPath + upDomain + "Controller.java", map);
        System.out.println(upDomain + "Controller已生成");
    }

    private static Set<String> getJavaTypes(List<Field> fields) {
        Set<String> javaTypes = new HashSet<>();
        fields.forEach(ele -> javaTypes.add(ele.getJavaType()));
        return javaTypes;
    }


}
