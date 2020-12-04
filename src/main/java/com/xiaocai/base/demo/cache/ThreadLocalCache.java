package com.xiaocai.base.demo.cache;

import java.util.HashMap;
import java.util.Map;

/**
 *  线程隔离进行的缓存
 * @author Xiaocai.Zhang
 */
public class ThreadLocalCache {

    private static ThreadLocal<Map<String, Object>> cache = new ThreadLocal<Map<String,Object>>();

    /**
     * 从ThreadLocal里获取缓存的值
     * @param key 要获取的数据的KEY
     * @return 要获取的值
     */
    public static Object getCache(String key) {
        Map<String, Object> map = cache.get();
        if (isCacheIsNull())
            return null;
        if (map.containsKey(key)){
            return map.get(key);
        }
        return null;
    }

    /**
     * 向ThreadLocal缓存值
     * @param key 要缓存的KEY
     * @param value 要缓存的VALUE
     */
    public static void set(String key,Object value) {
        if (!isCacheIsNull()){
            cache.get().put(key, value);
        } else{
            Map<String, Object> vmap = new HashMap<String, Object>();
            vmap.put(key, value);
            cache.set(vmap);
        }
    }

    /**
     * 根据KEY移除缓存里的数据
     * @param key
     */
    public static void removeByKey(String key){
        if (!isCacheIsNull()){
            cache.get().remove(key);
        }
    }

    /**
     * 移除当前线程缓存
     * 用于释放当前线程 threadLocal 资源
     */
    public static void remove(){
        cache.remove();
    }

    private static boolean isCacheIsNull(){
        return  cache.get()==null;
    }


    public static String cacheToString() {
        return isCacheIsNull() ? null : cache.get().toString();
    }

    public static void main(String[] args) {

    }
}
