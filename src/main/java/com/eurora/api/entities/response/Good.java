package com.eurora.api.entities.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Good {

    @SerializedName("externalId")
    @Expose
    String externalId;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("vat")
    @Expose
    Double vat;

    @SerializedName("duty")
    @Expose
    Double duty;

    @SerializedName("vatRate")
    @Expose
    Double vatRate;

    @SerializedName("dutyRate")
    @Expose
    Double dutyRate;

    @SerializedName("hsCodeCorrectness")
    @Expose
    Double hsCodeCorrectness;

    @SerializedName("calculatedHsCode")
    @Expose
    String calculatedHsCode;

    @SerializedName("calculatedHsCodeRestrictions")
    @Expose
    List<Object> calculatedHsCodeRestrictions;

    @SerializedName("providedHsCodeRestrictions")
    @Expose
    List<Object> providedHsCodeRestrictions;
}
