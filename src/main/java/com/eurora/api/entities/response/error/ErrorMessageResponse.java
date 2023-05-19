package com.eurora.api.entities.response.error;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorMessageResponse {

    @SerializedName("type")
    @Expose
    String type;

    @SerializedName("rows")
    @Expose
    List<Row> rows;
}
