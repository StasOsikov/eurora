package com.eurora.api.entities.request;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.annotation.Generated;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Generated("com.robohorse.robopojogenerator")
@AllArgsConstructor
@NoArgsConstructor
public class CustomsDutiesCalculatorRequest {

    @Builder.Default
    @SerializedName("externalId")
    String externalId = "123e4567-e89b-12d3-a456-426655440000";

    @Builder.Default
    @SerializedName("orderCurrency")
    String orderCurrency = "USD";

    @Builder.Default
    @SerializedName("transportationPrice")
    Integer transportationPrice = 0;

    @Builder.Default
    @SerializedName("insurancePrice")
    Integer insurancePrice = 0;

    @Builder.Default
    @SerializedName("otherAdditionalCosts")
    Integer otherAdditionalCosts = 0;

    @Builder.Default
    @SerializedName("originCountry")
    String originCountry = "CN";

    @Builder.Default
    @SerializedName("destinationCountry")
    String destinationCountry = "CA";

    @Builder.Default
    @SerializedName("destinationRegion")
    String destinationRegion = "CA-MB";

    @Builder.Default
    @SerializedName("additionalValueShare")
    String additionalValueShare = "MANUAL";

    @Builder.Default
    @SerializedName("goods")
    List<Good> goods = new ArrayList<>(Arrays.asList(Good.builder().build()));;

    @Builder.Default
    @SerializedName("date")
    String date = LocalDate.now().toString();

    @Builder.Default
    @SerializedName("useDeMinimis")
    Boolean useDeMinimis = true;

    @Builder.Default
    @SerializedName("hsCodeReplaceAllowed")
    Boolean hsCodeReplaceAllowed = true;

    @Builder.Default
    @SerializedName("declarationType")
    String declarationType = "H7";

    @Builder.Default
    @SerializedName("transactionModel")
    String transactionModel = "B2B";

    @Builder.Default
    @SerializedName("transactionCategory")
    String transactionCategory = "COMMERCIAL";
}
