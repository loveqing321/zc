package top.zuche.manager.console.system.controller;

import top.zuche.services.api.dto.DictDTO;
import top.zuche.services.api.dto.Paging;
import top.zuche.services.api.query.DictQuery;
import top.zuche.common.web.resp.ResponseData;
import top.zuche.services.api.service.DictRpcService;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/api/v1/system/dict")
public class DictController {

    @Reference
    private DictRpcService dictRpcService;

    // 分页查询
    @PostMapping("/queryPage")
    public ResponseData<Paging<DictDTO>> queryPage(@RequestBody DictQuery query) {
        Paging<DictDTO> pageResult = dictRpcService.queryPageByCondition(query);
        return ResponseData.ok(pageResult);
    }

    // 根据主键查询指定对象
    @GetMapping("/queryOne")
    public ResponseData<DictDTO> queryOne(int id) {
        DictDTO dto = dictRpcService.queryDictByPrimaryKey(id);
        return ResponseData.ok(dto);
    }

    // 增加和更新记录
    @PostMapping("/save")
    public ResponseData save(@RequestBody DictDTO dto) {
        if (dto.getId() != null) {
            dictRpcService.updateDictByPrimaryKey(dto);
        } else {
            dictRpcService.addDict(dto);
        }
        return ResponseData.ok();
    }

    // 删除记录
    @GetMapping("/del")
    public ResponseData del(int id) {
        dictRpcService.deleteDictByPrimaryKey(id);
        return ResponseData.ok();
    }
}
