//package top.zuche.merchant.console.order.service.impl;
//
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//import top.zuche.merchant.console.cache.CacheManagerNames;
//import top.zuche.merchant.console.cache.EhCacheNames;
//import top.zuche.merchant.console.order.service.IOrderService;
//
///**
// * @author lzx
// * @date 2019/5/8 上午10:11
// */
//@Service
//@CacheConfig(cacheNames = EhCacheNames.CACHE_10MIN, cacheManager = CacheManagerNames.EHCACHE_CACHE_MANAGER)
//public class OrderServiceImpl implements IOrderService {
//
//    @Override
//    @CachePut(key = "#p0")
//    public String save(String id, String content) {
//        System.out.println("保存数据库");
//        return content;
//    }
//
//    @Override
//    @Cacheable(key = "#p0")
//    public String get(String id) {
//        // 读数据库
//        System.out.println("读数据库");
//        return "abc";
//    }
//
//    @Override
//    @CacheEvict(key = "#p0")
//    public void delete(String id) {
//        // 删除数据库内容
//        System.out.println("删除数据库内容");
//    }
//
//    @Override
//    @CachePut(key = "#p0", condition = "#result != null")
//    public String update(String id, String content) {
//        System.out.println("更新数据库");
//        return null;
//    }
//
//    @Override
//    @CacheEvict(allEntries = true)
//    public void clear() {
//        System.out.println("清除数据库");
//    }
//
//}
