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
public class Price {

    @Builder.Default
    @SerializedName("currency")
    String currency = "USD";

    @Builder.Default
    @SerializedName("value")
    Double value = 11.25;
}
