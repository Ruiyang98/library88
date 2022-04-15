package com.example.wj.dto.base;

import org.springframework.lang.NonNull;
import static com.example.wj.utils.BeanUtils.updateProperties;

public interface OutputConverter<DTO extends OutputConverter<DTO, DOMAIN>, DOMAIN>{

    @SuppressWarnings("unchecked")
    @NonNull
    default <T extends DTO> T convertFrom(@NonNull DOMAIN domain) {
        updateProperties(domain, this);
        return (T) this;
    }
}
