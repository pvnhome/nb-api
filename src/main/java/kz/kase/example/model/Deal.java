package kz.kase.example.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.json.bind.annotation.JsonbDateFormat;

/**
 * <p>Сделка.</p>
 * <p><b>Created:</b> 06.05.2025 14:30:56</p>
 * @author victor
 */
@Schema(type = SchemaType.OBJECT, title = "Сделка", description = "Запись сделки с NTK.")
public class Deal {
   private static final DateTimeFormatter ANSI = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

   @Schema(type = SchemaType.INTEGER, format = "int64", description = "Уникальный код сделки в информационной системе НБ.")
   public Long id;
   @Schema(type = SchemaType.STRING, format = "date", description = "Дата и время сделки в формате \"yyyy-MM-ddTHH:mm:ss\"")
   @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
   public LocalDateTime dealTime;
   @Schema(type = SchemaType.STRING, description = "Код ценной бумаги")
   public String code;
   @Schema(type = SchemaType.STRING, description = "Код ISIN")
   public String isin;
   @Schema(type = SchemaType.INTEGER, format = "int32", description = "Срок до погашения в днях")
   public Integer dtm;
   @Schema(type = SchemaType.NUMBER, format = "double", description = "Доходность к погашению, % годовых")
   public BigDecimal yield;
   @Schema(type = SchemaType.NUMBER, format = "double", description = "Цена в % от номинала")
   public BigDecimal price;
   @Schema(type = SchemaType.INTEGER, format = "int64", description = "Количество в штуках")
   public Long quantity;
   @Schema(type = SchemaType.NUMBER, format = "double", description = "Объем сделки в тенге")
   public BigDecimal volKzt;
   @Schema(type = SchemaType.STRING, format = "date", description = "Дата оплаты в формате \"yyyy-MM-dd\"")
   @JsonbDateFormat("yyyy-MM-dd")
   public LocalDate paymentDate;
   @Schema(type = SchemaType.BOOLEAN, description = "Резидент не резидент")
   public Boolean resident;

   public Deal() {
   }

   public Deal(Long id, String time, String code, String isin, Integer dtm, String yield, String price, Long quantity, String volKzt, String paymentDate, Boolean resident) {
      this.id = id;
      this.dealTime = LocalDateTime.parse(time, ANSI);
      this.code = code;
      this.isin = isin;
      this.dtm = dtm;
      this.yield = new BigDecimal(yield);
      this.price = new BigDecimal(price);
      this.quantity = quantity;
      this.volKzt = new BigDecimal(volKzt);
      this.paymentDate = LocalDate.parse(paymentDate, DateTimeFormatter.ISO_DATE);
      this.resident = resident;
   }
}
