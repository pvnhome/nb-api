package kz.kase.example.converters;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.Provider;

/**
 * <p>ParamConverter for support of an endpoint input parameter type java.time.LocalDate.</p>
 * <p><b>Created:</b> 26.02.2024 11:30:55</p>
 * @author victor
 */
@Provider
public class LocalDateParamConverter implements ParamConverter<LocalDate> {
   @Override
   public LocalDate fromString(String value) {
      if (value != null) {
         try {
            return LocalDate.parse(value);
         } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(e.getMessage());
         }
      } else {
         throw new IllegalArgumentException("String value is NULL");
      }
   }

   @Override
   public String toString(LocalDate value) {
      return value.toString();
   }
}
