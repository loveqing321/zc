package com.zuche.codegen;

import java.io.IOException;

/**
 * @author lzx
 * @date 2018/12/21 7:13 PM
 */
public class Main {

    public static void main(String[] args) throws IOException {
        GenProperties properties = GenProperties.loadFrom("generator.properties");
        CodeGenerator generator = new CodeGenerator(properties);

        GenerateMeta meta = null;
        // system 模块
        meta = new GenerateMeta("system",
                "User",
                "用户管理",
                "用户列表");
        generator.genCode(meta);

//        meta = new GenerateMeta("assets",
//                "Vehicle",
//                "车辆管理",
//                "车辆列表");
//        generator.genCode(meta);
//
//        meta = new GenerateMeta("assets",
//                "Customer",
//                "客户管理",
//                "客户列表");
//        generator.genCode(meta);
//
//        meta = new GenerateMeta("assets",
//                "Equip",
//                "装备管理",
//                "装备列表");
//        generator.genCode(meta);
//
//        // biss 模块
//        meta = new GenerateMeta("biss",
//                "RentalRecord",
//                "租赁记录",
//                "租赁记录列表");
//        generator.genCode(meta);
//        // mtnc模块
//        meta = new GenerateMeta("mtnc",
//                "CheckRecord",
//                "年检记录",
//                "年检列表");
//        generator.genCode(meta);
    }
}
