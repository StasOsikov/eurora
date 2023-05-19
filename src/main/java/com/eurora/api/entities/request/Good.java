package com.eurora.api.entities.request;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.annotation.Generated;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Generated("com.robohorse.robopojogenerator")
public class Good {

    @Builder.Default
    @SerializedName("externalId")
    String externalId = "123e4567-e89b-12d3-a456-426655440000";

    @Builder.Default
    @SerializedName("gtin")
    String gtin = "00012345600012";

    @Builder.Default
    @SerializedName("title")
    String title = "Fidget spinners";

    @Builder.Default
    @SerializedName("description")
    String description = "Fidget spinners";

    @Builder.Default
    @SerializedName("hsCode")
    String hsCode = "0101000000";

    @Builder.Default
    @SerializedName("price")
    Price price = Price.builder().build();

    @Builder.Default
    @SerializedName("weight")
    Double weight = 0.15;

    @Builder.Default
    @SerializedName("quantity")
    Integer quantity = 250;

    @Builder.Default
    @SerializedName("additionalValueShareRatio")
    Integer additionalValueShareRatio = 1;
}
