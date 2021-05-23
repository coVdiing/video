package com.vi.generator.vue;

import com.vi.generator.util.DbUtil;
import com.vi.generator.util.Field;
import com.vi.generator.util.FreeMarkerUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

public class VueGenerator {
    static String moudule = "business";
    static String toVuePath = "admin\\src\\views\\admin\\";
    static String generatorConfigPath = "server\\src\\main\\resources\\generator\\generatorConfig.xml";

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
        List<Field> fields = DbUtil.getColumnByTableName(tableName);
        Set<String> typeSet = getJavaTypes(fields);
        map.put("Domain", upDomain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("fieldList", fields);
        map.put("typeSet", typeSet);
        map.put("module", moudule);
        // 生成Vue
        FreeMarkerUtil.initConfig("vue.ftl");
        FreeMarkerUtil.generator(toVuePath+domain+".vue",map);
        System.out.println(domain+".vue已生成");

    }

    private static Set<String> getJavaTypes(List<Field> fields) {
        Set<String> javaTypes = new HashSet<>();
        fields.forEach(ele -> javaTypes.add(ele.getJavaType()));
        return javaTypes;
    }


}
