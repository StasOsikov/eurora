package com.eurora.api.entities.response.error;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Row {

    @SerializedName("field")
    @Expose
    String field;

    @SerializedName("reason")
    @Expose
    String reason;

    @SerializedName("message")
    @Expose
    String message;
}
