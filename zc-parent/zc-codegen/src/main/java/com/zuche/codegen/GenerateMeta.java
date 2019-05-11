package com.zuche.codegen;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author lzx
 * @date 2018/12/21 9:07 PM
 */
@Getter
@RequiredArgsConstructor
public class GenerateMeta {

    private final String module;

    private final String subModule;

    private final String subModuleName;

    private final String subModuleListTitle;

    @JSONField(name = "subModuleLower")
    public String getSubModuleLower() {
        return subModule.substring(0, 1).toLowerCase() + subModule.substring(1);
    }

    @JSONField(name = "subModuleSpl")
    public String getSubModuleLineSplitter() {
        if (subModule == null) return null;
        String str = getSubModuleLower();
        return splitterString(str);
    }

    @JSONField(name = "moduleSpl")
    public String getModuleLineSplitter() {
        if (module == null) return null;
        return splitterString(module);
    }

    @JSONField(name = "DO")
    public String getDO() {
        return subModule;
    }

    @JSONField(name = "Entity")
    public String getEntity() {
        return subModule;
    }

    @JSONField(name = "DTO")
    public String getDTO() {
        return subModule + "DTO";
    }

    @JSONField(name = "VO")
    public String getVO() {
        return subModule + "VO";
    }

    @JSONField(name = "Query")
    public String getQuery() {
        return subModule + "Query";
    }

    private String splitterString(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("-").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
            i ++;
        }
        return sb.toString();
    }
}
