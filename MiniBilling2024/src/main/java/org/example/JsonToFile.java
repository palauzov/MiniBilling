package org.example;

import org.example.entities.Invoice;
import org.example.entities.Line;
import org.example.entities.Reading;
import org.example.entities.User;

import javax.json.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonToFile {
    public void writeToJson(List<Invoice> invoices) {
        JsonArrayBuilder jsonInvoiceBuilder = Json.createArrayBuilder();

        for (int i = 0; i < invoices.size(); i++) {
            JsonArrayBuilder jsonLineBuilder = Json.createArrayBuilder();
            List<Line> lines = invoices.get(i).getLines();
            for (int j = 0; j < lines.size(); j++) {
                jsonLineBuilder.add(Json.createObjectBuilder()
                        .add("index", lines.get(j).getIndex())
                        .add("quantity", lines.get(j).getQuantity())
                        .add("price", lines.get(j).getAmount()));
            }

            JsonObjectBuilder userBuilder = Json.createObjectBuilder()
                    .add("id", invoices.get(i).getUser().getClientId())
                    .add("name", invoices.get(i).getUser().getName())
                    .add("price_chart", invoices.get(i).getUser().getPriceChart().getId());
            JsonObjectBuilder jsonStandingChargeBuilder = Json.createObjectBuilder()
                            .add("quantity", invoices.get(i).getStandingCharge().getQuantity())
                            .add("price", invoices.get(i).getStandingCharge().getPrice())
                            .add("amount_without_vat", invoices.get(i).getStandingCharge().getAmountWithoutVat())
                            .add("vat", invoices.get(i).getStandingCharge().getVat())
                            .add("amount_with_vat", invoices.get(i).getStandingCharge().getAmountWithVat());

            jsonInvoiceBuilder.add(Json.createObjectBuilder()
                    .add("documentNumber", invoices.get(i).getDocumentNumber())
                    .add("date", invoices.get(i).getDate().toString())
                    .add("user", userBuilder)
                    .add("lines", jsonLineBuilder)
                    .add("standing_charge", jsonStandingChargeBuilder)
                    .add("amount_without_vat", invoices.get(i).getAmountWithoutVat())
                    .add("vat", invoices.get(i).getVat())
                    .add("finalAmount", invoices.get(i).getFinalAmount()));

        }
        JsonArray jsonArray = jsonInvoiceBuilder.build();
        System.out.println(jsonArray.toString());

    }

       public String toJson(User user){
           JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

           jsonBuilder.add("id", user.getClientId())
                   .add("name", user.getName())
                   .add("price_chart", user.getPriceChart().getId());

           JsonObject jsonObject = jsonBuilder.build();
           return jsonObject.toString();
       }

       public String toJson(List<Reading> readingList){
          JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
           for (int i = 0; i < readingList.size(); i++) {
               JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
               jsonObjectBuilder.add("id", readingList.get(i).getId())
                       .add("product", readingList.get(i).getProduct())
                       .add("date", readingList.get(i).getDate().toString())
                       .add("value", readingList.get(i).getValue());
               jsonArrayBuilder.add(jsonObjectBuilder);
           }
           JsonArray jsonArray = jsonArrayBuilder.build();
           return jsonArray.toString();
       }
    }

