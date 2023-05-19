package com.eurora.api.entities.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomCalculatorResponse {

    @SerializedName("externalId")
    @Expose
    String externalId;

    @SerializedName("totalVat")
    @Expose
    Double totalVat;

    @SerializedName("totalDuties")
    @Expose
    Double totalDuties;

    @SerializedName("destinationCountry")
    @Expose
    String destinationCountry;

    @SerializedName("originCountry")
    @Expose
    String originCountry;

    @SerializedName("goods")
    @Expose
    List<Good> goods;
}
