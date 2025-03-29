package com.oracle;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class OracleDemoConfiguration extends Configuration {
    @NotEmpty
    private String ociLogId;

    @JsonProperty
    public String getOciLogId() {
        return ociLogId;
    }

    @JsonProperty
    public void setOciLogId(String ociLogId) {
        this.ociLogId = ociLogId;
    }
}