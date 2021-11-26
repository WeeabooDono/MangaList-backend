package fr.weeab.mangalist.core.context;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class AbstractValidationTest extends AbstractAppContextTest {

    protected static void setValueToParam(Object dto, String parameterName, Object value) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        // On vire les prefix "is" des paramètres de la classe car les setters/getters n'utilisent pas ce prefix
        if (parameterName.startsWith("is")) {
            parameterName = parameterName.substring(2, 3).toLowerCase() + parameterName.substring(3);
        }

        for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(dto.getClass()).getPropertyDescriptors()) {
            if (propertyDescriptor.getName().equals(parameterName)) {
                propertyDescriptor.getWriteMethod().invoke(dto, value);
                break;
            }
        }
    }
}
