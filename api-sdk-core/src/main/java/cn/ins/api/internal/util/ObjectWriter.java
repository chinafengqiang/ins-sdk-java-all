package cn.ins.api.internal.util;



import cn.ins.api.InsConstants;
import cn.ins.api.internal.mapping.ApiField;
import cn.ins.api.internal.mapping.ApiListField;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.CharacterIterator;
import java.text.DateFormat;

import java.util.*;
import java.util.logging.Logger;

public class ObjectWriter {
    private InsHashMap  buf           = new InsHashMap();
    private Stack<Object> calls         = new Stack<Object>();
    private boolean       emitClassName = true;
    private DateFormat    format;
    private static final String GET_PREFIX = "get";
    private static final String SET_PREFIX = "set";
    private static final String IS_PREFIX = "is";

    public ObjectWriter(boolean emitClassName) {
        this.emitClassName = emitClassName;
    }

    public ObjectWriter() {
        this(false);
    }

    public ObjectWriter(DateFormat format) {
        this(false);
        this.format = format;
    }

    public InsHashMap write(Object object) {
        return write(object, false);
    }

    public InsHashMap write(Object object, boolean isApiModel) {
        buf.clear();
        value(object, isApiModel);
        return buf;
    }


    private void value(Object object) {
        value(object, false);
    }

    private void value(Object object, boolean isApiModel) {
        if (object == null || cyclic(object)) {

        } else {
            calls.push(object);

            if (isApiModel) {
                model(object);
            }
            else {
                bean(object);
            }

            calls.pop();
        }
    }

    private boolean cyclic(Object object) {
        Iterator<Object> it = calls.iterator();
        while (it.hasNext()) {
            Object called = it.next();
            if (object == called) { return true; }
        }
        return false;
    }

    private void bean(Object object) {
        BeanInfo info;
        try {
            info = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            for (int i = 0; i < props.length; ++i) {
                PropertyDescriptor prop = props[i];
                String name = prop.getName();
                Method accessor = prop.getReadMethod();
                if ((emitClassName || !"class".equals(name)) && accessor != null) {
                    if (!accessor.isAccessible()) { accessor.setAccessible(true); }
                    Object value = accessor.invoke(object, (Object[]) null);
                    if (value == null) { continue; }
                    add(name, value);
                }
            }
            Field[] ff = object.getClass().getFields();
            for (int i = 0; i < ff.length; ++i) {
                Field field = ff[i];
                Object value = field.get(object);
                if (value == null) { continue; }
                add(field.getName(), value);
            }
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
        } catch (InvocationTargetException ite) {
            ite.getCause().printStackTrace();
            ite.printStackTrace();
        } catch (IntrospectionException ie) {
            ie.printStackTrace();
        }
    }

    private void model(Object object) {
        Field[] ff = object.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < ff.length; ++i) {
                Field field = ff[i];
                // 获取注解
                ApiField jsonField = field.getAnnotation(ApiField.class);
                ApiListField listField = field.getAnnotation(ApiListField.class);
                // 优先处理列表类型注解,非列表类型才处理字段注解
                if (listField != null) {
                    PropertyDescriptor pd = getPropertyDescriptor(field.getName(), object);
                    Method accessor = pd.getReadMethod();
                    if (!accessor.isAccessible()) { accessor.setAccessible(true); }
                    Object value = accessor.invoke(object, (Object[]) null);
                    if (value == null) { continue; }
                    add(listField.value(), value, true);
                } else if (jsonField != null) {
                    PropertyDescriptor pd = getPropertyDescriptor(field.getName(), object);
                    Method accessor = pd.getReadMethod();
                    if (!accessor.isAccessible()) { accessor.setAccessible(true); }
                    Object value = accessor.invoke(object, (Object[]) null);
                    if (value == null) { continue; }
                    add(jsonField.value(), value, true);
                }
            }
        } catch (IntrospectionException e1) {
            InsLogger.logBizError(e1);
        } catch (IllegalAccessException e2) {
            InsLogger.logBizError(e2);
        } catch (IllegalArgumentException e3) {
            InsLogger.logBizError(e3);
        } catch (InvocationTargetException e4) {
            InsLogger.logBizError(e4);
        }
    }

    private void add(String name, Object value, boolean isApiModel) {
        buf.put(name,value);
        //value(value, isApiModel);
    }

    private PropertyDescriptor getPropertyDescriptor(String fieldName, Object object) throws IntrospectionException {
        try {
            return new PropertyDescriptor(fieldName, object.getClass());
        } catch (IntrospectionException e) {
            try {
                return new PropertyDescriptor(fieldName, object.getClass(),
                        GET_PREFIX + getSetMethod(fieldName), SET_PREFIX + getSetMethod(fieldName));
            } catch (IntrospectionException ex) {
                return new PropertyDescriptor(fieldName, object.getClass(),
                        IS_PREFIX + getSetMethod(fieldName), SET_PREFIX + getSetMethod(fieldName));
            }
        }
    }

    /**
     * 如果第二个字母是大写，则首字母不转大写
     */
    private String getSetMethod(String str) {
        if (null == str || "".equals(str.trim())) {
            return str;
        }
        char[] chars = str.toCharArray();
        char c1 = chars[0];
        if (c1 >= 97 && c1 <= 122) {
            if (chars.length > 1) {
                char c2 = chars[1];
                if (!(c2 >= 65 && c2 <= 90)) {
                    chars[0] = (char) (c1 - 32);
                }
            } else {
                chars[0] = (char) (c1 - 32);
            }
        }
        return new String(chars);
    }

    private void add(String name, Object value) {
        add(name, value, false);
    }






}
