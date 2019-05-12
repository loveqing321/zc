package top.zuche.common.base;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * @author lzx
 * @date 2019/5/12 上午8:32
 */
public abstract class BaseService<E, D> {

    private final Object dtoLock = new Object();
    private final Object entityLock = new Object();

    private Constructor<D> dtoConstructor;

    private Constructor<E> entityConstructor;

    /**
     * DTO 转 Entity
     *
     * @param dto
     * @return
     */
    public E dto2Entity(D dto) {
        Constructor<E> con = genEntityConstructorIfNecessary();
        try {
            E entity = con.newInstance();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("无法初始化Entity对象");
        }
    }

    /**
     * Entity 转 DTO
     *
     * @param entity
     * @return
     */
    public D entity2Dto(E entity) {
        Constructor<D> con = genDtoConstructorIfNecessary();
        try {
            D dto = con.newInstance();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("无法初始化DTO对象");
        }
    }

    @SuppressWarnings("unchecked")
    private Constructor<D> genDtoConstructorIfNecessary() {
        if (dtoConstructor == null) {
            synchronized (dtoLock) {
                if (dtoConstructor == null) {
                    Class<D> clazz = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
                    try {
                        dtoConstructor = clazz.getConstructor();
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException("无法获取" + clazz.getName() + "的无参构造函数");
                    }
                }
            }
        }
        return dtoConstructor;
    }

    @SuppressWarnings("unchecked")
    private Constructor<E> genEntityConstructorIfNecessary() {
        if (entityConstructor == null) {
            synchronized (entityLock) {
                if (entityConstructor == null) {
                    Class<E> clazz = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                    try {
                        entityConstructor = clazz.getConstructor();
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException("无法获取" + clazz.getName() + "的无参构造函数");
                    }
                }
            }
        }
        return entityConstructor;
    }
}
