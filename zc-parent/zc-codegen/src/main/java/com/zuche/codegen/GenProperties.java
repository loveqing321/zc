package com.zuche.codegen;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Properties;

/**
 * @author lzx
 * @date 2018/12/21 7:19 PM
 */
@Getter
@Setter
public class GenProperties {

    private static final String BASE_PACKAGE_KEY = "base.package";
    private static final String CONTROLLER_VM_KEY = "controller.vm.file";
    private static final String SERVICE_VM_KEY = "service.vm.file";
    private static final String SERVICE_IMPL_VM_KEY = "service.impl.vm.file";
    private static final String DTO_VM_KEY = "dto.vm.file";
    private static final String QUERY_VM_KEY = "query.vm.file";
    private static final String CONVERTER_VM_KEY = "converter.vm.file";
    private static final String API_VM_KEY = "api.vm.file";
    private static final String API_MOCK_VM_KEY = "api.mock.vm.file";
    private static final String LIST_VM_KEY = "list.vm.file";
    private static final String FORM_VM_KEY = "form.vm.file";
    private static final String OUTPUT_DIR_KEY = "output.dir";
    private static final String SERVICE_OUTPUT_DIR_KEY = "service.output.dir";
    private static final String SERVICE_IMPL_OUTPUT_DIR_KEY = "service.impl.output.dir";

    private String basePackage;

    private String controllerVm;

    private String serviceVm;

    private String serviceImplVm;

    private String dtoVm;

    private String queryVm;

    private String converterVm;

    private String apiVm;

    private String apiMockVm;

    private String listVm;

    private String formVm;

    private String outputDir;

    private String serviceOutputDir;

    private String serviceImplOutputDir;

    private String controllerOutputDir;

    private String uiApiOutputDir;

    private String uiApiMockOutputDir;

    private String uiListOutputDir;

    private String uiFormOutputDir;

    /**
     * 从配置文件加载
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static GenProperties loadFrom(String filename) throws IOException {
        Properties props = new Properties();
        props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename));

        GenProperties genProps = new GenProperties();
        genProps.setBasePackage(props.getProperty(BASE_PACKAGE_KEY));
        genProps.setControllerVm(props.getProperty(CONTROLLER_VM_KEY));
        genProps.setServiceVm(props.getProperty(SERVICE_VM_KEY));
        genProps.setServiceImplVm(props.getProperty(SERVICE_IMPL_VM_KEY));
        genProps.setDtoVm(props.getProperty(DTO_VM_KEY));
        genProps.setQueryVm(props.getProperty(QUERY_VM_KEY));
        genProps.setConverterVm(props.getProperty(CONVERTER_VM_KEY));
        genProps.setApiVm(props.getProperty(API_VM_KEY));
        genProps.setApiMockVm(props.getProperty(API_MOCK_VM_KEY));
        genProps.setListVm(props.getProperty(LIST_VM_KEY));
        genProps.setFormVm(props.getProperty(FORM_VM_KEY));
        genProps.setOutputDir(props.getProperty(OUTPUT_DIR_KEY));
        genProps.setServiceOutputDir(props.getProperty("service.output.dir"));
        genProps.setServiceImplOutputDir(props.getProperty("service.impl.output.dir"));
        genProps.setControllerOutputDir(props.getProperty("controller.output.dir"));
        genProps.setUiApiOutputDir(props.getProperty("ui.api.output.dir"));
        genProps.setUiApiMockOutputDir(props.getProperty("ui.api.mock.output.dir"));
        genProps.setUiListOutputDir(props.getProperty("ui.list.output.dir"));
        genProps.setUiFormOutputDir(props.getProperty("ui.form.output.dir"));
        return genProps;
    }

}
