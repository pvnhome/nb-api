package kz.kase.example.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import kz.kase.example.model.Deal;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/ntk")
public class NTKDealsResource {
   private List<Deal> deals = new ArrayList<>();

   public NTKDealsResource() {
      deals.add(new Deal(30790L, "2025-04-01 14:15:01", "NTK028_2861", "KZ2C00010478", 28, "15.7500", "98.8062", 100000000L, "9880620000.00", "2025-04-05", true));
      deals.add(new Deal(30791L, "2025-04-01 13:25:21", "NTK028_2861", "KZ2C00010478", 28, "15.7500", "98.7012", 30000000L, "380620077.00", "2025-04-05", false));
      deals.add(new Deal(30792L, "2025-04-01 15:46:22", "NTK028_2861", "KZ2C00010478", 28, "15.7500", "98.7012", 30040000L, "390920077.00", "2025-04-05", false));
      deals.add(new Deal(30892L, "2025-04-02 15:01:03", "NTK028_2861", "KZ2C00010478", 27, "15.6500", "98.6072", 70000000L, "780620023.00", "2025-04-06", true));
      deals.add(new Deal(30893L, "2025-04-02 16:17:31", "NTK028_2861", "KZ2C00010478", 27, "15.6500", "98.6773", 96700000L, "980620000.00", "2025-04-06", true));
   }

   //@formatter:off
   @GET
   @Path("/deals")
   @Produces(MediaType.APPLICATION_JSON)
   @Operation(
      summary = "Загрузка списка сделок с NTK", 
      description = "Метод возвращает список сделок в формате JSON на указанную дату.",
      operationId = "getNtkDeals"
   )
   @Tag(name = "NTK_DEALS", description = "Методы REST API для работы со сделками NTK.")
   @APIResponses( value = {
      @APIResponse(responseCode = "200", description = "Успешное выполнение операции", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Deal[].class))),
      @APIResponse(responseCode = "404", description = "Неверный формат параметров", content = @Content(mediaType = MediaType.TEXT_HTML)),
      @APIResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = MediaType.TEXT_HTML))
   })
   public Response getNtkDeals(
      @QueryParam("date") 
      @Parameter(
         in = ParameterIn.QUERY, 
         name = "date",
         description = "Дата совершения сделок в формате ISO-8601 {uuuu-MM-dd}. В случае если сделки на указанную дату отсутствует, метод возвращает пустой список. В случае если дата не указана, метод вернет сделки за тот день, когда они были в последний раз.",
          schema = @Schema(
             type = SchemaType.STRING,
             format = "date",
             example = "2025-04-01"
          )
      ) 
      LocalDate requestDate 
   ) {
   //@formatter:on
      try {
         if (requestDate == null) {
            requestDate = deals.stream().map(d -> d.dealTime.toLocalDate()).sorted(Comparator.reverseOrder()).findFirst().orElseGet(LocalDate::now);
         }
         LocalDateTime beg = requestDate.atStartOfDay();
         LocalDateTime end = requestDate.atTime(LocalTime.MAX);
         List<Deal> filteredDeals = deals.stream().filter(d -> d.dealTime.isAfter(beg) && d.dealTime.isBefore(end)).collect(Collectors.toList());
         return Response.ok(filteredDeals).build();
      } catch (Exception e) {
         return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML).build();
      }
   }
}
