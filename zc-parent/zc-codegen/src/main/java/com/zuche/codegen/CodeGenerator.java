package com.zuche.codegen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * 代码生成器，用于生成Service和Controller的模版代码
 */
public class CodeGenerator {

    private VelocityContext context;

    private final GenProperties properties;

    public CodeGenerator(GenProperties properties) {
        this.properties = properties;
        Velocity.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        Velocity.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        Velocity.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        Velocity.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init();

        context = new VelocityContext();
        context.put("basePackage", properties.getBasePackage());
    }

    /**
     * 生成代码
     *
     * @throws IOException
     */
    public void genCode(GenerateMeta meta) throws IOException {
        // 填充Context对象
        this.fillContext(meta);
        // 生成pojo代码
//        this.genPojo(meta);
        // 生成Service代码
//        this.genService(meta);
//        // 生成Controller代码
//        this.genController(meta);

        // 生成UI相关代码
        this.genUI(meta);
    }

    /**
     * 生成DTO代码
     *
     * @throws IOException
     */
    private void genPojo(GenerateMeta meta) throws IOException {
        System.out.println("Begin to generate pojo code.");

        // 获取模版文件对象
        Template template = Velocity.getTemplate(properties.getDtoVm());

        // 确保dto文件夹存在
        String dtoDir = insureWebDirExists(meta.getModule() + "/dto");

        // 写Service接口文件
        writeToFile(template, dtoDir + "/" + meta.getSubModule() + "DTO.java");

        // 获取Query模版文件对象
        template = Velocity.getTemplate(properties.getQueryVm());

        // 写Service接口实现文件
        writeToFile(template, dtoDir + "/" + meta.getSubModule() + "Query.java");

        // 确保vo文件夹存在
        String converterDir = insureWebDirExists(meta.getModule() + "/converter");

        // 获取Vo模版文件对象
        template = Velocity.getTemplate(properties.getConverterVm());

        // 写Service接口实现文件
        writeToFile(template, converterDir + "/ModelConverter.java");
    }

    /**
     * 生成子模块的服务类代码
     */
    private void genService(GenerateMeta meta) throws IOException {
        System.out.println("Begin to generate service code.");

        // 获取模版文件对象
        Template template = Velocity.getTemplate(properties.getServiceVm());

        // 确保Service文件夹存在
        String serviceDir = insureDirExists(properties.getServiceOutputDir());

        // 写Service接口文件
        writeToFile(template, serviceDir + "/" + meta.getSubModule() + "RpcService.java");

//        // 获取ServiceImpl模版文件对象
//        template = Velocity.getTemplate(properties.getServiceImplVm());
//
//        // 确保ServiceImpl文件夹存在
//        String serviceImplDir = insureDirExists(properties.getServiceImplOutputDir());
//
//        // 写Service接口实现文件
//        writeToFile(template, serviceImplDir + "/" + meta.getSubModule() + "ServiceImpl.java");
    }

    /**
     * 生成子模块的控制器代码
     */
    private void genController(GenerateMeta meta) throws IOException {
        System.out.println("Begin to generate controller code.");

        // 获取模版文件对象
        Template template = Velocity.getTemplate(properties.getControllerVm());

        // 确保Service文件夹存在
        String controllerDir = insureDirExists(properties.getControllerOutputDir());

        // 写Service接口文件
        writeToFile(template, controllerDir + "/" + meta.getSubModule() + "Controller.java");
    }

    /**
     * 生成子模块的UI代码
     */
    private void genUI(GenerateMeta meta) throws IOException {
        System.out.println("Begin to generate ui code.");

        // 获取模版文件对象
        Template template = Velocity.getTemplate(properties.getApiVm());

        // 确保api文件夹存在
        String apiDir = insureDirExists(properties.getUiApiOutputDir());

        // 写api接口文件
        writeToFile(template, apiDir + "/" + meta.getSubModuleLineSplitter() + ".js");

        // 获取模版文件对象
        template = Velocity.getTemplate(properties.getApiMockVm());

        // 确保mock文件夹存在
        String mockDir = insureDirExists(properties.getUiApiMockOutputDir());

        // 写mock文件
        writeToFile(template, mockDir + "/" + meta.getSubModuleLineSplitter() + ".js");

        // 获取模版文件对象
        template = Velocity.getTemplate(properties.getListVm());

        // 确保子文件夹存在
        String subDir = insureDirExists(properties.getUiListOutputDir() + "/" + meta.getSubModuleLineSplitter());

        // 写index文件
        writeToFile(template, subDir + "/index.vue");

        // 获取模版文件对象
        template = Velocity.getTemplate(properties.getFormVm());

        // 写index文件
        writeToFile(template, subDir + "/" + meta.getSubModuleLineSplitter() + "-form.vue");
    }

    /**
     * 填充上下文对象
     *
     * @param meta
     */
    private void fillContext(GenerateMeta meta) {
        JSONObject object = JSON.parseObject(JSON.toJSONString(meta));
        object.forEach((key, value) -> {
            context.put(key, value);
        });
    }

    /**
     * 确保文件夹存在
     *
     * @param path
     */
    private String insureDirExists(String path) {
        // 输出Service接口
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();
        return dir.getAbsolutePath();
    }

    /**
     * 确保文件夹存在
     *
     * @param subPath
     */
    private String insureWebDirExists(String subPath) {
        // 输出Service接口
        File dir = new File(properties.getOutputDir() + File.separator + "web" + File.separator + subPath);
        if (!dir.exists()) dir.mkdirs();
        return dir.getAbsolutePath();
    }

    /**
     * 确保文件夹存在
     *
     * @param subPath
     */
    private String insureUIDirExists(String subPath) {
        // 输出Service接口
        File dir = new File(properties.getOutputDir() + File.separator + "ui" + File.separator + subPath);
        if (!dir.exists()) dir.mkdirs();
        return dir.getAbsolutePath();
    }

    /**
     * 写入到文件
     * @param template
     * @param filePath
     * @throws IOException
     */
    private void writeToFile(Template template, String filePath) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath), Charset.forName("UTF-8"));
        template.merge(context, writer);
        writer.flush();
        writer.close();
    }
}
