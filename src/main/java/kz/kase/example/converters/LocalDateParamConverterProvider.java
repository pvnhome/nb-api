package kz.kase.example.converters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

/**
 * <p>ParamConverterProvider for LocalDate.</p>
 * <p><b>Created:</b> 26.02.2024 11:34:00</p>
 * @author victor
 */
@Provider
public class LocalDateParamConverterProvider implements ParamConverterProvider {
   @Override
   @SuppressWarnings("unchecked")
   public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
      if (rawType.isAssignableFrom(LocalDate.class)) {
         return (ParamConverter<T>) new LocalDateParamConverter();
      }
      return null;
   }
}
