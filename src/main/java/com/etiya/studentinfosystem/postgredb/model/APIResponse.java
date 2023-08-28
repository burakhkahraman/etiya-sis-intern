package com.etiya.studentinfosystem.postgredb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "API'den dönen yanıt mesajlarını içeren model.")
public class APIResponse {

    @Schema(description = "API'den dönen mesajın içeriği.", example = "İşlem başarıyla tamamlandı.")
    private String message;
}
