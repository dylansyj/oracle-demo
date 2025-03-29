package com.oracle.api;

import com.codahale.metrics.annotation.Timed;
import com.oracle.OracleDemoConfiguration;
import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.loggingingestion.LoggingClient;
import com.oracle.bmc.loggingingestion.model.LogEntry;
import com.oracle.bmc.loggingingestion.model.LogEntryBatch;
import com.oracle.bmc.loggingingestion.model.PutLogsDetails;
import com.oracle.bmc.loggingingestion.requests.PutLogsRequest;
import com.oracle.bmc.loggingingestion.responses.PutLogsResponse;
import com.oracle.model.SurveyData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.*;

@Path("/demo")
@Produces(MediaType.APPLICATION_JSON)
public class OracleDemoResource {

    private static final LoggingClient loggingClient;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private String ociLogId;
    static {
        try {
            final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
            final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
            loggingClient = LoggingClient.builder().build(provider);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to initialize OCI LoggingClient: " + e.getMessage());
        }
    }

    public OracleDemoResource(OracleDemoConfiguration configuration) {
        ociLogId = configuration.getOciLogId();
        System.out.println("Default ociLogId: " + ociLogId);
    }

    @POST
    @Timed
    @Path("/survey")
    public SurveyData surveyData(@Valid SurveyData surveyData) {

        logSurveyToOci(surveyData);

        return surveyData;
    }

    private void logSurveyToOci(SurveyData survey) {
        try {
            String surveyDataJson = objectMapper.writeValueAsString(survey);

            LogEntry logEntry = LogEntry.builder()
                    .data(surveyDataJson)
                    .id(UUID.randomUUID().toString())
                    .time(new Date())
                    .build();

            PutLogsDetails putLogsDetails = PutLogsDetails.builder()
                    .specversion("1.0")
                    .logEntryBatches(new ArrayList<>(Arrays.asList(LogEntryBatch.builder()
                            .entries(new ArrayList<>(Arrays.asList(logEntry)))
                            .type("SurveyData")
                            .source("SurveyService")
                            .subject("Survey Data")
                            .defaultlogentrytime(new Date()).build()))).build();

            PutLogsRequest request = PutLogsRequest.builder()
                    .logId(ociLogId)
                    .putLogsDetails(putLogsDetails)
                    .build();

            PutLogsResponse response = loggingClient.putLogs(request);

            if (response.get__httpStatusCode__() != 200) {
                // log error, dont throw
            }
        } catch (Exception e) {
            //throw error
        }
    }
}